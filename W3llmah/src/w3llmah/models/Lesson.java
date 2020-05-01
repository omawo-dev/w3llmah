/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import w3llmah.services.DB;

/**
 *
 * @author 96655
 */
public class Lesson {

    int lessonId, studentId;
    String start, end, assessment;
    Date date;

    public Lesson() {
    }

    public Lesson(int studentId, String start, String end, String assessment, Date date) {
        this.studentId = studentId;
        this.start = start;
        this.end = end;
        this.assessment = assessment;
        this.date = date;
    }

    public Lesson(int lessonId, int studentId, String start, String end, String assessment, Date date) {
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.start = start;
        this.end = end;
        this.assessment = assessment;
        this.date = date;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Lesson{" + "lessonId=" + lessonId + ", studentId=" + studentId + ", start=" + start + ", end=" + end + ", assessment=" + assessment + ", date=" + date + '}';
    }

    public static void addNextLesson(int studentId, String startLesson, String endLesson, int day) throws SQLException {
        
        String sql = "INSERT INTO Lesson (studentId ,start , end , date ) VALUES ( ? , ? , ? , (select date('now' ,  '+"+day+" day'  ) ) )";
        Connection con = DB.connect();
        System.out.println(sql);
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, studentId);
            st.setString(2, startLesson);
            st.setString(3, endLesson);
           
            System.out.println(sql);
            

            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int updateLesson(Lesson newLesson) {
        String sql = "UPDATE Lesson" + 
                " SET assessment = ?" +
                " , start = ?" +
                " , end = ?" +
                " WHERE lessonId = ?";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, newLesson.getAssessment());
            st.setString(2, newLesson.getStart());
            st.setString(3, newLesson.getEnd());
            st.setInt(4, newLesson.getLessonId());
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
