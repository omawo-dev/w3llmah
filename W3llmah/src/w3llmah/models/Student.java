/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import w3llmah.enums.StudentStatus;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TStudent;
import w3llmah.types.TStudentTeacher;
import w3llmah.ui.teacher.AddNewLessonAndReview.ActionHandleToAddNextRecite;

/**
 *
 * @author mapvsnp
 */
public class Student {

    private int studentId;
    private String name;
    private int userId;
    private int ringId;

    public Student(int studentId, String name, int userId, int ringId) {
        this.studentId = studentId;
        this.name = name;
        this.userId = userId;
        this.ringId = ringId;
    }

    public Student() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", name=" + name + ", userId=" + userId + ", ringId=" + ringId + '}';
    }

    

    public static ObservableList<TStudent> getStudentReport(int userId, int month , int year) {
        String sql = "select st.ringId, st.studentId , st.name  ,le.LessonId , le.start || '-' || le.end as lesson, le.assessment as assessmentLe , le.date , re.reviewId , re.start || '-' || re.end as review , re.assessment as assessmentRe  , re .date from Student st \n"
                + "join Lesson le \n"
                + "on le.studentId = st.studentId\n"
                + "join User us \n"
                + "on st.userId = us.userId\n"
                + "join Review re\n"
                + "on st.studentId = re.studentId and le.LessonId = re.reviewId\n"
                + "where us.userId = ?"
                + "and le.date > (select date('"+year+"-0" + month + "-01')) \n"
                + "and  le.date <  (select date('"+year+"-0" + month + "-30')) \n"
                + "order by le.date";
        System.out.println(userId);
        System.out.println("TStudent"+sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);
//            st.setInt(2, month);
//            st.setInt(3, month);
            rs = st.executeQuery();

            ObservableList<TStudent> students = FXCollections.observableArrayList();
            while (rs.next()) {
                
                TStudent student = new TStudent();
                student.setLessonId(rs.getInt("LessonId"));
                student.setLesson(rs.getString("lesson"));
                student.setLessonAssess(rs.getString("assessmentLe"));
                student.setReviewId(rs.getInt("reviewId"));
                student.setReview(rs.getString("review"));
                student.setReviewAssess(rs.getString("assessmentRe"));
                student.setDate(rs.getString("date"));
                students.add(student);
            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
    
    public static Student getStudent(int userId){
        
        String sql = "select  * from Student where userId = ?";
        System.out.println(userId);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        Student stu =null;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);

            rs = st.executeQuery();
            
            if(rs.next()){
                stu = new Student();
                stu.setStudentId(rs.getInt("studentId"));
                stu.setName(rs.getString("name"));
                stu.setRingId(rs.getInt("ringId"));
                stu.setUserId(rs.getInt("userId"));
            }

            
            return stu;
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    public static String currentReciting(int userId) {
        String sql = "select  '{ الدرس : ('||  le.start || '-' || le.end || ' ) , المراجعة : ( ' ||  re.start || '-' || re.end  || ' ) }' as recite  from Student st \n"
                + "join Lesson le \n"
                + "on le.studentId = st.studentId\n"
                + "join User us \n"
                + "on st.userId = us.userId\n"
                + "join Review re \n"
                + "on re.studentId = st.studentId  and re.reviewId = le.LessonId\n"
                + "where us.userId = ? and \n"
                + "le.date = (select date() )";
        System.out.println(userId);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getString("recite");
            } else {
                return " null ";
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void addNewStudent(String studentName, int ringId) {
        String sql = "INSERT INTO Student (ringId , userId, name) VALUES ( ?  , (select userId from User where name like ? ) ,? )";
        Connection con = DB.connect();
        try {
            PreparedStatement st;

            st = con.prepareStatement(sql);

            st.setInt(1, ringId);
            st.setString(2, studentName);
            st.setString(3, studentName);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static int updateStudentStatus(StudentStatus status, Integer studentId) {
        String sql = "UPDATE Student" + 
                " SET status = ?" +
                " WHERE studentId = ?";
        Connection conn = DB.connect();
        PreparedStatement st;
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, status.toString());
            st.setInt(2, studentId);
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
