package app.grades_mitgui.FileHandling;

import app.grades_mitgui.Course.Course;

import java.io.File;
import java.io.FileNotFoundException;


public interface FileHandling {

    default File getFile(String filename) {

        File gradesFile = null;

        try {
            File file = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_MitGui/src/main/java/app/grades_mitgui/FileHandling/" + filename);

            if (file.exists()) {
                if (file.length() == 0) {
                    System.out.println("Gradfile is empty");
                }
                gradesFile = file;
            }
        } catch (Exception e) {
            System.out.println("Gradefile not found");
            e.printStackTrace();
        }

        return gradesFile;
    }

    void readStudent(MajorMapReader majorMapReader, Course course) throws FileNotFoundException;

    static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
