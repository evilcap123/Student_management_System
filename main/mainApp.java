package main;

import dao.StudentDao;
import dao.UserDao;
import java.util.List;
import java.util.Scanner;
import model.Student;
import model.User;


    
public class mainApp{

    private static UserDao userDao =new UserDao();
    private static StudentDao studentDao = new StudentDao();
    private static Scanner sc = new Scanner(System.in);
    private static User currentUser= null;

    public static void main(String[] args){


        while(true){
            if(currentUser == null){
                loginMenu();
            }else{
                mainMenu();
            }
        }
    }

    static void loginMenu(){
        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. EXIT");
            System.out.println("Select an option: ");
            int choice = sc.nextInt();
            sc.nextLine();


            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("GoodBye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                
                }
    }

        static void login(){
            System.out.println("Enter your ID: ");
            String generatedId = sc.nextLine().trim();

            System.out.println("DEBUG login ID: [" + generatedId + "]");

            System.out.print("Enter your password: ");
            String password = sc.nextLine();

            User user = userDao.findByGeneratedId(generatedId);

            if(user == null){
                System.out.println("User not found");
                return;
            }

            if(user.getPasswordHash().equals(password)){
                System.out.println("\nLogin Successful!!");
                System.out.println("Welcome, " + user.getName());
                currentUser = user;
            }
            else{
                System.out.println("Invalid Password");
            }
}

    static void register(){
        System.out.println("Enter your name: ");
        String name= sc.nextLine();

        System.out.println("Enter your email: ");
        String email = sc.nextLine();

        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        String generatedId = "STU-" + System.currentTimeMillis();

        String role = "student";

        User newUser = new User(generatedId, name, email, password, role);

        if(userDao.registerUser(newUser)){
            System.out.println("\nRegisteration succesfull");
            System.out.println("Your login ID is: " + generatedId);
            System.out.println("Keep this safe!!");

        }
        else{
            System.out.println("Registeration failed");
        }
    }

    static void mainMenu(){

        System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Update Student");
            System.out.println("5. Logout");
            System.out.println("0. EXIT");
            System.out.println("Select ANY option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    logout();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
            }

    }

    static void addStudent(){
        System.out.print("Enter Student name: ");
        String name= sc.nextLine();

        System.out.print("Enter student email: ");
        String email = sc.nextLine();

        System.out.print("Enter course: ");
        String course = sc.nextLine();
        
        System.out.print("Enter GPA: ");
        double gpa = sc.nextDouble();
        sc.nextLine();

        Student student = new Student(name, email, course, gpa);

        if(studentDao.addStudent(student)){
            System.out.println("Student added successfully");
        }else{
            System.out.println("Failed to add studetn");
        }
    }

    static void viewAllStudents(){
        List<Student> students = studentDao.getAllStudent();

        if(students.isEmpty()){
            System.out.println("No such students found");
        }
        else{
            System.out.println("\n====== ALL STUDENTS ======");
            for(Student s: students){
                System.out.println(s);
            }
        }
    }

    static void deleteStudent(){
        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        if(studentDao.deleteStudent(id)){
            System.out.println("Student deleted successfully!!");
        }
        else{
            System.out.println("Failed to deleted student");
        }
    }

    static void updateStudent(){
        System.out.print("Enter student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        List<Student> allStudents = studentDao.getAllStudent();

        Student found = null;

        for(Student s : allStudents){
            if(s.getId() == id){
                found = s;
                break;
            }
        }

        if(found == null){
            System.out.println("Student not found!");
            return;
        }

        System.out.println("Current: " + found);
        System.out.println("New name(press enter to keep current): ");
        String newName = sc.nextLine();

        if(!newName.isEmpty()){
            found.setName(newName);
        }

        System.out.print("New email(press enter to keep current): ");
        String newEmail = sc.nextLine();
        if(!newEmail.isEmpty()){
            found.setEmail(newEmail);
        }

        System.out.print("New course(press enter to keep current): ");
        String newCourse = sc.nextLine();
        if(!newCourse.isEmpty()){
            found.setCourse(newCourse);
        }

        System.out.print("New GPA(press enter to keep current): ");
        String newGpaStr = sc.nextLine();
        if(!newGpaStr.isEmpty()){
            try{
                double newGpa = Double.parseDouble(newGpaStr);
                found.setGpa(newGpa);
            } 
                
            catch(NumberFormatException e){
                System.out.println("Invalid GPA");
            }
        }

        if(studentDao.updateStudent(found)){
            System.out.println("Student updated successfully!");
        }
        else{
            System.out.println("Failed to update student");
        }

    }

    static void logout(){
        System.out.println("You have been logged out");
        currentUser = null;
    }
}