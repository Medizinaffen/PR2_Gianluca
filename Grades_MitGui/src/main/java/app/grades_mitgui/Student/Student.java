package app.grades_mitgui.Student;

import java.util.ArrayList;

public class Student {
    private String name;

    public Double examGrade;
    private String majorShort;
    private String majorLong;

    public Student(ArrayList<Double> gradeList, Double examGrade) {
    }

    public double getFinaleGrade(Double preGradeFactor) {
        return 0;
    }

    public void setGrades(Double grade) {
    }

    public String getMajor(String length) {
        if (length.equals("short")) {
            return majorShort;
        }
        return majorLong;
    }

    public void setMajor(String majorShort, String majorLong) {
        this.majorShort = majorShort;
        this.majorLong = majorLong;
    }

    public void setExamGrade(Double examGrade) {
        this.examGrade = examGrade;
    }

    public void setName(String name) {
        if (name == null) {
            System.out.println("Name is null");
        } else if (name.isEmpty()) {
            System.out.println("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
