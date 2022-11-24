package app.grades_mitgui.FileHandling;

import app.grades_mitgui.Course.Course;

import java.io.File;
import java.io.FileNotFoundException;

public class CsvDataReader implements FileHandling {
    @Override
    public File getFile(String filename) {
        return FileHandling.super.getFile(filename);
    }

    @Override
    public void readStudent(MajorMapReader majorMapReader, Course course) throws FileNotFoundException {

    }
}
