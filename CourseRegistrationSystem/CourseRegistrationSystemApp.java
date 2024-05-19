import java.util.Scanner;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;

public class CourseRegistrationSystemApp
{
    public static ArrayList<Course> readFileC() throws Exception{
        ArrayList<Course> course = new ArrayList<Course>();
        File file = new File("CourseObjects.txt");
        try {
            file.createNewFile();
        }
        catch (Exception e) {
        }
        if(file.length()!=0){
            try{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream objC = new ObjectInputStream(fis);
                while (fis.available() != 0){
                    Course input = (Course) objC.readObject();
                    course.add(input);
                }
                fis.close();
                objC.close();
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
        
        }
        return course;
    }
    
    public static ArrayList<Student> readFileS() throws Exception{
        ArrayList<Student> student = new ArrayList<Student>();
        File file = new File("StudentsObject.txt");
        try {
            file.createNewFile();
        }
        catch (Exception e) {
        }
        if(file.length()!=0){
            try{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream objStd = new ObjectInputStream(fis);
                while (fis.available() != 0){
                    Student input = (Student) objStd.readObject();
                    student.add(input);
                }
                fis.close();
                objStd.close();
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }
        
        }
        return student;
    }
    
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);
        Scanner inline = new Scanner(System.in);
        ArrayList<String> chatS = new ArrayList<String>();
        ArrayList<String> chatF = new ArrayList<String>();
        Admin[] admin = new Admin[2];
        admin[0] = new Admin();
        admin[1] = new Admin();
        admin[0].setName("Aiman");
        admin[0].setId("abc");
        admin[0].setPassword("abc");
        admin[1].setName("Hany");
        admin[1].setId("123");
        admin[1].setPassword("123");
        Facillitator[] faci = new Facillitator[2];
        faci[0] = new Facillitator();
        faci[1] = new Facillitator();
        faci[0].setName("Aiman");
        faci[0].setId("abc");
        faci[0].setPassword("abc");
        faci[1].setName("Hany");
        faci[1].setId("123");
        faci[1].setPassword("123");
        while(true){
            System.out.print("\f");
            System.out.println("******** COURSE REGISTRATION SYSTEM ********");
            System.out.print("\n\t1 - Admin\n\t2 - Student\n\t3 - Facillitator\n\t4 - Shut Down\n\nPlease choose type of user (1/2/3/4) : ");
            int user = in.nextInt();
            if(user == 1){
                boolean reLogin = true;
                Admin userA = new Admin();
                while(reLogin){
                    System.out.print("\nUsername : ");
                    String username = inline.nextLine();
                    System.out.print("Password : ");
                    String password = inline.nextLine();
                    reLogin = userA.login(admin, username, password);
                }
                System.out.println("******** LOGIN SUCCESSFUL ********");
                boolean actionMenu = true;
                while(actionMenu){
                    System.out.print("\f");
                    System.out.println("\n******** MENU ********");
                    System.out.print("\n\t1 - Add New Courses\n\t2 - Update Courses Information\n\t3 - Remove Course\n\t4 - View Courses Data");
                    System.out.print("\n\t5 - View Student Data\n\t6 - Log Out\n\nPlease choose an action (1/2/3/4/5/6) : ");
                    int action = in.nextInt();
                    if(action==1){
                        ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                        userA.createCourse(course);
                        actionMenu = userA.actionMenu();
                    }
                    else if(action==2){
                        ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                        userA.updateCourse(course);
                        actionMenu = userA.actionMenu();
                    }
                    else if(action==3){
                        ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                        userA.removeCourse(course);
                        actionMenu = userA.actionMenu();
                    }
                    else if(action==4){
                        ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                        userA.viewCoursesData(course);
                        actionMenu = userA.actionMenu();
                    }
                    else if(action==5){
                        ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                        userA.viewStudentsData(student);
                        actionMenu = userA.actionMenu();
                    }
                    else if(action==6) actionMenu=false;
                    else{
                        System.out.print("INVALID");
                        actionMenu = userA.actionMenu();
                    }
                }
            }
            else if(user == 2){
                Student userB = new Student();
                System.out.print("Please choose 1 to register and 2 to login : ");
                int mode = in.nextInt();
                if(mode == 1){
                    ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                    userB.register(student);
                    System.out.print("\n\t1 - Back To Main Menu\n\t2 - Shut Down");
                    System.out.print("\n\nPlease choose an action (1/2) :");
                    int action = in.nextInt();
                    if(action==2)break;
                }
                else if(mode == 2){
                    boolean reLogin = true;
                    String username = null;
                    String password = null;
                    while(reLogin){
                        System.out.print("\nUsername : ");
                        username = inline.nextLine();
                        System.out.print("Password : ");
                        password = inline.nextLine();
                        ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                        reLogin = userB.login(student, username, password);
                    }
                    System.out.println("\n******** LOGIN SUCCESSFUL ********");
                    boolean actionMenu = true;
                    while(actionMenu){
                        System.out.print("\f");
                        userB.readAnnouncement();
                        System.out.println("\n******** MENU ********");
                        System.out.print("\n\t1 - View Available Courses\n\t2 - Register Courses\n\t3 - Drop Courses");
                        System.out.print("\n\t4 - View Current Courses\n\t5 - Generate Bill\n\t6 - Message Facillitator");
                        System.out.print("\n\t7 - Read Message\n\t8 - Log Out\n\nPlease choose an action (1/2/3/4/5/6/7/8) :");
                        int action = in.nextInt();
                        if(action==1){
                            ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                            userB.viewAvailableCourses(course);
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==2){
                            ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                            ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                            userB.registerCourse(course, student, username);
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==3){
                            ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                            ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                            userB.dropCourse(course, student, username);
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==4){
                            ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                            userB.viewCurrentCourses(student, username);
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==5){
                            System.out.println("\f\n******** GENERATE BILL ********\n");
                            System.out.format("\nAcademic Fee : RM%.2f", userB.getFee());
                            System.out.print("\n");
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==6){
                            userB.writeFaci(username);
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==7){
                            userB.readFaci(username);
                            actionMenu = userB.actionMenu();
                        }
                        else if(action==8) actionMenu=false;
                        else{
                            System.out.print("INVALID");
                            actionMenu = userB.actionMenu();
                        }
                    }
                }
                else{
                    System.out.println("Invalid : Wrong Input");
                }    
            }
            else if(user == 3){
                boolean reLogin = true;
                Facillitator userC = new Facillitator();
                while(reLogin){
                    System.out.print("\nUsername : ");
                    String username = inline.nextLine();
                    System.out.print("Password : ");
                    String password = inline.nextLine();
                    reLogin = userC.login(faci, username, password);
                }
                System.out.println("******** LOGIN SUCCESSFUL ********");
                boolean actionMenu = true;
                while(actionMenu){
                    System.out.print("\f");
                    System.out.println("\n******** MENU ********");
                    System.out.print("\n\t1 - Read Student's Message\n\t2 - Message Student\n\t3 - Write Announcement\n\t4 - View Courses Data");
                    System.out.print("\n\t5 - View Student Data\n\t6 - Log Out\n\nPlease choose an action (1/2/3/4/5/6) : ");
                    int action = in.nextInt();
                    if(action==1){
                        ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                        userC.readStudent(student);
                        actionMenu = userC.actionMenu();
                    }
                    else if(action==2){
                        userC.writeStudent();
                        actionMenu = userC.actionMenu();
                    }
                    else if(action==3){
                        userC.writeAnnouncements();
                        actionMenu = userC.actionMenu();
                    }
                    else if(action==4){
                        ArrayList<Course> course = CourseRegistrationSystemApp.readFileC();
                        userC.viewCoursesData(course);
                        actionMenu = userC.actionMenu();
                    }
                    else if(action==5){
                        ArrayList<Student> student = CourseRegistrationSystemApp.readFileS();
                        userC.viewStudentsData(student);
                        actionMenu = userC.actionMenu();
                    }
                    else if(action==6) actionMenu=false;
                    else{
                        System.out.print("INVALID");
                        actionMenu = userC.actionMenu();
                    }
                }
            }
            else if(user == 4) break;
            else {
                System.out.println("Invalid : Wrong Input");
            }
        }  
    }
}
