/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.admin.rings;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import w3llmah.models.School;
import w3llmah.models.Teacher;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TRingManager;
import w3llmah.ui.slider.SlidingDrawer;

/**
 * FXML Controller class
 *
 * @author mapvsnp
 */
public class RingsController implements Initializable {

    @FXML
    private AnchorPane pnAnchorPane;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private Label nameTeacherLabel;
    @FXML
    private Label TeacherLabel;
    @FXML
    private TableView<TRingManager> ringsTable;
    @FXML
    private TableColumn<TRingManager, String> tblcRingName;
    @FXML
    private TableColumn<TRingManager, String> tblcTeacherName;
    @FXML
    private TableColumn<TRingManager, Button> tblcRingButton;
    @FXML
    private JFXDrawer drawer;
    
    ObservableList<TRingManager> rings;
    @FXML
    private Label lblCurrentDate;
    @FXML
    private TableColumn<TRingManager, Button> tblcTeacherUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSlider();
        this.getRingsData();
        this.fetchRingsDataInTable();
        lblCurrentDate.setText(DB.getCurrentDate());
    }
    
    public void setSlider(){
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }
    
    public void getRingsData() {
        int userId = (Integer) LocalStorage.getItem("userId");
        this.rings = School.getRings(userId);
    }
    
    private void fetchRingsDataInTable() {
        
        
        this.ringsTable.setItems(this.rings);
        this.ringsTable.setEditable(true);
        this.tblcRingName.setCellValueFactory(new PropertyValueFactory<>("ringName"));
        this.tblcTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        this.tblcTeacherUsername.setCellValueFactory(new PropertyValueFactory<>("teacherUsername"));
        this.tblcRingButton.setCellValueFactory(new PropertyValueFactory<>("button"));
        
        this.ringsTable.getColumns().setAll(tblcRingName, tblcTeacherName, tblcTeacherUsername , tblcRingButton);
    }
    
}
