package app.grades_mitgui.FileHandling;

import app.grades_mitgui.Course.Course;
import app.grades_mitgui.Student.RegularStudent;
import app.grades_mitgui.Student.RepeatingStudent;
import app.grades_mitgui.Student.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CourseDataReader implements FileHandling {
    private File file;
    public CourseDataReader(File file) {
        this.file = file;
    }

    @Override
    public File getFile(String filename) {
        return FileHandling.super.getFile(filename);
    }

    @Override
    public void readStudent(MajorMapReader majorMapReader, Course course) throws FileNotFoundException {
        Scanner scannerGrades = new Scanner(this.file);

        course.setId(scannerGrades.nextLine());
        course.setName(scannerGrades.nextLine());

        while (scannerGrades.hasNext()) {
            Student student;
            String line = scannerGrades.nextLine();
            String[] tokens = line.split(",");

            if (line.contains(",r,") || line.contains(", r,")) {
                student = new RepeatingStudent(null, null);

                for (String x : tokens) {
                    String t = x.trim();
                    if (FileHandling.isNumeric(t)) {
                        student.setExamGrade(Double.parseDouble(t));
                    }
                    if (!FileHandling.isNumeric(t)) {
                        if (!t.equals("r") && t.length() >= 3) {
                            student.setName(t);
                        }
                        if (t.length() <= 3 && !t.equals("r")) {
                            student.setMajor(t, majorMapReader.getMajors().get(t));
                        }
                    }

                }
                course.setStudentList(student);
            } else {
                student = new RegularStudent(null, null);

                boolean first = true;
                for (String x : tokens) {
                    String t = x.trim();

                    if (!FileHandling.isNumeric(t) && !t.isEmpty()) {
                        if (t.length() <= 2) {
                            student.setMajor(t, majorMapReader.getMajors().get(t));
                        }
                        if (!t.equals("r") && t.length() >= 3) {
                            student.setName(t);
                        }
                    }

                    if (FileHandling.isNumeric(t)) {
                        if (first) {
                            first = false;
                            student.setExamGrade(Double.parseDouble(t));
                        }
                        student.setGrades(Double.parseDouble(t));
                    }
                }
                course.setStudentList(student);
            }
        }
        scannerGrades.close();
    }
}


