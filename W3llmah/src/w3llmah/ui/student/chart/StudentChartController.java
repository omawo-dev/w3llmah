/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.student.chart;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import w3llmah.enums.AssessType;
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
public class StudentChartController implements Initializable {

    @FXML
    private Label lblCurrentDate;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private Label TeacherLabel;
    @FXML
    private JFXComboBox<Integer> cbShowByYear;
    @FXML
    private JFXComboBox<String> cbShowByMonth;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label lblStudentName;
    
    private ObservableList<Integer> year = FXCollections.observableArrayList();

    private Student student ;
    
    private ObservableList<TStudent> studentsObvList;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSlider();
        getStudentInfo();
        getStudentsData();
        barChart.setAnimated(false);
        fetchStudentsDataInChart();
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

    private void fetchStudentsDataInChart() {
        this.barChart.getData().clear();
        
        XYChart.Series lessonSer = new XYChart.Series();
        lessonSer.setName("درس");
        XYChart.Series reviewSer = new XYChart.Series();
        reviewSer.setName("مراجعة");
        this.studentsObvList.forEach((std) -> {
            lessonSer.getData().add(new XYChart.Data(std.getDate(), getAssessmentNumber(std.getLessonAssess())));
            reviewSer.getData().add(new XYChart.Data(std.getDate(), getAssessmentNumber(std.getReviewAssess())));
        });
        this.barChart.getData().addAll(lessonSer, reviewSer);
    }
    
    private Integer getAssessmentNumber(String assess) {
        if(assess == null) {
            return 0;
        }
        AssessType assessType = AssessType.typeOf(assess);
        switch(assessType) {
            case EXELLENT:
                return 5;
            case VERY_GOOD:
                return 4;
            case GOOD:
                return 3;
            case BAD:
                return 1;
            case NOT_EVAL:
                return 0;
        }
        return 0;
    }

    @FXML
    private void changeMonthAction(ActionEvent event) {
        getStudentsData();
        fetchStudentsDataInChart();
    }

    @FXML
    private void changeYearAction(ActionEvent event) {
        getStudentsData();
        fetchStudentsDataInChart();
    }
    
}
