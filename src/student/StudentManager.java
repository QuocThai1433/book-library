package student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    static Connection connection = ConnectDB.getConnection();
    static Scanner scanner = new Scanner(System.in);

//    static student.Student student = new student.Student(); // chỉ có một thằng thôi mà buiur

    static private List<Student> students = new ArrayList<>();

    public static Student inputStudent(Student student) {

        System.out.println("Nhap Id:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhap ho ten:");
        String name = scanner.nextLine();
        System.out.print("Nhap chuyen nganh:");
        String major = scanner.nextLine();
        System.out.print("Nhap Diem Trung Binh:");
        float gradle = scanner.nextFloat();
        return student = new Student(id, name, major, gradle);

    }

    public static int create(Student student) {
          inputStudent(student);
        int kq = 0;
        try {
            String query = "insert into students value (?,?,?,?) ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getMajor());
            ps.setFloat(4, student.getGradle());
            kq = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static int update( Student student) {
         inputStudent(student);
        int kq = 0;
        try {
            String query = "UPDATE students  SET nameStudent = ?, major=?,gradle=? WHERE ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(4, student.getId());
            ps.setString(1, student.getName());
            ps.setString(2, student.getMajor());
            ps.setFloat(3, student.getGradle());
            kq = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static List<Student> getList() {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM students";
        try {

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameStudent = rs.getString("nameStudent");
                String major = rs.getString("major");
                Float gradle = rs.getFloat("gradle");
                Student student = new Student(id, nameStudent, major, gradle);
                studentList.add(student);
                System.out.println(id);
                System.out.println(nameStudent);
                System.out.println(major);
                System.out.println(gradle);

            }
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static List<Student> showListStudent() {
        for (Student student : students) {
            System.out.println("Id: " + student.getId() + " Name: " + student.getName() + " Chuyen Nganh: " + student.getMajor() + " Diem Trung Binh: " + student.getGradle());
        }

        return null;
    }

    public static void filterStudent(String name) {


        for (Student student : students) {
            if (student.getName().equals(name)) {
                System.out.println("Tim thay sinh vien:" + name);
            } else {
                System.out.println("null");
            }

        }


    }

    public static void writeReadFile() {
        //        addStudent();
//        showListStudent();
//        try {
//            FileOutputStream f = new FileOutputStream("student.txt");   // tao student.file f tro den student.txt
//            ObjectOutputStream oStream = new ObjectOutputStream(f); // dung de ghi theo Object vao student.file f
//            oStream.writeObject(students);   // ghi students theo kieu Object vao student.file
//            oStream.close();
//        } catch (IOException e) {
//           e.printStackTrace();
//        }
/// ---------------------------------------------- Read object
//        try (FileInputStream fis = new FileInputStream("student.txt");
//             ObjectInputStream ois = new ObjectInputStream(fis)) {
//
//            // read object from student.file
//            List<student.Student> studentList = (List<student.Student>) ois.readObject();
//            System.out.println("The Object has been read from the student.file");
//            ois.close();
//
//            for (int i = 0; i < studentList.size(); i++) {
//                System.out.println(outputStudent(studentList.get(i)));
//            }

//            for (student.Student s : studentList
//                    ) { System.out.println(outputStudent(s));
//
//
//            }


//        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
    }

    public static void countStudent(Student student)
    {
//        try {
//            String query= "select count(*) as tongsv  from students";
//            PreparedStatement ps = connection.prepareStatement(query);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next())
//            {
//                int count = rs.getInt("tongsv");
//                System.out.println("Tong SV:"+count);
//            }
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        try {
            String query= "select count(*) as tongsv  from students";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                rs.last();
                System.out.println("Tong SV:"+rs.getRow());
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Student s= new Student();
create(s)  ;  }
}
