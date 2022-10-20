import Course.Course;
import Student.Student;

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
            System.out.println("The average grade for " + student.getName() + " (" +student.getMajorLong()+ ") is: " + student.getDurchschnitt());
        }
        System.out.println("-----------------------------------------------------------");

    }


    public static void notenEinlesen() throws FileNotFoundException {
        File m = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_OhneGui/src/majors.txt");
        File f = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_OhneGui/src/grades.txt");

        Scanner scannerStudenten = new Scanner(f);
        Scanner scannerMajors = new Scanner(m);

        course.setId(scannerStudenten.nextLine());
        course.setName(scannerStudenten.nextLine());

        while (scannerMajors.hasNext()) {
            String line = scannerMajors.nextLine();
            String[] tokens = line.split("\\t");
            majorsMap.put(tokens[0], tokens[1]);
            System.out.println(tokens);
        }

        scannerMajors.close();

        while (scannerStudenten.hasNext()) {
            Student student = new Student();
            String x = scannerStudenten.nextLine();

            String[] tokens = x.split(",");

            for (String t : tokens) {
                // Überprüfen ob der String eine Zahle (Note) enthält
                if (!isNumeric(t)) {
                    // Überprüfen ob der String kleiner als 3 Zeichen ist -> wenn ja -> ist es die Abkürzung des Major
                    if (t.length() <= 3) {
                        // Wir holen den ausgeschrieben Major vom majorsMap, da die variable t der Schlüssel ist
                        String majorLong = majorsMap.get(t.trim());
                        // Mit Trim die leerzeichen entfern.
                        student.setMajorShort(t.trim());
                        student.setMajorLong(majorLong);
                    } else {
                        student.setName(t);
                    }
                }
                if (isNumeric(t)) {
                    student.setGrades(Double.parseDouble(t));
                }
            }

        course.setStudentList(student);
    }

        scannerStudenten.close();

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
