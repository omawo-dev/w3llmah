/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.teacher.AddNewLessonAndReview;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javax.swing.JOptionPane;
import w3llmah.models.Lesson;
import w3llmah.models.Review;
import w3llmah.services.LocalStorage;
import w3llmah.ui.teacher.dashboard.TeacherDashboardController;

/**
 * FXML Controller class
 *
 * @author 96655
 */
public class AddNextReciteController implements Initializable {

    @FXML
    private JFXButton btnAddLesAndRev;
    @FXML
    private JFXTextField txtNextEndLesson;
    @FXML
    private JFXTextField txtNextEndReview;
    @FXML
    private JFXTextField txtNextStartReview;
    @FXML
    private JFXTextField txtNextStartLesson;
    @FXML
    private Label lbltitle;
    
    //  validators   //
    private RequiredFieldValidator required;
    
    TeacherDashboardController teacherController ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String studentData  = (String) LocalStorage.getItem("studentIdBtn");
        int hasLesson = Integer.parseInt(studentData.charAt(0)+"");
        if(hasLesson == 1){
        lbltitle.setText(lbltitle.getText()+" : 'درس الغد'");
        }else if ( hasLesson == 0 ){
            lbltitle.setText(lbltitle.getText()+" : 'درس اليوم'");
        }
        required = new RequiredFieldValidator("هذا الحقل مطلوب");
        this.setValidators();
    }    

    @FXML
    private void Add(ActionEvent event) {
        if(!this.validInputs()) {
            return;
        }
        AddLesson();
        AddReview();
        teacherController.init();
        teacherController.showStudentAddedMsg();
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    
    public void AddLesson(){
        String startLesson = txtNextStartLesson.getText();
        String endLesson = txtNextEndLesson.getText();
        String studentData  = (String) LocalStorage.getItem("studentIdBtn");
        int studentId = Integer.parseInt(studentData.substring(1)) ;
        int hasLesson = Integer.parseInt(studentData.charAt(0)+"");
        try {
            Lesson.addNextLesson(studentId, startLesson, endLesson , hasLesson);
        } catch (SQLException ex) {
            Logger.getLogger(AddNextReciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public void AddReview(){
        String startReview = txtNextStartReview.getText();
        String endReview = txtNextEndReview.getText();
        String studentData  = (String) LocalStorage.getItem("studentIdBtn");
        int studentId = Integer.parseInt(studentData.substring(1)) ;
        int hasLesson = Integer.parseInt(studentData.charAt(0)+"");
        try {
            Review.addNextReview(studentId, startReview, endReview , hasLesson);
        } catch (SQLException ex) {
            Logger.getLogger(AddNextReciteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setTeacherDashboard(TeacherDashboardController tController){
        
        this.teacherController = tController;
    }
    
    private boolean validInputs() {
        return 
            this.txtNextEndLesson.validate()
            & this.txtNextEndReview.validate()
            & this.txtNextStartLesson.validate()
            & this.txtNextStartReview.validate();
    }
    
    private void setValidators() {
        this.txtNextEndLesson.setValidators(required);
        this.txtNextEndReview.setValidators(required);
        this.txtNextStartLesson.setValidators(required);
        this.txtNextStartReview.setValidators(required);
    }
}
