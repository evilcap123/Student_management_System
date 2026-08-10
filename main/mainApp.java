package main;

import dao.StudentDao;
import java.util.List;
import java.util.Scanner;
import model.Student;


    
public class mainApp{


    public static void main(String[] args){
        StudentDao dao = new StudentDao();

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Student");
            System.out.println("3. Delete Student");
            System.out.println("0. EXIT");
            System.out.println("Select ANY option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.println("Enter name: ");
                    String name = sc.nextLine();
                    
                    System.out.println("Enter Email: ");
                    String email = sc.nextLine();
                    
                    System.out.println("Enter course: ");
                    String course = sc.nextLine();

                    System.out.println("Enter gpa: ");
                    double gpa = sc.nextDouble();


                    if(dao.addStudent(new Student(name , email , course , gpa))) {
                        System.out.println("Student Added Succesfully!");
                    } else{
                        System.out.println("Failed To add Student");
                    }
                    break;

                    case 2:
                        List<Student> students = dao.getAllStudent();
                        if( students.isEmpty()) {
                            System.out.println("No records Found...");
                        } 
                        else{
                            System.out.println("\n###### Student Records ######");
                            for(Student s : students){
                                System.out.println(s);
                            }

                        }

                        break;

                        case 3:
                            System.out.println("Enter Student ID to delete: ");
                            int id = sc.nextInt();

                            if(dao.deleteStudent(id)){
                                System.out.println("Student deleted successfully!");
                            }
                            else{
                                System.out.println("Record not found or error occured..");
                            }
                            break;


                        case 0: 
                            System.out.println("Exiting System. GoodBye!");
                            sc.close();
                            System.exit(0);


                        default:
                            System.out.println("Invalid selection. Try again....");
            }
        }
    }
}