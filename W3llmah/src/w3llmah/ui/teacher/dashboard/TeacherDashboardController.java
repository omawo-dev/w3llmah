/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.teacher.dashboard;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXSnackbar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import w3llmah.enums.AssessType;
import w3llmah.models.Lesson;
import w3llmah.models.Review;
import w3llmah.models.Ring;
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
public class TeacherDashboardController implements Initializable {

    @FXML
    private AnchorPane pnAnchorPane;
    @FXML
    private JFXHamburger hamb;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private TableView<TStudentTeacher> studentTable;

    private ObservableList<TStudentTeacher> studentsObvList;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcStudentName;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcLesson;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcLessonAssess;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcReview;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcReviewAssess;

    private Teacher teacher;
    private Ring ring;

    @FXML
    private TableColumn<TStudentTeacher, Button> tblcNextRecite;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcLessonStart;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcLessonEnd;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcReviewStart;
    @FXML
    private TableColumn<TStudentTeacher, String> tblcReviewEnd;
    @FXML
    private Label lblRingName;
    @FXML
    private Label lblTeacherName;
    @FXML
    private Label lblNumberOfStudent;
    @FXML
    private Label lblCurrentDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.init();
        this.setRingInfo();
        this.setTeacherInfo();
    }

    public void init() {
        setSlider();
        getTeacherInfo();
        getStudentsData();
        getRingInfo();
        fetchStudentsDataInTable();
        lblCurrentDate.setText(" " + DB.getCurrentDate());
    }

    public void setSlider() {
        SlidingDrawer sd = new SlidingDrawer();
        sd.setDrawer(drawer);
        sd.setHamb(hamb);
        sd.slider();
    }

    @FXML
    private void onLessonAssessCommit(TableColumn.CellEditEvent<TStudentTeacher, String> event) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(event.getRowValue().getLessonId());
        lesson.setAssessment(event.getNewValue());
        lesson.setStart(event.getRowValue().getLessonStart());
        lesson.setEnd(event.getRowValue().getLessonEnd());
        Lesson.updateLesson(lesson);
        this.showUpdatedMsg();
        this.init();
    }

    @FXML
    private void onLessonStartCommit(TableColumn.CellEditEvent<TStudentTeacher, String> event) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(event.getRowValue().getLessonId());
        lesson.setAssessment(event.getRowValue().getLessonAssess());
        lesson.setStart(event.getNewValue());
        lesson.setEnd(event.getRowValue().getLessonEnd());
        Lesson.updateLesson(lesson);
        this.showUpdatedMsg();
        this.init();
    }

    @FXML
    private void onLessonEndCommit(TableColumn.CellEditEvent<TStudentTeacher, String> event) {
        Lesson lesson = new Lesson();
        lesson.setLessonId(event.getRowValue().getLessonId());
        lesson.setAssessment(event.getRowValue().getLessonAssess());
        lesson.setStart(event.getRowValue().getLessonStart());
        lesson.setEnd(event.getNewValue());
        Lesson.updateLesson(lesson);
        this.showUpdatedMsg();
        this.init();
    }

    @FXML
    private void onReviewAssessCommit(TableColumn.CellEditEvent<TStudentTeacher, String> event) {
        Review review = new Review();
        review.setReviewId(event.getRowValue().getReviewId());
        review.setAssessment(event.getNewValue());
        review.setStart(event.getRowValue().getReviewStart());
        review.setEnd(event.getRowValue().getReviewEnd());
        Review.updateReview(review);
        this.showUpdatedMsg();
        this.init();
    }

    @FXML
    private void onReviewStartCommit(TableColumn.CellEditEvent<TStudentTeacher, String> event) {
        Review review = new Review();
        review.setReviewId(event.getRowValue().getReviewId());
        review.setAssessment(event.getRowValue().getLessonAssess());
        review.setStart(event.getNewValue());
        review.setEnd(event.getRowValue().getReviewEnd());
        Review.updateReview(review);
        this.showUpdatedMsg();
        this.init();
    }

    @FXML
    private void onReviewEndCommit(TableColumn.CellEditEvent<TStudentTeacher, String> event) {
        Review review = new Review();
        review.setReviewId(event.getRowValue().getReviewId());
        review.setAssessment(event.getRowValue().getLessonAssess());
        review.setStart(event.getRowValue().getReviewStart());
        review.setEnd(event.getNewValue());
        Review.updateReview(review);
        this.showUpdatedMsg();
        this.init();
    }

    public void getStudentsData() {
        int userId = (Integer) LocalStorage.getItem("userId");
        this.studentsObvList = Teacher.getStudents(userId, this);
    }

    private void fetchStudentsDataInTable() {

        this.studentTable.setItems(this.studentsObvList);
        this.studentTable.setEditable(true);
        this.tblcStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        this.tblcLesson.setCellValueFactory(new PropertyValueFactory<>("lesson"));
        this.tblcLessonStart.setCellValueFactory(new PropertyValueFactory<>("lessonStart"));
        this.tblcLessonEnd.setCellValueFactory(new PropertyValueFactory<>("lessonEnd"));
        this.tblcLessonAssess.setCellValueFactory(new PropertyValueFactory<>("lessonAssess"));
        this.tblcReview.setCellValueFactory(new PropertyValueFactory<>("review"));
        this.tblcReviewStart.setCellValueFactory(new PropertyValueFactory<>("reviewStart"));
        this.tblcReviewEnd.setCellValueFactory(new PropertyValueFactory<>("reviewEnd"));
        this.tblcReviewAssess.setCellValueFactory(new PropertyValueFactory<>("reviewAssess"));
        this.tblcNextRecite.setCellValueFactory(new PropertyValueFactory<>("button"));

        //this.tblcStudentName.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tblcLessonStart.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tblcLessonEnd.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tblcLessonAssess.setCellFactory(ComboBoxTableCell.forTableColumn(getComboboxesOptions()));
        this.tblcReviewStart.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tblcReviewEnd.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tblcReviewAssess.setCellFactory(ComboBoxTableCell.forTableColumn(getComboboxesOptions()));

        this.studentTable.getColumns().setAll(tblcStudentName, tblcLessonStart, tblcLessonEnd, tblcLessonAssess, tblcReviewStart, tblcReviewEnd, tblcReviewAssess, tblcNextRecite);
    }

    private void getTeacherInfo() {
        int userId = (Integer) LocalStorage.getItem("userId");
        this.teacher = Teacher.getTeacherByUserId(userId);

        if (teacher == null) {
            return;
        }
    }

    private void setTeacherInfo() {

        if (teacher == null) {
            return;
        }
        this.lblTeacherName.setText(lblTeacherName.getText() + teacher.getName());

    }

    private void getRingInfo() {

        this.ring = Ring.getRingByTeacherId(this.teacher.getTeacherId());

        if (ring == null) {
            return;
        }
    }

    private void setRingInfo() {

        if (ring == null) {
            return;
        }
        String ringName = lblRingName.getText() + " : " + this.ring.getName();
        String numberOfStudent = lblNumberOfStudent.getText() + Ring.getNumberOfStudent(ring.getRingId()) + " / " + ring.getMaxStudentNumber();

        this.lblRingName.setText(ringName);
        this.lblNumberOfStudent.setText(numberOfStudent);

    }

    private String[] getComboboxesOptions() {
        String[] labels = {
            AssessType.EXELLENT.toString(),
            AssessType.VERY_GOOD.toString(),
            AssessType.GOOD.toString(),
            AssessType.BAD.toString(),
            AssessType.NOT_EVAL.toString(),};
        return labels;
    }

    public void showStudentAddedMsg() {
        Label label = new Label("تمت إضافة الدرس والمراجعة بنجاح");
        label.getStyleClass().add("succes-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }

    private void showUpdatedMsg() {
        Label label = new Label("تم التحديث بنجاح");
        label.getStyleClass().add("succes-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }

}
