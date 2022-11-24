import Course.Course;
import Student.Student;
import Student.RegularStudent;
import Student.RepeatingStudent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static Course course = new Course();
    private static final Map<String, String> majorsMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        notenEinlesen();
        System.out.println("Grades for:" + course.getName() + " (" + course.getId() + ")");
        System.out.println("-----------------------------------------------------------");
        for (Student student : course.getStudentList()) {
            System.out.println(student + " " + student.getMajor("long") + " : " + student.getFinaleGrade(0.3));
        }
        System.out.println("-----------------------------------------------------------");

    }


    public static void notenEinlesen() throws FileNotFoundException {
        File m = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_OhneGui/src/majors.txt");
        File f = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_OhneGui/src/grades.txt");

        Scanner scannerGrades = new Scanner(f);
        Scanner scannerMajor = new Scanner(m);


        course.setId(scannerGrades.nextLine());
        course.setName(scannerGrades.nextLine());

        // Zuerst werden die Majors in einer Map gespeichert -> majorsMap
        while (scannerMajor.hasNext()) {
            String line = scannerMajor.nextLine();
            String[] tokens = line.split("\\t");
            majorsMap.put(tokens[0], tokens[1]);
        }


        // Die Studenten werden eingelesen
        while (scannerGrades.hasNext()) {
            Student student;
            String line = scannerGrades.nextLine();
            String[] tokens = line.split(",");

            if (line.contains(",r,") || line.contains(", r,")) {
                student = new RepeatingStudent(null, null);

                for (String x : tokens) {
                    String t = x.trim();
                    if (isNumeric(t)) {
                        student.setExamGrade(Double.parseDouble(t));
                    }
                    if (!isNumeric(t)) {
                        if (!t.equals("r") && t.length() >= 3) {
                            student.setName(t);
                        }
                        if (t.length() <= 3 && !t.equals("r")) {
                            student.setMajor(t, majorsMap.get(t));
                        }
                    }

                }
                course.setStudentList(student);
            } else {
                student = new RegularStudent(null, null);

                boolean first = true;
                for (String x : tokens) {
                    String t = x.trim();

                    if (!isNumeric(t) && !t.isEmpty()) {
                        if (t.length() <= 2) {
                            student.setMajor(t, majorsMap.get(t));
                        }
                        if (!t.equals("r") && t.length() >= 3) {
                            student.setName(t);
                        }
                    }
                    if (isNumeric(t)) {
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

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
