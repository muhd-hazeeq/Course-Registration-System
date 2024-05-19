import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

public class Admin extends User
{
    public Admin(){}
    
    public boolean login(Admin[] admin, String username, String password){
        boolean reLogin=true;
        if(admin[0].getId().equals(username) && admin[0].getPassword().equals(password)) reLogin = false; 
        else if(admin[1].getId().equals(username) && admin[1].getPassword().equals(password)) reLogin = false; 
        else {
            System.out.println("\nWrong input. Please login again.");
        }
        return reLogin;
    }
    
    public void createCourse(ArrayList<Course> course) throws Exception {
        System.out.print("\f");
        System.out.println("\n******** CREATE COURSE ********\n");
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
        System.out.print("Enter course name : ");
        String cn = inLine.nextLine();
        System.out.print("Enter course ID : ");
        String id = inLine.nextLine();
        System.out.print("Enter students quota : ");
        int sq = in.nextInt();
        System.out.print("Enter course credit : ");
        int c = in.nextInt();
        System.out.print("Enter course faculty : ");
        String cf = inLine.nextLine();
        System.out.print("Enter fee : ");
        double f = in.nextDouble();
        course.add(new Course(cn, id, sq, 0, c, null, cf, f));
        writeFileC(course);
        System.out.println("Course created");
    }
    
    public void updateCourse(ArrayList<Course> course) throws Exception {
        System.out.print("\f");
        System.out.println("\n******** UPDATE COURSE ********\n");
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
        System.out.print("\nEnter course ID : ");
        String id = inLine.nextLine();
        System.out.println("\n\t1 - Name\n\t2 - Quota\n\t3 - Credit\n\t4 - Faculty\n\t5 - Fee");
        System.out.println("\nEnter information to be updated (1/2/3/4/5) : ");
        int choice = in.nextInt();
        if(choice==1){
            System.out.print("\nEnter the new course name : ");
            String newName = inLine.nextLine();
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setCourseName(newName);
                }
            }
        }
        else if(choice==2){
            System.out.print("\nEnter the new student quota : ");
            int newQuota = in.nextInt();
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setStudentQuota(newQuota);
                }
            }
        }
        else if(choice==3){
            System.out.print("\nEnter the new course credit : ");
            int newCredit = in.nextInt();
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setCredit(newCredit);
                }
            }
        }
        else if(choice==4){
            System.out.print("\nEnter the new faculty : ");
            String newFaculty = inLine.nextLine();
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setCourseFaculty(newFaculty);
                }
            }
        }
        else if(choice==5){
            System.out.print("\nEnter the new fee : ");
            double newFee = in.nextDouble();
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setFee(newFee);
                }
            }
        }
        writeFileC(course);
        System.out.println("Course updated");
    }
    
    public void removeCourse(ArrayList<Course> course) throws Exception {
        System.out.print("\f");
        System.out.println("\n******** REMOVE COURSE ********\n");
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
        System.out.print("\nEnter course ID : ");
        String id = inLine.nextLine();
        Iterator itr = course.iterator();
        while (itr.hasNext()) {
            Course input = (Course)itr.next();
            if (input.getCourseID().equals(id)) itr.remove();
        }
        writeFileC(course);
        System.out.println("\nCourse removed");
    }
}
