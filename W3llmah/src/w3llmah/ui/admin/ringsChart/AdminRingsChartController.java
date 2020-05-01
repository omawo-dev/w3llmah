/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.admin.ringsChart;

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
import w3llmah.models.Ring;
import w3llmah.services.DB;
import w3llmah.types.TRingManager;
import w3llmah.types.TStudent;
import w3llmah.ui.slider.SlidingDrawer;

/**
 * FXML Controller class
 *
 * @author mapvsnp
 */
public class AdminRingsChartController implements Initializable {

    @FXML
    private Label lblCurrentDate;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private Label TeacherLabel;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private JFXDrawer drawer;
    
    private ObservableList<TRingManager> ringsObvList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSlider();
        barChart.setAnimated(false);
        getRingsCount();
        fetchRingsDataInChart();
        lblCurrentDate.setText(DB.getCurrentDate());
    }
    
    public void setSlider() {
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }
    
    private void getRingsCount() {
        this.ringsObvList = Ring.getStudentCountForRings();
    }
    
    private void fetchRingsDataInChart() {
        this.barChart.getData().clear();
        
        XYChart.Series count = new XYChart.Series();
        count.setName("عدد الطلاب");
        this.ringsObvList.forEach((ri) -> {
            count.getData().add(new XYChart.Data(ri.getRingName(), ri.getStudentCount()));
        });
        this.barChart.getData().addAll(count);
    }
    
}
