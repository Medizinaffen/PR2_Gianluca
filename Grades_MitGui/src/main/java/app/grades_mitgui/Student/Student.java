package app.grades_mitgui.Student;

import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private String name;
    private final ArrayList<Double> gradeList = new ArrayList<>();

    private String majorShort;
    private String majorLong;

    public void setGrades(Double grade) {
        if (grade == null) {
            System.out.println("Grade format is null");
        } else if (grade == 0) {
            System.out.println("Grade cannot be 0");
        }
        gradeList.add(grade);
    }


    public ArrayList<Double> getGrades() {
        return gradeList;
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

    public Double getAverage() {
        double total = 0;

        removeLeastGrade();

        for (Double note : gradeList) {
            total += note;
        }

        return total / gradeList.size();
    }

    private void removeLeastGrade() {
        Collections.sort(gradeList);
        gradeList.remove(0);
    }
}
