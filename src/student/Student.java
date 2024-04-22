package student;

import java.io.Serializable;

public class Student implements Serializable {

    private int id;

    private String name;

    private String major;

    private float gradle;


    public Student() {
    }

    public Student(int id, String name, String major, float gradle) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.gradle = gradle;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getGradle() {
        return gradle;
    }

    public String getMajor() {
        return major;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGradle(float gradle) {
        this.gradle = gradle;
    }

    public void setMajor(String major) {
        this.major = major;
    }


}
