package student;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import student.model.Student;
import student.model.StudentWithSortTime;
import student.util.SortMethodFactory;
import student.util.SortType;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static student.util.SortType.*;


public class StudentApplication extends Application {

    private static final String NUM_REGEX = "-?((([0-9]{1,3})(,[0-9]{3})*)|[0-9]*)(\\.[0-9]+)?([Ee][0-9]*)?";

    @Override
    public void start(Stage stage) {

        createMainWindow(Collections.emptyList(), stage, RANDOM);
    }

    private void createMainWindow(List<Student> studentList, Stage stage, SortType sortType) {

        Scene scene = createScene(stage);

        VBox fileChooserForOpenVbox = createFileChooserForOpenVbox(stage);
        VBox fileChooserForSaveVbox = createFileChooserForSaveVbox(stage, studentList);
        VBox tableVbox = createTableVbox(studentList);
        VBox comboboxVbox = createComboBoxVbox(studentList, sortType, stage);

        ((Group) scene.getRoot()).getChildren().addAll(tableVbox, fileChooserForOpenVbox, fileChooserForSaveVbox, comboboxVbox);

        stage.setScene(scene);
        stage.show();
    }

    private VBox createComboBoxVbox(List<Student> studentList, SortType sortType, Stage stage) {

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().add(RANDOM);
        comboBox.getItems().add(BUBBLE);
        comboBox.getItems().add(HEAP);
        comboBox.getItems().add(MERGE);
        comboBox.getSelectionModel().select(sortType);
        comboBox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> executeSorting((SortType) newValue, studentList, stage));

        VBox vBox = new VBox(comboBox);
        vBox.setPadding(new Insets(5, 0, 0, 10));

        return vBox;
    }

    private void executeSorting(SortType sortType, List<Student> studentList, Stage stage) {

        SortMethodFactory sortMethodFactory = new SortMethodFactory();
        StudentWithSortTime studentWithSortTime = sortMethodFactory.sort(studentList, sortType);

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Algorithm sorting time in ns: " + studentWithSortTime.getSortTimeInNs(), ButtonType.CLOSE);
        alert.showAndWait();

        createMainWindow(studentWithSortTime.getStudentList(), stage, sortType);
    }

    private VBox createTableVbox(List<Student> studentList) {

        TableView table = createTable(studentList);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(50, 0, 0, 10));
        vbox.getChildren().add(table);

        return vbox;
    }

    private TableView createTable(List<Student> studentList) {

        TableView table = new TableView();

        TableColumn<Student, String> nameCol = new TableColumn("Student");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setSortable(false);

        TableColumn gradeCol = new TableColumn("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
        gradeCol.setSortable(false);

        table.getColumns().addAll(nameCol, gradeCol);
        table.getItems().addAll(studentList);

        return table;
    }

    private Scene createScene(Stage stage) {

        Scene scene = new Scene(new Group());
        stage.setTitle("Students sorting program");
        stage.setWidth(300);
        stage.setHeight(500);

        return scene;
    }

    private VBox createFileChooserForOpenVbox(Stage stage) {

        Button button = new Button("Select File");
        button.setOnAction(e -> executeFileImport(stage));

        VBox vBox = new VBox(button);
        vBox.setPadding(new Insets(5, 0, 5, 190));

        return vBox;
    }

    private VBox createFileChooserForSaveVbox(Stage stage, List<Student> studentList) {

        Button button = new Button("Save file");
        button.setOnAction(e -> executeFileSave(stage, studentList));

        VBox vBox = new VBox(button);
        vBox.setPadding(new Insets(5, 0, 5, 115));

        return vBox;
    }

    private void executeFileSave(Stage stage, List<Student> studentList) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showSaveDialog(stage);

        if (selectedFile == null) {
            return;
        }

        executeFileSave(studentList, selectedFile);
    }

    private void executeFileSave(List<Student> studentList, File file) {

        try {
            String fileContent = createFileContent(studentList);

            PrintWriter writer = new PrintWriter(file);
            writer.println(fileContent);
            writer.close();

        } catch (IOException ex) {
        }
    }

    private String createFileContent(List<Student> studentList) {

        StringBuilder content = new StringBuilder();
        int listSize = studentList.size();

        for (int i = 0; i < listSize; i++) {

            Student student = studentList.get(i);
            content.append(student.getName()).append(",").append(student.getGrade());

            if (i != listSize - 1) {
                content.append("\n");
            }
        }

        return content.toString();
    }

    private void executeFileImport(Stage stage) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            return;
        }

        try (BufferedReader input = new BufferedReader(new FileReader(selectedFile))) {
            List<Student> studentList = extractStudentList(input);
            createMainWindow(studentList, stage, RANDOM);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<Student> extractStudentList(BufferedReader input) throws IOException {

        List<Student> studentList = new ArrayList<>();
        try {

            for (String line = input.readLine(); line != null; line = input.readLine()) {
                Student student = validateAndGet(line);
                studentList.add(student);
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE);
            alert.showAndWait();
        }
        return studentList;
    }

    private Student validateAndGet(String line) {

        if (StringUtils.countMatches(line, ",") != 1) {
            throw new IllegalArgumentException("Expected to have one comma in the line");
        }

        String[] parts = line.split(",");
        String namePart = parts[0].trim();
        String gradePart = parts[1].trim();

        if (namePart.isBlank() || gradePart.isBlank()) {
            throw new IllegalArgumentException("Expected to have not empty strings between comma");
        }

        if (!gradePart.matches(NUM_REGEX)) {
            throw new IllegalArgumentException("Grade part is not numeric value");
        }

        BigDecimal grade = new BigDecimal(gradePart);

        return Student.builder()
                .name(namePart)
                .grade(grade)
                .build();
    }

    public static void main(String[] args) {
        launch();
    }
}