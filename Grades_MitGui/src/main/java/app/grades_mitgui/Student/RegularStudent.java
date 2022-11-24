package app.grades_mitgui.Student;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

public class RegularStudent extends Student {
    private final ArrayList<Double> gradeList = new ArrayList<>();

    public RegularStudent(ArrayList<Double> gradeList, Double examGrade) {
        super(gradeList, examGrade);
    }

    @Override
    public double getFinaleGrade(Double preGradeFactor) {
        return (getAverage() * preGradeFactor) + (examGrade * (1 - preGradeFactor));
    }

    @Override
    public void setGrades(Double grade) {
        if (grade == null) {
            System.out.println("Grade format is null");
        } else if (grade == 0) {
            System.out.println("Grade cannot be 0");
        }
        gradeList.add(grade);
    }

    private @NotNull Double getAverage() {
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


    public String toString() {
        return getName();
    }
}
