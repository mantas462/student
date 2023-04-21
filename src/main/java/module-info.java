module student.student {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;
    opens student.model;


    opens student to javafx.fxml;
    exports student;
    exports student.util;
    opens student.util to javafx.fxml;
}