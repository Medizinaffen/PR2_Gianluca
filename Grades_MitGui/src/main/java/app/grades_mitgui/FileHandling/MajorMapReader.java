package app.grades_mitgui.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MajorMapReader {
    private String filename;
    private final Map<String, String> majorsMap = new HashMap<>();

    public MajorMapReader(String filename) {
        this.getFile(filename);
        this.filename = filename;
    }

    private File getFile(String filename) {
        File majorFile = null;

        try {
            File file = new File("/Users/gianlucafrongia/GitHub/programmieren2/Grades_MitGui/src/main/java/app/grades_mitgui/FileHandling/" + filename);

            if (file.exists()) {
                if (file.length() == 0) {
                    System.out.println("Gradfile is empty");
                }
                majorFile = file;
            }
        } catch (Exception e) {
            System.out.println("Gradefile not found");
            e.printStackTrace();
        }

        return majorFile;
    }

    public Map<String, String> getMajors() throws FileNotFoundException {
        Scanner scannerMajor = new Scanner(this.getFile(this.filename));

        while (scannerMajor.hasNext()) {
            String line = scannerMajor.nextLine();
            String[] tokens = line.split("\\t");
            majorsMap.put(tokens[0], tokens[1]);
        }
        return majorsMap;
    }

}
