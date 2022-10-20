package Course;

import Student.Student;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String name;
    private List<Student> studentList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(Student student) {
        this.studentList.add(student);
    }
}
