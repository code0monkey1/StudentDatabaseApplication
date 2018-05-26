import java.io.Serializable;
import java.util.ArrayList;


public class Student implements Serializable {

    private  String name;
    private String ID;
    private ArrayList<Courses> courses=new ArrayList<>();
    private int totalMoney=3200;

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    private int grade;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
      return ID;
    }

    public void setID() {

        String pick ="abcdefghijklmnopqrstuvwxyz12345";
        String unique = "" + getGrade();
        do {
           unique=""+getGrade();
            for (int i = 0; i < 4; i++) {
                int index = (int) (Math.random() * pick.length());
                unique+=pick.charAt(index);
            }
        }while (Authentication.uniqueStudent.containsKey(unique));
        this.ID = unique;
    }

    public ArrayList<Courses> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Courses> courses) {
        this.courses = courses;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney() {
        this.totalMoney -=800;
    }
    public void addSubject(Courses obj){
        setTotalMoney();
        courses.add(obj);
    }
   public static void main(String args[]){


        Student stu=new Student();

        stu.addSubject(Courses.chemistry101);
   }

}
