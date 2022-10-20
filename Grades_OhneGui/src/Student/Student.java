package Student;

import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private String name;
    private final ArrayList<Double> notenListe = new ArrayList<>();

    private String majorShort;
    private String majorLong;

    public String getMajorShort() {
        return majorShort;
    }

    public void setMajorShort(String majorShort) {
        this.majorShort = majorShort;
    }

    public String getMajorLong() {
        return majorLong;
    }

    public void setMajorLong(String majorLong) {
        this.majorLong = majorLong;
    }

    public Student() {

    }

    public void setGrades(Double note) {
        notenListe.add(note);
    }

    public ArrayList<Double> getNoten() {
        return notenListe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Double getDurchschnitt() {
        double x = 0;
        for (Double note : notenListe) {
            x += note;
        }
        removeLeastGrade();
        return x / notenListe.size();
    }

    private void removeLeastGrade() {
        Collections.sort(notenListe);

        notenListe.remove(0);
    }
}
