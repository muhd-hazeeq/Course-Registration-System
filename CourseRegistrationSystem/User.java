import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public abstract class User
{
    protected String name;
    protected String password;
    protected String id;
    
    public User(){}
    
    public User(String n, String i, String p){
        this.name = n;
        this.id = i;
        this.password = p;
    }
    
    public void setName(String n){this.name = n;}
    public void setPassword(String p){this.password = p;}
    public void setId(String i){this.id = i;}
    
    public String getName(){return this.name;}
    public String getPassword(){return this.password;}
    public String getId(){return this.id;}
    
    public static String generatePassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890@";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<17; i++){
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
    
    public boolean login(){
        return false;
    }
    
    public void register(){}
    
    public boolean actionMenu(){
        boolean actionMenu = true;
        Scanner in = new Scanner(System.in);
        System.out.print("\n\t1 - Back to Action Menu\n\t2 - Log Out\n\nPlease choose an action (1/2) : ");
        int action = in.nextInt();
        if(action==1){}
        else if(action==2) actionMenu = false;
        return actionMenu;
    }
    
    public void viewCoursesData(ArrayList<Course> course){
        System.out.print("\f");
        System.out.println("\n******** COURSES DATA ********\n");
        for(Course input: course){
            System.out.println(input.toString());
        }
    }
    
    public void viewStudentsData(ArrayList<Student> student){
        System.out.print("\f");
        System.out.println("\n******** STUDENTS DATA ********\n");
        for(Student input: student){
            System.out.println(input.toString());
        }
    }
    
    public void writeFileC(ArrayList<Course> course) throws Exception{
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File("Courses.txt")));
            for(Course input: course){
                pw.println(input.toString());
            }
            pw.close();
            
            File file = new File("CourseObjects.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream objC = new ObjectOutputStream(fos);
            for(Course input: course){
                objC.writeObject(input);
            }
            fos.close();
            objC.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public void writeFileS(ArrayList<Student> student) throws Exception{
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File("Students.txt")));
            for(Student input: student){
                pw.println(input.toString());
            }
            pw.close();
            
            File file = new File("StudentsObject.txt");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream objStd = new ObjectOutputStream(fos);
            for(Student input: student){
                objStd.writeObject(input);
            }
            fos.close();
            objStd.close();
            
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
