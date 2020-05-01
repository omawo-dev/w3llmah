/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.admin.studentsRing;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSnackbar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import w3llmah.enums.AssessType;
import w3llmah.enums.StudentStatus;
import w3llmah.models.Ring;
import w3llmah.models.Student;
import w3llmah.services.DB;
import w3llmah.services.LocalStorage;
import w3llmah.types.TStudentRing;
import w3llmah.ui.slider.SlidingDrawer;

/**
 * FXML Controller class
 *
 * @author mapvsnp
 */
public class StudentRingController implements Initializable {

    @FXML
    private AnchorPane pnAnchorPane;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private Label nameTeacherLabel;
    @FXML
    private Label TeacherLabel;
    @FXML
    private TableView<TStudentRing> ringsTable;
    @FXML
    private TableColumn<TStudentRing, String> tblcStudentName;
    @FXML
    private TableColumn<TStudentRing, String> tblcStudentState;
    @FXML
    private JFXDrawer drawer;
    
    private ObservableList<TStudentRing> students;
    @FXML
    private Label lblCurrentDate;
    @FXML
    private TableColumn<TStudentRing, String> tblcUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setSlider();
        this.getRingStudetnsData();
        this.fetchRingStudentsDataInTable();
        lblCurrentDate.setText(DB.getCurrentDate());
    }
    
    public void setSlider(){
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }
    
    public void getRingStudetnsData() {
        int ringId = (Integer) LocalStorage.getItem("ringId");
        this.students = Ring.getStudents(ringId);
    }
    
    private void fetchRingStudentsDataInTable() {
        
        this.ringsTable.setItems(this.students);
        this.ringsTable.setEditable(true);
        this.tblcStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        this.tblcUsername.setCellValueFactory(new PropertyValueFactory<>("studentUsername"));
        this.tblcStudentState.setCellValueFactory(new PropertyValueFactory<>("studentStatus"));
        
        this.tblcStudentState.setCellFactory(ComboBoxTableCell.forTableColumn(getComboboxesOptions()));
        
        this.ringsTable.getColumns().setAll(tblcStudentName,tblcUsername  , tblcStudentState);
    }
    
    private void onStudentStateEditCommitMethod(TableColumn.CellEditEvent<TStudentRing, String> event) {
        Integer studentId = event.getRowValue().getStudentId().get();
        StudentStatus status = StudentStatus.typeOf(event.getNewValue());
        Student.updateStudentStatus(status, studentId);
        this.showUpdatedMsg();
    }
    
    private String[] getComboboxesOptions() {
        String[] labels = {
            StudentStatus.ACTIVE.toString(),
            StudentStatus.DEACTIVATED.toString()
        };
        return labels;
    }
    
    private void showUpdatedMsg() {
        Label label = new Label("تم التحديث بنجاح");
        label.getStyleClass().add("succes-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }
    
@FXML
    private void onStudentStateEditCommit(TableColumn.CellEditEvent<TStudentRing, String> event ) {
        onStudentStateEditCommitMethod(event);
        this.getRingStudetnsData();
        this.fetchRingStudentsDataInTable();
    }

    

    
   
    
}
