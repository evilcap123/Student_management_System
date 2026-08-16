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
            System.out.println("4. Update Student");
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
                        
                        case 4:
                            System.out.println("Enter Student ID to update: ");
                            int updateId = sc.nextInt();
                            sc.nextLine();

                            Student selectedStudent = null;

                            List<Student> allStudent = dao.getAllStudent();

                            for(Student s : allStudent){
                                if(s.getId() == updateId){
                                    selectedStudent = s;
                                    break;
                                }
                            }

                            if(selectedStudent == null){
                                System.out.println("Student not found duh!!!");
                                break;
                            }

                            System.out.println("Current student: " + selectedStudent);

                            System.out.println("Enter new name (press enter to keep current) : ");
                            String newName = sc.nextLine();
                            
                            if(!newName.trim().isEmpty()){
                                selectedStudent.setName(newName);
                            }


                            System.out.println("Enter new Email (press enter to keep current) : ");
                            String newEmail = sc.nextLine();

                            if(!newEmail.trim().isEmpty()){
                                selectedStudent.setEmail(newEmail);
                            }

                            System.out.println("Enter new course (press enter to keep current) : ");
                            String newCourse = sc.nextLine();

                            if(!newCourse.trim().isEmpty()){
                                selectedStudent.setCourse(newCourse);
                            }

                            System.out.println("Enter new gpa (press enter to keep current) : ");
                            String newGpa = sc.nextLine();

                            if(!newGpa.trim().isEmpty()){
                                try {
                                    selectedStudent.setGpa(Double.parseDouble(newGpa));
                                } catch (NumberFormatException e) {
                                    System.out.println("Invalid GPA . keeing current one");
                                }
                            }

                            if(dao.updateStudent(selectedStudent)){
                                System.out.println("Student updated successfully!");
                            } else{
                                System.out.println("Failed to update stduetn");
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