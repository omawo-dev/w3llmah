package w3llmah.ui.admin.addRing;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import w3llmah.enums.UserType;
import w3llmah.models.Ring;
import w3llmah.models.Teacher;
import w3llmah.models.User;
import w3llmah.services.DB;
import w3llmah.ui.slider.SlidingDrawer;


public class AddRingController implements Initializable {

    @FXML
    private JFXTextField txtRingName;
    @FXML
    private JFXTextField txtTecherName;
    @FXML
    private JFXComboBox<Integer> cbMaxStudentNumber;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private Label lblStudentName;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane pnAnchorPane;
    
    private RequiredFieldValidator required;
    
    ObservableList<Ring> oblist = FXCollections.observableArrayList();
    ObservableList<Integer> MaxStudentNumber = FXCollections.observableArrayList();
    @FXML
    private Label lblCurrentDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loodDataRoomTypeCheak();
        setSlider();
        setValidators();
        lblCurrentDate.setText(DB.getCurrentDate());
    }

    @FXML
    private void Add(ActionEvent event) {
        if(!this.validInputs()) {
            return;
        }
        String ringName = txtRingName.getText();
        String teacherName = txtTecherName.getText();
        System.out.println();
        Integer maxStudentNumber = cbMaxStudentNumber.getValue();

        try {
            User.addUser(teacherName, "password", UserType.TEACHER,teacherName);
            Ring.addRing(ringName, maxStudentNumber);
            Teacher.addTeacher(teacherName,ringName);
            User user = User.getUserByNameOfUser(teacherName);
            this.showRingAddedMsg();
            String message= " Username : "+ user.getUsername() + "  \n  Password : "+user.getPassword();
                JOptionPane.showMessageDialog(null, message , "Information", 3);
        } catch (SQLException ex) {
            int status = ex.getErrorCode();
            if(status == DB.DUPLICATE_ERROR_CODE) {
                this.showRingExistMsg();
            }
        }
    }

    public void loodDataRoomTypeCheak() {
        MaxStudentNumber.removeAll(MaxStudentNumber);
        Integer[] numbers = new Integer[20];
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        MaxStudentNumber.addAll(numbers);
        cbMaxStudentNumber.getItems().addAll(MaxStudentNumber);
    }

    public void setSlider(){
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }
    
    private void showRingAddedMsg() {
        Label label = new Label("تمت إضافة الحلقة بنجاح");
        label.getStyleClass().add("succes-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }
    
    private void showRingExistMsg() {
        Label label = new Label("توجد حلقة مدرس بهذا الاسم");
        label.getStyleClass().add("error-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }
    
    private void setValidators() {
        required = new RequiredFieldValidator("هذا الحقل مطلوب");
        this.cbMaxStudentNumber.setValidators(required);
        this.txtRingName.setValidators(required);
        this.txtTecherName.setValidators(required);
    }
    
    private boolean validInputs() {
        return 
            this.cbMaxStudentNumber.validate()
            & this.txtRingName.validate()
            & this.txtTecherName.validate();
    }
}
