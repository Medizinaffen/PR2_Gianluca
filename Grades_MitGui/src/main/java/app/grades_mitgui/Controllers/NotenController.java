package app.grades_mitgui.Controllers;

import app.grades_mitgui.Course.Course;
import app.grades_mitgui.FileHandling.CourseDataReader;
import app.grades_mitgui.FileHandling.MajorMapReader;
import app.grades_mitgui.Student.Student;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class NotenController {

    private static final Course course = new Course();

    @FXML
    private ListView<String> listViewStudent;
    @FXML
    private BarChart<String, Number> gradeChart;


    @FXML
    protected void populateListView() {
        for (Student student : course.getStudentList()) {
            listViewStudent.getItems().add(student.getName() + " " + student.getMajor("long") + " : " + student.getFinaleGrade(0.3));
        }
    }

    @FXML
    protected void populateGradeChart() {
        for (Student student : course.getStudentList()) {
            XYChart.Series<String, Number> series1 = new XYChart.Series();
            series1.getData().add(new XYChart.Data(student.getName(), student.getFinaleGrade(0.3)));
            gradeChart.getData().add(series1);
        }
    }

    @FXML
    public void onLoadData() {
        try {
            FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if(selectedFile.exists()) {
                readData(selectedFile);
                populateListView();
                populateGradeChart();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    private static void readData(File file) throws FileNotFoundException {
        MajorMapReader majorMapReader = new MajorMapReader("major-map.txt");
        String fileName = file.toString();
        int index = fileName.lastIndexOf('.');
        if(index > 0) {
            String extension = fileName.substring(index + 1);
            if(extension.contains("csv")){
                CourseDataReader courseDataReader = new CourseDataReader(file);
                courseDataReader.readStudent(majorMapReader, course);
            } else if (extension.contains("txt")) {

            }
        }
    }
}