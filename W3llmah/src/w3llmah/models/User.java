
package w3llmah.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import w3llmah.dto.LoginDto;
import w3llmah.enums.UserType;
import w3llmah.services.DB;

/**
 *
 * @author 96655
 */
public class User {
    
    int userId;
    String username , password , createdData , userKind , name ;

    public User() {
    }

    public User(String username, String password, String createdData, String userKind , String name) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.createdData = createdData;
        this.userKind = userKind;
    }

    public User(int userId, String username, String password, String createdData, String userKind , String name) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.password = password;
        this.createdData = createdData;
        this.userKind = userKind;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedData() {
        return createdData;
    }

    public void setCreatedData(String createdData) {
        this.createdData = createdData;
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", createdData=" + createdData + ", userKind=" + userKind + '}';
    }
    
    public static User login(LoginDto loginDto) {
        User user = null;
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        
        String sql = "SELECT * FROM User" +
                " WHERE username LIKE ? AND password LIKE ?";
        
        Connection conn = DB.connect();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                System.out.println("success");
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setUserKind(rs.getString("userKind"));
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                System.out.println(user);
                return user;
            } else {
                System.out.println("invalid username or password!");
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public static void addUser(  String username,String password,UserType userKind ,String name) throws SQLException {
        int randomNumber = (int)  (1+Math.random()*9);
        
        password = ""+randomNumber+""+randomNumber+""+randomNumber+""+randomNumber;
        username = userKind.equals(UserType.TEACHER)?"tch":"stu" ; 
        
        String sql = "INSERT INTO User (username, password, createdData , userKind , name ) VALUES (  ? ||(select seq+1 from sqlite_sequence where name = 'User') ,? , (select date() ) ,? ,? )";
        Connection con = DB.connect();
        
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, userKind.toString());
            st.setString(4, name);
           
             st.executeUpdate();
            
            
            
        
        

    }
    
    public static User getUserByNameOfUser(String name){
        
        
         User user = null;
        
        String sql = "SELECT * FROM User" +
                " WHERE name LIKE ? ";
        
        Connection conn = DB.connect();
        try {
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                System.out.println("success");
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setUserKind(rs.getString("userKind"));
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                System.out.println(user);
                return user;
            } else {
                System.out.println("invalid username or password!");
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
        
    }
    
}
