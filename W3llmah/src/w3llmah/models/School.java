/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.models;

import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TRingManager;
import w3llmah.types.TStudentTeacher;
import w3llmah.ui.teacher.AddNewLessonAndReview.ActionHandleToAddNextRecite;
import w3llmah.ui.teacher.dashboard.TeacherDashboardController;

/**
 *
 * @author 96655
 */
public class School {

    int schoolId, managerId;

    public School() {
    }

    public School(int managerId) {
        this.managerId = managerId;
    }

    public School(int schoolId, int managerId) {
        this.schoolId = schoolId;
        this.managerId = managerId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "School{" + "schoolId=" + schoolId + ", managerId=" + managerId + '}';
    }

    public static ObservableList<TRingManager> getRings(int userId) {
        String sql = "select ri.ringId as ringId, te.teacherId as teacherId, sc.schoolId as schoolId, ma.managerId as managerId, ri.name as ringName, te.name as teacherName , us.username from Ring ri\n"
                + "join School sc\n"
                + "on ri.schoolId = sc.schoolId\n"
                + "join Manager ma\n"
                + "on sc.managerId = ma.managerId\n"
                + "join Teacher te\n"
                + "on te.ringId = ri.ringId\n"
                + "join User us\n"
                + "on us.userId = te.userId\n"
                + "where ma.userId = ?";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();
            ObservableList<TRingManager> rings = FXCollections.observableArrayList();

            while (rs.next()) {

                Button btn = new Button("  <  ");
                btn.setCursor(Cursor.HAND);
                int ringId = rs.getInt("ringId");
                btn.setId("" + ringId);

                btn.setOnAction((event) -> {
                    LocalStorage.setItem("ringId", ringId);
                    try {
                        FXRouter.goTo("adminStudentsRing");
                    } catch (IOException ex) {
                        Logger.getLogger(School.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                TRingManager ringManager = new TRingManager();
                ringManager.setRingId(rs.getInt("ringId"));
                ringManager.setManagerId(rs.getInt("managerId"));
                ringManager.setSchoolId(rs.getInt("schoolId"));
                ringManager.setTeacherId(rs.getInt("teacherId"));
                ringManager.setRingName(rs.getString("ringName"));
                ringManager.setTeacherName(rs.getString("teacherName"));
                ringManager.setTeacherUsername(rs.getString("username"));
                ringManager.setButton(btn);
                rings.add(ringManager);
            }
            return rings;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
