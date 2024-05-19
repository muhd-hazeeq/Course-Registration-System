import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;

public class Student extends User implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private String id;
    private String currentCourses;
    private ArrayList<Course> listCourse;
    private double fee;
    
    public Student(){}
    
    public Student(String n, String i, String p){
        this.name = n;
        this.id = i;
        this.password = p;
    } 
    
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public String getId(){return this.id;}
    public String getCurrentCourses(){return this.currentCourses;}
    public void setCurrentCourses(String cc){this.currentCourses = cc;}
    public ArrayList<Course> getListCourse(){return this.listCourse;}
    public void setListCourse(ArrayList<Course> lc){this.listCourse = lc;}
    public void setFee(double f){this.fee = f;}
    public double getFee(){return this.fee;}
    
    public static boolean login(ArrayList<Student> student, String username, String password){
        boolean reLogin=true;
        for(Student input: student){
            if(input.getId().equals(username) && input.getPassword().equals(password)){
                reLogin = false;
                break;
            }
        }
        if(reLogin == true){
            System.out.println("\nWrong input. Please login again.");
        }
        return reLogin;
    }
    
    public void register(ArrayList<Student> student) throws Exception {
        int count = student.size();
        Scanner inLine = new Scanner(System.in);
        System.out.println("\nPlease enter your name and student ID to register");
        System.out.print("Name : ");
        String name = inLine.nextLine();
        System.out.print("Student ID : ");
        String id = inLine.nextLine();
        String password = Student.generatePassword();
        student.add(new Student(name, id, password));
        System.out.println("\nRegistration complete. Please login with these information.");
        System.out.println("Username : " + student.get(count).getId() + "\nPassword : " + student.get(count).getPassword());
        writeFileS(student);
        
        System.out.println("\n******** REGISTRATION SUCCESSFUL ********");
    }
    
    public void viewAvailableCourses(ArrayList<Course> course){
        System.out.print("\f");
        System.out.println("\n******** AVAILABLE COURSES ********\n");
        int i=0;
        for(Course input: course){
            System.out.println((i+1) + " - " + input.getCourseName() + "(" + input.getCourseID() + ")");
            i++;
        }
    }
    
    public void registerCourse(ArrayList<Course> course, ArrayList<Student> student, String username) throws Exception {
        System.out.print("\f");
        System.out.println("\n******** REGISTER COURSE ********\n");
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
        System.out.print("\nEnter course ID : ");
        String id = inLine.nextLine();
        System.out.print("\nConfirm registration? (y/n) : ");
        char confirm = in.next().charAt(0);
        if(confirm=='y'){
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setEnrolledStudents(input.getEnrolledStudents()+1);
                    for(Student input2: student){
                        if(input2.getId().equals(username)){
                            if(input.getCourseStudents()==null){
                                input.setCourseStudents(" - " + input2.getName() + " (" + input2.getId() + ")\n");
                            }
                            else{
                                input.setCourseStudents(input.getCourseStudents() + " - " + input2.getName() + " (" + input2.getId() + ")\n");
                            }
                            if(input2.getCurrentCourses()==null){
                                input2.setCurrentCourses(" - " + input.getCourseName() + " (" + input.getCourseID() + ")\n");
                            }
                            else{
                                input2.setCurrentCourses(input2.getCurrentCourses() + " - " + input.getCourseName() + " (" + input.getCourseID() + ")\n");
                            }
                            ArrayList<Course> listCourse = new ArrayList<Course>();
                            if(input2.getListCourse()!=null){
                                listCourse = input2.getListCourse();
                                listCourse.add(input);
                            }
                            else 
                                listCourse.add(input);
                            input2.setListCourse(listCourse);
                        }
                    }
                }
            }
            student = this.generateBill(student, username);
            writeFileC(course);
            writeFileS(student);
        }
        System.out.println("\nCourse registered");
    }
    
    public void dropCourse(ArrayList<Course> course, ArrayList<Student> student, String username) throws Exception {
        System.out.print("\f");
        System.out.println("\n******** DROP COURSE ********\n");
        Scanner in = new Scanner(System.in);
        Scanner inLine = new Scanner(System.in);
        System.out.print("\nEnter course ID : ");
        String id = inLine.nextLine();
        System.out.print("\nConfirm dropping? (y/n) : ");
        char confirm = in.next().charAt(0);
        if(confirm=='y'){
            for(Course input: course){
                if(input.getCourseID().equals(id)){
                    input.setEnrolledStudents(input.getEnrolledStudents()-1);
                    for(Student input2: student){
                        if(input2.getId().equals(username)){
                            String old = " - " + input2.getName() + " (" + input2.getId() + ")\n";
                            input.setCourseStudents(input.getCourseStudents().replace(old,""));
                            String old2 = " - " + input.getCourseName() + " (" + input.getCourseID() + ")\n";
                            input2.setCurrentCourses(input2.getCurrentCourses().replace(old2,""));
                            Iterator itr = input2.getListCourse().iterator();
                            while (itr.hasNext()) {
                                Course old3 = (Course)itr.next();
                                if (old3.getCourseID().equals(input.getCourseID())) itr.remove();
                            }
  
                        }
                    }
                }
            }
            student = this.generateBill(student, username);
            writeFileC(course);
            writeFileS(student);
        }
        System.out.println("\nCourse registered");
    }
    
    public void viewCurrentCourses(ArrayList<Student> student, String username){
        System.out.print("\f");
        System.out.println("\n******** CURRENT COURSES ********\n");
        for(Student input: student){
            if(input.getId().equals(username)){
                System.out.println(input.getCurrentCourses());
            }
        }
    }
    
    public ArrayList<Student> generateBill(ArrayList<Student> student, String username){
        double fee = 0;
        ArrayList<Course> course = new ArrayList<Course>();
        for(Student input: student){
            if(input.getId().equals(username)){
                course = input.getListCourse();
                for(Course input2: course){
                    fee = fee + input2.getFee();
                }
                input.setFee(fee);
            }
        }
        this.setFee(fee);
        return student;
    }
    
    public void readAnnouncement() throws Exception{
        Scanner inLine = new Scanner(System.in);
        String announcement = null;
        try{
            File file = new File("Announcement.txt");
            if(file.length()==0){}
            else{
                System.out.println("******** ANNOUNCEMENTS ********\n");
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String str = br.readLine();
                while(str!=null){
                    System.out.println("\nAnnouncement : " + str);
                    str = br.readLine();
                }
            }
            
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public void readFaci(String username) throws Exception{
        System.out.print("\f");
        Scanner inLine = new Scanner(System.in);
        System.out.println("\n******** READ ANSWER ********\n");
        String message = null;
        try{
            File file = new File("ChatBoxF.txt");
            
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            while(str!=null){
                if(str.equals(username)){
                    str = br.readLine();
                    System.out.println("Message : " + str);
                }
                str = br.readLine();
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public void writeFaci(String username) throws Exception{
        System.out.print("\f");
        Scanner inLine = new Scanner(System.in);
        System.out.println("\n******** MESSAGE FACILLITATOR ********\n");
        ArrayList<String> chat = new ArrayList<String>();
        chat.add(username);
        System.out.println("\nPlease type in your question below :");
        String message = inLine.nextLine();
        chat.add(message);
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File("ChatBoxS.txt"), true));
            for(String input: chat){
                pw.println(input);
            }
            pw.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public String toString(){
        return "Name : " + this.getName() + "\nStudent ID : " + this.getId() + "\nPassword : " + this.getPassword() + 
        "\nList of Enrolled Courses :\n" + this.getCurrentCourses() +  "\nAcademic Fee : RM" + String.format("%.2f", this.getFee()) +
        "\n---------------------------------------------------------------------------------------\n";
    }
}