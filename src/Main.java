import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) {

        int prompt;
        File myfine = new File("std.bat");
        Scanner sc = new Scanner(System.in);
        if (myfine.length() > 0) {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("std.bat"))) {
                Authentication.uniqueStudent = (HashMap<String, Student>) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        while (true) {
            System.out.println("If you want to make a fresh entry PRESS 1 \n If you want to manipulate data in an existing student entry PRESS 2 " +
                    "\n If you want to save and quit PRESS 3");
            prompt = sc.nextInt();
            sc.nextLine();

            switch (prompt) {
                case 1:
                    Student s1 = new Student();
                    System.out.println("Enter the student name");
                    String temp = sc.nextLine();
                    s1.setName(temp);
                    System.out.println("Enter the Student Grade");
                    int grade = sc.nextInt();
                    s1.setGrade(grade);
                    s1.setID();
                    Authentication.uniqueStudent.put(s1.getID(), s1);
                    System.out.println("The unique student ID is " + s1.getID());
                    break;
                case 2:
                    System.out.println("Enter the student ID");
                    String str = sc.nextLine();

                    if (Authentication.uniqueStudent.containsKey(str)) {
                        Student objective = Authentication.uniqueStudent.get(str);
                        System.out.println("The students name is " + objective.getName() + "\nThe students grade is " + objective.getGrade());
                        System.out.println("The subjects taken by the student are");
                        ArrayList<Courses> c = objective.getCourses();
                        if (c != null) {
                            for (Courses cr : c) {
                                System.out.println(cr.toString());
                            }
                        }
                        else if(c==null){
                            System.out.println("The student has not enrolled in any course ");
                        }
                        System.out.println("The balance in the students account is " + objective.getTotalMoney());

                        if (objective.getTotalMoney() >= 800) {
                            System.out.println("You have enough money to take " + (objective.getTotalMoney() / 800) + " more courses");

                            System.out.println("Would you like to take more courses ? \nEnter 1 for yes \n" +
                                    "Enter 2 for no");

                            int token = sc.nextInt();
                            sc.nextLine();
                            if (token == 1) {

                                HashSet<Courses> allCourses = new HashSet<>();

                                for (Courses cs : Courses.values()) {
                                    allCourses.add(cs);
                                }
                                if (c != null) allCourses.removeAll(c);
                                System.out.println("Select from the following courses");

                                for (Courses course : allCourses) {
                                    System.out.println(course);
                                }

                                  String stringu=sc.nextLine();
                                  Courses crs=null;
                                  try {
                                      crs=Courses.valueOf(stringu);
                                  }
                                  catch (IllegalArgumentException e){
                                     System.out.println("Wrong Subject entered try again");
                                     continue;
                                  }

                                System.out.println("Debugging "+crs);
                                objective.addSubject(crs);
                              System.out.println("Now the student has ");
                              for(Courses cs: objective.getCourses()){
                                  System.out.println(cs);
                              }
                            }

                        }


                    } else {
                        System.out.println("Invalid Student ID");

                    }
                    break;
                case 3:
                    try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("std.bat"))) {
                        ois.writeObject(Authentication.uniqueStudent);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Saving and Quiting");
                    return;

            }

        }

    }
}
