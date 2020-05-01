/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.teacher.charts;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import w3llmah.enums.AssessType;
import w3llmah.models.Teacher;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TStudentTeacher;
import w3llmah.ui.slider.SlidingDrawer;

/**
 * FXML Controller class
 *
 * @author mapvsnp
 */
public class TeacherChartController implements Initializable {

    @FXML
    private JFXHamburger hamb;
    @FXML
    private Label nameTeacherLabel;
    @FXML
    private Label TeacherLabel;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private JFXDrawer drawer;

    private ObservableList<TStudentTeacher> studentsObvList;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Label lblCurrentDate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSlider();
        getStudentsData();
        fetchStudentsDataInChart();
        lblCurrentDate.setText(DB.getCurrentDate());
    }
    
    public void setSlider(){
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }
    
    public void getStudentsData() {
        int userId = (Integer) LocalStorage.getItem("userId");
        this.studentsObvList = Teacher.getStudents(userId);
    }
    
    private void fetchStudentsDataInChart() {
        XYChart.Series lessonSer = new XYChart.Series();
        lessonSer.setName("درس");
        XYChart.Series reviewSer = new XYChart.Series();
        reviewSer.setName("مراجعة");
        this.studentsObvList.forEach((std) -> {
             lessonSer.getData().add(new XYChart.Data(std.getStudentName(), getAssessmentNumber(std.getLessonAssess())));
             reviewSer.getData().add(new XYChart.Data(std.getStudentName(), getAssessmentNumber(std.getReviewAssess())));
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
    
}
