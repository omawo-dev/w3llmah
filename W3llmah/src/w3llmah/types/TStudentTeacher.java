/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.types;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author mapvsnp
 */
public class TStudentTeacher {

    public TStudentTeacher(Integer studentId, String studentName, Integer lessonId, String lesson, String lessonAssess, Integer reviewId, String review, String reviewAssess) {
        this.studentId.set(studentId);
        this.studentName.set(studentName);
        this.lessonId.set(lessonId);
        this.lessonStart.set(lesson);
        this.lessonEnd.set(lesson);
        this.lessonAssess.set(lessonAssess);
        this.reviewId.set(reviewId);
        this.reviewStart.set(review);
        this.reviewEnd.set(review);
        this.reviewAssess.set(reviewAssess);
    }
    
    private IntegerProperty studentId = new SimpleIntegerProperty();
    private StringProperty studentName = new SimpleStringProperty();
    private IntegerProperty lessonId = new SimpleIntegerProperty();
    private StringProperty lessonStart = new SimpleStringProperty();
    private StringProperty lessonEnd = new SimpleStringProperty();
    private StringProperty lessonAssess = new SimpleStringProperty();
    private IntegerProperty reviewId = new SimpleIntegerProperty();
    private StringProperty reviewStart = new SimpleStringProperty();
    private StringProperty reviewEnd = new SimpleStringProperty();
    private StringProperty reviewAssess = new SimpleStringProperty();
    private Button button = new Button();

    public TStudentTeacher() {
    }

    public Integer getStudentId() {
        return studentId.get();
    }

    public void setStudentId(Integer studentId) {
        this.studentId.set(studentId);
    }
    
    public IntegerProperty studentIdProperty() {
        return studentId;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }
    
    public StringProperty studentNameProperty() {
        return studentName;
    }

    public Integer getLessonId() {
        return lessonId.get();
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId.set(lessonId);
    }
    
    public IntegerProperty lessonIdProperty() {
        return lessonId;
    }

    public String getLessonStart() {
        return lessonStart.get();
    }

    public void setLessonStart(String lesson) {
        this.lessonStart.set(lesson);
    }
    
    public StringProperty lessonStartProperty() {
        return lessonStart;
    }
    public String getLessonEnd() {
        return lessonEnd.get();
    }

    public void setLessonEnd(String lesson) {
        this.lessonEnd.set(lesson);
    }
    
    public StringProperty lessonEndProperty() {
        return lessonEnd;
    }
    
    public String getLessonAssess() {
        return lessonAssess.get();
    }

    public void setLessonAssess(String lessonAssess) {
        this.lessonAssess.set(lessonAssess);
    }
    
    public StringProperty lessonAssessProperty() {
        return lessonAssess;
    }

    public Integer getReviewId() {
        return reviewId.get();
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId.set(reviewId);
    }
    
    public IntegerProperty reviewIdProperty() {
        return reviewId;
    }

    public String getReviewStart() {
        return reviewStart.get();
    }

    public void setReviewStart(String review) {
        this.reviewStart.set(review);
    }
    
    public StringProperty reviewStartProperty() {
        return reviewStart;
    }
    
    public String getReviewEnd() {
        return reviewEnd.get();
    }

    public void setReviewEnd(String review) {
        this.reviewEnd.set(review);
    }
    
    public StringProperty reviewEndProperty() {
        return reviewEnd;
    }

    public String getReviewAssess() {
        return reviewAssess.get();
    }

    public void setReviewAsses(String reviewAssess) {
        this.reviewAssess.set(reviewAssess);
    }
    
    public StringProperty reviewAssessProperty() {
        return reviewAssess;
    }

    public Button ButtonProperty() {
        return button;
    }
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button=button;
    }
    
    
    
    
    
    @Override
    public String toString() {
        return "TStudentTeacher{" + "studentId=" + studentId.get() + ", studentName=" + studentName.get() + ", lessonId=" + lessonId.get() + ", lesson=" + lessonStart.get()+"-"+lessonEnd.get() + ", lessonAssess=" + lessonAssess.get() + ", reviewId=" + reviewId.get() + ", review=" + reviewStart.get()+"-"+ reviewEnd.get() + ", reviewAsses=" + reviewAssess.get() + '}';
    }
}
