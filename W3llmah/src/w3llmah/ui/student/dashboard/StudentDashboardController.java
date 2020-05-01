/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.student.dashboard;

import com.jfoenix.controls.JFXComboBox;
import w3llmah.ui.teacher.dashboard.*;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import w3llmah.models.Student;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TStudent;
import w3llmah.ui.slider.SlidingDrawer;

/**
 * FXML Controller class
 *
 * @author mapvsnp
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private AnchorPane pnAnchorPane;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label TeacherLabel;
    @FXML
    private TableView<TStudent> studentTable;

    private ObservableList<TStudent> studentsObvList;
    @FXML
    private TableColumn<TStudent, String> tblcLesson;
    @FXML
    private TableColumn<TStudent, String> tblcLessonAssess;
    @FXML
    private TableColumn<TStudent, String> tblcReview;
    @FXML
    private TableColumn<TStudent, String> tblcReviewAssess;
    @FXML
    private Label recitingToDay;
    @FXML
    private TableColumn<TStudent, String> tblcDate;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXComboBox<Integer> cbShowByYear;
    @FXML
    private JFXComboBox<String> cbShowByMonth;
    
     ObservableList<Integer> year = FXCollections.observableArrayList();

     Student student ;
    @FXML
    private Label lblCurrentDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setSlider();
        getStudentInfo();
        getStudentsData();
        fetchStudentsDataInTable();
                currentReciting();
        setMonth();
        setYear();
        lblCurrentDate.setText(DB.getCurrentDate());

    }

    public void setSlider() {
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }

    public void setMonth() {
        ObservableList<String> months
                = FXCollections.observableArrayList(
                        "يناير",
                        "فبراير",
                        "مارس",
                        "أبريل",
                        "مايو",
                        "يونيو",
                        "يوليو",
                        "أغسطس",
                        "سبتمبر",
                        "أكتوبر",
                        "نوفمبر",
                        "ديسمبر");
        cbShowByMonth.getItems().addAll(months);

    }

    public int getMonth() {
        if (cbShowByMonth.getValue() == null) {
            cbShowByMonth.setPromptText("أبريل");
            return 4;
        } else {
            return cbShowByMonth.getSelectionModel().getSelectedIndex() + 1;
        }

    }
    
    public void setYear() {
        Integer[] numbers = new Integer[33];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = i+1997;
        }
        year.addAll(numbers);
        cbShowByYear.getItems().addAll(year);

    }

    public int getYear() {
        if (cbShowByYear.getValue() == null) {
            cbShowByYear.setPromptText("2020");
            return 2020;
        } else {
            System.out.println("Y"+cbShowByYear.getSelectionModel().getSelectedItem().toString());
            return Integer.parseInt(cbShowByYear.getSelectionModel().getSelectedItem().toString());
        }

    }
    
    

    public void getStudentsData() {
        System.out.println("month :" + getMonth());
        System.out.println("year :" + getYear());
        
        this.studentsObvList = Student.getStudentReport(student.getUserId(), getMonth() , getYear() );

    }

    public void getStudentInfo() {
        int userId = (Integer) LocalStorage.getItem("userId");
        student = Student.getStudent(userId);
        System.out.println("stuName : " + student.getName());
        lblStudentName.setText(lblStudentName.getText() + student.getName());
    }

    public void currentReciting() {
        int userId = (Integer) LocalStorage.getItem("userId");
        recitingToDay.setText(Student.currentReciting(userId));
    }

    private void fetchStudentsDataInTable() {
        this.studentTable.setItems(this.studentsObvList);
        this.studentTable.setEditable(true);

        this.tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.tblcLesson.setCellValueFactory(new PropertyValueFactory<>("lesson"));
        this.tblcLessonAssess.setCellValueFactory(new PropertyValueFactory<>("lessonAssess"));
        this.tblcReview.setCellValueFactory(new PropertyValueFactory<>("review"));
        this.tblcReviewAssess.setCellValueFactory(new PropertyValueFactory<>("reviewAssess"));

//        this.tblcDate.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.tblcLesson.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.tblcLessonAssess.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.tblcReview.setCellFactory(TextFieldTableCell.forTableColumn());
//        this.tblcReviewAssess.setCellFactory(TextFieldTableCell.forTableColumn());

        this.studentTable.getColumns().setAll(tblcDate, tblcLesson, tblcLessonAssess, tblcReview, tblcReviewAssess);
    }

    @FXML
    private void changeMonthAction(ActionEvent event) {

        getStudentsData();
        fetchStudentsDataInTable();
    }

    @FXML
    private void changeYearAction(ActionEvent event) {
        getStudentsData();
        fetchStudentsDataInTable();
    }

}
