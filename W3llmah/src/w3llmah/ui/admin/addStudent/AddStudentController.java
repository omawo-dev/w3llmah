package w3llmah.ui.admin.addStudent;

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
import w3llmah.models.RingTeacher;
import w3llmah.models.Student;
import w3llmah.models.Teacher;
import w3llmah.models.User;
import w3llmah.services.DB;
import w3llmah.ui.slider.SlidingDrawer;

public class AddStudentController implements Initializable {

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

    //ObservableList<Ring> oblist = FXCollections.observableArrayList();
    ObservableList<RingTeacher> RingsTeacher;
    @FXML
    private JFXTextField txtStudentName;
    @FXML
    private JFXComboBox<RingTeacher> cbRingsToChoose;
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
        if (!this.validInputs()) {
            return;
        }
        String studentName = txtStudentName.getText();
        System.out.println();
        RingTeacher ringObjectSelected = cbRingsToChoose.getValue();

        
        int ringId = ringObjectSelected.getRingId();
        int MaxStudents = ringObjectSelected.getMaxStudent();
        int currentNumberStudent = Ring.getNumberOfStudent(ringObjectSelected.getRingId());
       
        if (currentNumberStudent < MaxStudents) {
            try {
                User.addUser(studentName, "password", UserType.STUDENT , studentName);
                Student.addNewStudent(studentName, ringId);
                User user = User.getUserByNameOfUser(studentName);
                this.showStudentAddedMsg();
                String message= " Username : "+ user.getUsername() + "  \n  Password : "+user.getPassword();
                JOptionPane.showMessageDialog(null, message , "Information", 3);
            } catch (SQLException ex) {
                int status = ex.getErrorCode();
                if (status == DB.DUPLICATE_ERROR_CODE) {
                    this.showStudentExistMsg();
                }
            }
        } else {
            this.showRingMaxMsg();
        }
    }

    public void loodDataRoomTypeCheak() {
        // RingsTeacher.removeAll(RingsTeacher);

        this.RingsTeacher = Teacher.getRingsTeachersNames();
        cbRingsToChoose.getItems().addAll(RingsTeacher);
    }

    public void setSlider() {
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }

    private void showStudentAddedMsg() {
        Label label = new Label("تمت إضافة الطالب بنجاح");
        label.getStyleClass().add("succes-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }

    private void showStudentExistMsg() {
        Label label = new Label("يوجد طالب بهذا الاسم");
        label.getStyleClass().add("error-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }

    private void showRingMaxMsg() {
        Label label = new Label("الحلقة مكتملة !!");
        label.getStyleClass().add("error-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }

    private void setValidators() {
        required = new RequiredFieldValidator("هذا الحقل مطلوب");
        this.cbRingsToChoose.setValidators(required);
        this.txtStudentName.setValidators(required);

    }

    private boolean validInputs() {
        return this.cbRingsToChoose.validate()
                & this.txtStudentName.validate();
    }
}
