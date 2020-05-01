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
import w3llmah.models.User;
import w3llmah.services.DB;

/**
 *
 * @author 96655
 */
public class Review {
    
    int reviewId , studentId;
    String start , end , assessment ;
    Date date ;

    public Review() {
    }

    public Review(int studentId, String start, String end, String assessment, Date date) {
        this.studentId = studentId;
        this.start = start;
        this.end = end;
        this.assessment = assessment;
        this.date = date;
    }

    public Review(int reviewId, int studentId, String start, String end, String assessment, Date date) {
        this.reviewId = reviewId;
        this.studentId = studentId;
        this.start = start;
        this.end = end;
        this.assessment = assessment;
        this.date = date;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
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
        return "Review{" + "reviewId=" + reviewId + ", studentId=" + studentId + ", start=" + start + ", end=" + end + ", assessment=" + assessment + ", date=" + date + '}';
    }
    
    
    
    public static void addNextReview(  int studentId ,String startLesson,String endLesson ,int day) throws SQLException {
        
        String sql = "INSERT INTO Review (studentId ,start , end , date ) VALUES ( ? ,? ,?, ( select date('now' ,'+"+day+" day'  ) ) )";
        Connection con = DB.connect();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,studentId );
            st.setString(2, startLesson);
            st.setString(3, endLesson);
            
            System.out.println( sql);
            
           
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int updateReview(Review newReview) {
        String sql = "UPDATE Review" + 
                " SET assessment = ?" +
                " , start = ?" +
                " , end = ?" +
                " WHERE reviewId = ?";
        Connection conn = DB.connect();
        PreparedStatement st;
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, newReview.getAssessment());
            st.setString(2, newReview.getStart());
            st.setString(3, newReview.getEnd());
            st.setInt(4, newReview.getReviewId());
            
            st.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Lesson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
