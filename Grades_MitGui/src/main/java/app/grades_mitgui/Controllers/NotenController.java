package app.grades_mitgui.Controllers;

import app.grades_mitgui.Course.Course;
import app.grades_mitgui.FileHandling.FileHandling;
import app.grades_mitgui.Student.Student;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NotenController {
    private static final Map<String, String> majorsMap = new HashMap<>();

    private static Course course = new Course();

    @FXML
    private ListView<String> listViewStudent;
    @FXML
    private BarChart<String, Number> gradeChart;


    @FXML
    protected void populateListView() {
        for (Student student : course.getStudentList()) {
            listViewStudent.getItems().add(student.getName() + " " + student.getMajor("long") + " : " + student.getAverage());
        }
    }

    @FXML
    protected void populateGradeChart() {
        for (Student student : course.getStudentList()) {
            XYChart.Series<String, Number> series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(student.getName(), student.getAverage()));
            gradeChart.getData().add(series1);
        }
    }

    @FXML
    public void onLoadData() {
        try {
            readData();
            if (System.getenv("VAR").equals("fx")) {
                populateListView();
                populateGradeChart();
            }
            if (System.getenv("VAR").equals("java")) {
                for (Student student : course.getStudentList()) {
                    System.out.println(student.getName() + " " + student.getMajor("long") + " : " + student.getAverage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    private static void readData() throws FileNotFoundException {
        // Instanzieren von der Klasse File
        FileHandling fileHandler = new FileHandling();
        // Die Files dem Scanner übergeben. Ein Scanner für den File grades und eines für die majors
        Scanner scannerGrades = new Scanner(fileHandler.getFile("grades"));
        Scanner scannerMajor = new Scanner(fileHandler.getFile("major-map"));

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
            Student student = new Student();

            String line = scannerGrades.nextLine();

            String[] tokens = line.split(",");

            for (String t : tokens) {
                // Überprüfen ob der String eine Zahle (Note) enthält
                if (!isNumeric(t)) {
                    // Überprüfen ob der String kleiner als 3 Zeichen ist -> wenn ja -> ist es die Abkürzung des Major
                    if (t.length() <= 3) {
                        // Mit Trim die leerzeichen entfern. Mit setMajor speichere ich die Abkürzung und den vollen Namen des Major.
                        student.setMajor(t.trim(), majorsMap.get(t.trim()));
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