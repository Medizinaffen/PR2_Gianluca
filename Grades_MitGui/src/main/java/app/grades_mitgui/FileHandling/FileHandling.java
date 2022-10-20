package app.grades_mitgui.FileHandling;

import java.io.File;


public class FileHandling {
    public FileHandling() {
    }

    public File getFile(String filename) {

        File gradesFile = null;

        try {
            File file = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_MitGui/src/main/java/app/grades_mitgui/FileHandling/"+filename+".txt");

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
}
