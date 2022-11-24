package Student;

import java.util.ArrayList;

public class RepeatingStudent extends Student {
    public RepeatingStudent(ArrayList<Double> gradeList, Double examGrade) {
        super(gradeList, examGrade);
    }

    @Override
    public double getFinaleGrade(Double preGradeFactor) {
        return examGrade;
    }

    public String toString() {
        return getName() + "*";
    }

}
