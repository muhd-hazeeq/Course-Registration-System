import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String courseName;
    private String courseID;
    private int studentQuota;
    private int enrolledStudents;
    private int credit;
    private String courseStudents;
    private String courseFaculty;
    private double fee;
    
    public Course(){}
    
    public Course(String cn, String id, int sq, int es, int c, String cs, String cf, double f){
        this.courseName = cn;
        this.courseID = id;
        this.studentQuota = sq;
        this.enrolledStudents = es;
        this.credit = c;
        this.courseStudents = cs;
        this.courseFaculty = cf;
        this.fee = f;
    }
    
    public void setCourseName(String cn){this.courseName = cn;}
    public void setCourseID(String id){this.courseID = id;}
    public void setStudentQuota(int sq){this.studentQuota = sq;}
    public void setEnrolledStudents(int es){this.enrolledStudents = es;}
    public void setCredit(int c){this.credit = c;}
    public void setCourseStudents(String cs){this.courseStudents = cs;}
    public void setCourseFaculty(String cf){this.courseFaculty = cf;}
    public void setFee(double f){this.fee = f;}
    
    public String getCourseName(){return this.courseName;}
    public String getCourseID(){return this.courseID;}
    public int getStudentQuota(){return this.studentQuota;}
    public int getEnrolledStudents(){return this.enrolledStudents;}
    public int getCredit(){return this.credit;}
    public String getCourseStudents(){return this.courseStudents;}
    public String getCourseFaculty(){return this.courseFaculty;}
    public double getFee(){return this.fee;}
    
    public String toString(){
        return "Course Name : " + this.getCourseName() + "\nCourse ID : " + this.getCourseID() + "\nStudent Quota : " +
        this.getStudentQuota() + "\nNumber of Enrolled Students : " + this.getEnrolledStudents() + "\nCredit : " + this.getCredit() + "\nFaculty : " +
        this.getCourseFaculty() + "\nFee : " + this.getFee() + 
        "\nList of Students Enrolled :\n" + this.getCourseStudents() + 
        "\n\n---------------------------------------------------------------------------------------\n";
    }
}
