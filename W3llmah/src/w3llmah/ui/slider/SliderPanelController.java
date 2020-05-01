/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.slider;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import w3llmah.enums.UserType;
import w3llmah.services.LocalStorage;

/**
 * FXML Controller class
 *
 * @author 96655
 */
public class SliderPanelController implements Initializable {

    @FXML
    private JFXButton btnTable;
    @FXML
    private JFXButton btnChart;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnAddRing;
    @FXML
    private JFXButton btnAddStudent;
    @FXML
    private JFXButton btnRingsTable;
    @FXML
    private JFXButton btnStudentDash;
    @FXML
    private JFXButton btnStudentChart;
    @FXML
    private JFXButton btnRingsCountChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setVisibility();
    }    

    @FXML
    private void onTableBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("teacherDashboard");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onChartBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("teacherChart");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onLogoutBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("login");
            LocalStorage.clear();
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void onAddRingBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("adminAddRing");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onAddStudentBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("adminAddStudent");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onRingsTableBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("adminRings");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onStudentDashBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("studentDashboard");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onStudentChartBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("studentChart");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void onRingsCountChartBtnClick(ActionEvent event) {
        try {
            FXRouter.goTo("adminRingsChart");
        } catch (IOException ex) {
            Logger.getLogger(SliderPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setVisibility() {
        
        this.btnChart.setVisible(false);
        this.btnTable.setVisible(false);
        this.btnAddRing.setVisible(false);
        this.btnAddStudent.setVisible(false);
        this.btnRingsTable.setVisible(false);
        this.btnStudentDash.setVisible(false);
        this.btnStudentChart.setVisible(false);
        this.btnRingsCountChart.setVisible(false);
        
        UserType type = UserType.valueOf((String) LocalStorage.getItem("userType"));
        if(type == UserType.TEACHER) {
            this.btnChart.setVisible(true);
            this.btnTable.setVisible(true);
        }
        if(type == UserType.ADMIN){
            this.btnAddRing.setVisible(true);
            this.btnAddStudent.setVisible(true);
            this.btnRingsTable.setVisible(true);
            this.btnRingsCountChart.setVisible(true);
        }
        if(type == UserType.STUDENT){
            this.btnStudentDash.setVisible(true);
            this.btnStudentChart.setVisible(true);
        }
    }

    
}
