package w3llmah.models;

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
import w3llmah.types.TStudentTeacher;
import w3llmah.ui.teacher.AddNewLessonAndReview.ActionHandleToAddNextRecite;
import w3llmah.ui.teacher.dashboard.TeacherDashboardController;

public class Teacher {

    int teacherId, userId, ringId;
    String name;

    public Teacher() {
    }

    public Teacher(int userId, int ringId, String name) {
        this.userId = userId;
        this.ringId = ringId;
        this.name = name;
    }

    public Teacher(int teacherId, int userId, int ringId, String name) {
        this.teacherId = teacherId;
        this.userId = userId;
        this.ringId = ringId;
        this.name = name;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRingId() {
        return ringId;
    }

    public void setRingId(int ringId) {
        this.ringId = ringId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher{" + "teacherId=" + teacherId + ", userId=" + userId + ", ringId=" + ringId + ", name=" + name + '}';
    }

    public static void addTeacher(String teacherName, String ringName) throws SQLException {
        String sql = "INSERT INTO Teacher (userId , ringId, name ) VALUES ( (select userId from User where name like ?) ,(select ringId from Ring where name like ? ) ,? )";
        Connection con = DB.connect();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, teacherName);
            st.setString(2, ringName);
            st.setString(3, teacherName);

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Teacher getTeacherByUserId(Integer userId) {
        String sql = "SELECT * from Teacher WHERE userId = ?";
        Connection con = DB.connect();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                Teacher t = new Teacher();
                t.setUserId(rs.getInt("userId"));
                t.setTeacherId(rs.getInt("teacherId"));
                t.setRingId(rs.getInt("ringId"));
                t.setName(rs.getString("name"));
                return t;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ObservableList<RingTeacher> getRingsTeachersNames() {
        String sql = "select ri.ringId , ' حلقة : ' || ri.Name || '/ الاستاذ: ' ||  te.Name as ringTeacher , te.teacherId , ri.maxStudentNumber \n"
                + "from Ring ri \n"
                + "join Teacher te \n"
                + "on te.ringId = ri.ringId";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            //st.setInt(1, userId);
            rs = st.executeQuery();
            ObservableList<RingTeacher> ringTeacherList = FXCollections.observableArrayList();

            while (rs.next()) {

                RingTeacher ringTeacher = new RingTeacher();
                ringTeacher.setRingId(rs.getInt("ringId"));
                ringTeacher.setTeacherId(rs.getInt("teacherId"));
                ringTeacher.setRingTeacherName(rs.getString("ringTeacher"));
                ringTeacher.setMaxStudent(rs.getInt("maxStudentNumber"));

                ringTeacherList.add(ringTeacher);
            }
            return ringTeacherList;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static ObservableList<TStudentTeacher> getStudents(int userId, TeacherDashboardController tController) {
        String sql = "select   st.studentId , st.name , le.LessonId , (select date from Lesson where studentId = st.studentId and date = (select date('now','+1 day')) ) is not null as hasNextLesson   , le.start as lessonStart , le.end as lessonEnd ,le.assessment as assessmentLe ,re.reviewId, re.start as reviewStart , re.end as reviewEnd , re.assessment as assessmentRe  from Student st \n"
                + "join Ring ri \n"
                + "on st.ringId = ri.ringId\n"
                + "join Teacher te\n"
                + "on ri.ringId=te.ringId\n"
                + "left join Lesson le \n"
                + "on le.studentId=st.studentId and  le.date = (select date())\n"
                + "left join Review re\n"
                + "on re.studentId=st.studentId\n"
                + "and re.reviewId = le.LessonId\n"
                + "join User us\n"
                + "on us.userId=te.userId\n"
                + "where us.userId= ?\n"
                + "and st.status = 'نشط'";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();
            ObservableList<TStudentTeacher> students = FXCollections.observableArrayList();

            while (rs.next()) {

                Button btn = new Button("  +   ");
                btn.setCursor(Cursor.HAND);
                btn.setDisable(rs.getInt("hasNextLesson")==1); 
                int hasLesson = rs.getString("lessonStart") == null ? 0 : 1;

                btn.setId("" + hasLesson + rs.getString("studentId"));

                ActionHandleToAddNextRecite actionHandle = new ActionHandleToAddNextRecite();
                actionHandle.setTeacherController(tController);
                btn.setOnAction(actionHandle::addLessonAndReview);

                TStudentTeacher student = new TStudentTeacher();
                student.setStudentId(rs.getInt("studentId"));
                student.setStudentName(rs.getString("name"));
                student.setLessonId(rs.getInt("LessonId"));
                student.setLessonStart(rs.getString("lessonStart"));
                student.setLessonEnd(rs.getString("lessonEnd"));
                student.setLessonAssess(rs.getString("assessmentLe"));
                student.setReviewId(rs.getInt("reviewId"));
                student.setReviewStart(rs.getString("reviewStart"));
                student.setReviewEnd(rs.getString("reviewEnd"));
                student.setReviewAsses(rs.getString("assessmentRe"));
                student.setButton(btn);
                students.add(student);
            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public static ObservableList<TStudentTeacher> getStudents(int userId) {
        String sql = "select st.studentId , st.name , le.LessonId, le.start as lessonStart , le.end as lessonEnd ,le.assessment as assessmentLe ,re.reviewId, re.start as reviewStart , re.end as reviewEnd , re.assessment as assessmentRe  from Student st \n"
                + "join Ring ri \n"
                + "on st.ringId = ri.ringId\n"
                + "join Teacher te\n"
                + "on ri.ringId=te.ringId\n"
                + "join Lesson le \n"
                + "on le.studentId=st.studentId\n"
                + "join Review re\n"
                + "on re.studentId=st.studentId\n"
                + "and re.reviewId = le.LessonId\n"
                + "join User us\n"
                + "on us.userId=te.userId\n"
                + "where us.userId= ?\n" 
                + "and le.date = (select date())";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();
            ObservableList<TStudentTeacher> students = FXCollections.observableArrayList();
            
            while (rs.next()) {
                TStudentTeacher student = new TStudentTeacher();
                student.setStudentId(rs.getInt("studentId"));
                student.setStudentName(rs.getString("name"));
                student.setLessonId(rs.getInt("LessonId"));
                student.setLessonStart(rs.getString("lessonStart"));
                student.setLessonEnd(rs.getString("lessonEnd"));
                student.setLessonAssess(rs.getString("assessmentLe"));
                student.setReviewId(rs.getInt("reviewId"));
                student.setReviewStart(rs.getString("reviewStart"));
                student.setReviewEnd(rs.getString("reviewEnd"));
                student.setReviewAsses(rs.getString("assessmentRe"));
                students.add(student);
            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
