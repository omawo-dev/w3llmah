package w3llmah.models;

import com.github.fxrouter.FXRouter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TRingManager;
import w3llmah.types.TStudentRing;

public class Ring {

    int ringId, schoolId ,maxStudentNumber ;
    Date date;
    String name;

    public Ring() {
    }

    public Ring(int ringId, int schoolId, int maxStudentNumber, Date date, String name) {
        this.ringId = ringId;
        this.schoolId = schoolId;
        this.maxStudentNumber = maxStudentNumber;
        this.date = date;
        this.name = name;
    }

    
    

    public int getRingId() {
        return ringId;
    }

    public void setRingId(int ringId) {
        this.ringId = ringId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getMaxStudentNumber() {
        return maxStudentNumber;
    }

    public void setMaxStudentNumber(int maxStudentNumber) {
        this.maxStudentNumber = maxStudentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ring{" + "ringId=" + ringId + ", schoolId=" + schoolId + ", maxStudentNumber=" + maxStudentNumber + ", date=" + date + ", name=" + name + '}';
    }

    
    
    

    public static void addRing(String name, int maxStuNO) throws SQLException {
        String sql = "INSERT INTO Ring (schoolId , date, name, maxStudentNumber) VALUES (1 , (select date() ) , ? ,? )";
        Connection con = DB.connect();

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, name);
        st.setInt(2, maxStuNO);
        st.executeUpdate();
    }

    public static int getNumberOfStudent(int ringId) {
        //select count(studentId) from Student where ringId = 3

        String sql = "select count(studentId) from Student where ringId = ?";
        System.out.println(ringId);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, ringId);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt("count(studentId)");
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public static Ring getRingByTeacherId(int teacherId) {
        //select count(studentId) from Student where ringId = 3

        String sql = "select r.* from ring r \n"
                + "join Teacher t \n"
                + "on t.ringId = r.ringId \n"
                + "where t.teacherId = ?";

        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        Ring ring;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, teacherId);
            rs = st.executeQuery();
            System.out.println(sql);
            if (rs.next()) {
                ring = new Ring();
                
                ring.setRingId(rs.getInt("ringId"));
                ring.setSchoolId(rs.getInt("schoolId"));
                //ring.setDate(rs.getDate("date"));
                ring.setName(rs.getString("name"));
                ring.setMaxStudentNumber(rs.getInt("maxStudentNumber"));
                
                return ring;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }

    public static ObservableList<TStudentRing> getStudents(int ringId) {
        String sql = "select s.name, s.studentId, s.status ,u.username from Student s  join User u on u.userId = s.userId where ringId = ?";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, ringId);
            rs = st.executeQuery();
            ObservableList<TStudentRing> students = FXCollections.observableArrayList();

            while (rs.next()) {

                TStudentRing studentRing = new TStudentRing();
                studentRing.setStudentId(rs.getInt("studentId"));
                studentRing.setStudentName(rs.getString("name"));
                studentRing.setStudentStatus(rs.getString("status"));
                studentRing.setStudentUsername(rs.getString("username"));
                students.add(studentRing);
            }
            return students;
        } catch (SQLException ex) {
            Logger.getLogger(Ring.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static ObservableList<TRingManager> getStudentCountForRings() {
        String sql = "select ri.ringId as ringId, ri.name as ringName, count(studentId) as studentCount \n" +
            "from Student st \n" +
            "join Ring ri \n" +
            "on st.ringId = ri.ringId \n" +
            "where st.status not like 'ملغي' \n" +
            "group by st.ringId \n";
        System.out.println(sql);
        Connection conn = DB.connect();
        PreparedStatement st;
        ResultSet rs;
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            ObservableList<TRingManager> rings = FXCollections.observableArrayList();

            while (rs.next()) {

                TRingManager ring = new TRingManager();
                ring.setStudentCount(rs.getInt("studentCount"));
                ring.setRingId(rs.getInt("ringId"));
                ring.setRingName(rs.getString("ringName"));
                rings.add(ring);
            }
            return rings;
        } catch (SQLException ex) {
            Logger.getLogger(Ring.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
