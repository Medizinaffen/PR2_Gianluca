module app.grades_mitgui {
    requires javafx.controls;
    requires javafx.fxml;

    opens app.grades_mitgui.Controllers to javafx.fxml;
    opens app.grades_mitgui to javafx.fxml;
    exports app.grades_mitgui;
}