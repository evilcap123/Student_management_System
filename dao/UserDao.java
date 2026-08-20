package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;

public class UserDao {
    
    //This func registers and user into the System and generates an id for the perticular student automatically ex ("STU - xxxxxxx" miliseconds format)
    public boolean registerUser(User user){
        String query = "INSERT INTO users (generated_id , name , email , password_hash , role) VALUES(?, ?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement msg = conn.prepareStatement(query)){

                msg.setString(1 , user.getGeneratedId());
                msg.setString(2 , user.getName());
                msg.setString(3, user.getEmail());
                msg.setString(4, user.getPasswordHash());
                msg.setString(5, user.getRole());

                return msg.executeUpdate() > 0;
            }
            catch(SQLException e){
                e.printStackTrace();
                return false;
            }
    }

    // This func uses prebuilt method or keyword Called ResultSet in order to traverse rows of the tables and returns the user as soon as the id gets matched

    public User findByGeneratedId(String generatedId){
        String query = "SELECT * FROM users WHERE generated_id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement msg = conn.prepareStatement(query)){

                msg.setString(1, generatedId);

                ResultSet detail = msg.executeQuery();

                if(detail.next()){
                    User user = new User();
                    user.setId(detail.getInt("id"));
                    user.setGeneratedId(detail.getString("generated_id"));
                    user.setName(detail.getString("name"));
                    user.setEmail(detail.getString("email"));
                    user.setPasswordHash(detail.getString("password_hash"));
                    user.setRole(detail.getString("role"));
                    return user;
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }

            return null;
    }
}
