import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Facillitator extends User
{
    public Facillitator(){}
    
    public boolean login(Facillitator[] faci, String username, String password){
        boolean reLogin=true;
        if(faci[0].getId().equals(username) && faci[0].getPassword().equals(password)) reLogin = false; 
        else if(faci[1].getId().equals(username) && faci[1].getPassword().equals(password)) reLogin = false; 
        else {
            System.out.println("\nWrong input. Please login again.");
        }
        return reLogin;
    }
    
    public void readStudent(ArrayList<Student> student) throws Exception{
        System.out.print("\f");
        Scanner inLine = new Scanner(System.in);
        System.out.println("\n******** READ HELP REQUEST ********\n");
        String message = null;
        try{
            File file = new File("ChatBoxS.txt");
            
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine();
            int i = 1;
            while(str!=null){
                if(i%2==1){
                    System.out.println("Student : " + str);
                }
                else if(i%2==0){
                    System.out.println("Message : " + str);
                }
                i++;
                str = br.readLine();
            }
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public void writeAnnouncements() throws Exception{
        System.out.print("\f");
        Scanner inLine = new Scanner(System.in);
        System.out.println("\n******** MAKE ANNOUNCEMENT ********\n");
        System.out.println("\nPlease type in your announcement below :");
        String announcement = inLine.nextLine();
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File("Announcement.txt"), true));
            pw.println(announcement);
            pw.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public void writeStudent() throws Exception{
        System.out.print("\f");
        Scanner inLine = new Scanner(System.in);
        System.out.println("\n******** MESSAGE STUDENT ********\n");
        ArrayList<String> chat = new ArrayList<String>();
        System.out.print("Student ID : ");
        String username = inLine.nextLine();
        chat.add(username);
        System.out.println("\nPlease type in your message below :");
        String message = inLine.nextLine();
        chat.add(message);
        try{
            PrintWriter pw = new PrintWriter(new FileWriter(new File("ChatBoxF.txt"), true));
            for(String input: chat){
                pw.println(input);
            }
            pw.close();
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
