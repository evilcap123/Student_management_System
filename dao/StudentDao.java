package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;


public class StudentDao{

    public boolean addStudent(Student student){
        String query = "INSERT INTO students (name, email, course, gpa) VALUES(?, ?, ?, ?)";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement msg = conn.prepareStatement(query)){

                msg.setString(1 , student.getName());
                msg.setString(2, student.getEmail());
                msg.setString(3, student.getCourse());
                msg.setDouble(4, student.getGpa());
     
                return msg.executeUpdate() > 0;
            }
            catch (SQLException e){
                System.err.println("Error adding Student: " + e.getMessage());
                return false;
            }
    }

    public List<Student> getAllStudent() {
        List<Student> list = new ArrayList<>();

        String query = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement msg = conn.createStatement();
             ResultSet rs = msg.executeQuery(query)){
                while (rs.next()){
                    Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course"),
                        rs.getDouble("gpa")
                    );
                    list.add(s);
                }

             } catch (SQLException e){
                System.err.println("Error fetching records: " + e.getMessage());
                
             }
             return list;

    }

    public boolean deleteStudent(int id){
        String query = "DELETE FROM students WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement msg = conn.prepareStatement(query)){

                msg.setInt(1, id);
                return msg.executeUpdate() > 0;
            }
            catch(SQLException e){
                System.err.println("Error deleting the student: " + e.getMessage());
                return false;
            }
    }

    public boolean updateStudent(Student student){
        String query = "UPDATE students SET name = ? , enail = ?. course = ?, gpa = ?, WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement msg = conn.prepareStatement(query)){

                msg.setString(1 , student.getName());
                msg.setString(2 , student.getEmail());
                msg.setString(3 , student.getCourse());
                msg.setDouble(4 , student.getGpa());
                

                return msg.executeUpdate() > 0;
            }
            catch(SQLException e){
                System.err.println("Error updateing the studetn: " + e);
                return false;
            }
    }
}