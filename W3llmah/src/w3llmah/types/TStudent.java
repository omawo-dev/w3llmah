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

/**
 *
 * @author 96655
 */
public class TStudent {

    private IntegerProperty lessonId = new SimpleIntegerProperty();
    private StringProperty lesson = new SimpleStringProperty();
    private StringProperty lessonAssess = new SimpleStringProperty();
    private IntegerProperty reviewId = new SimpleIntegerProperty();
    private StringProperty review = new SimpleStringProperty();
    private StringProperty reviewAssess = new SimpleStringProperty();
    private StringProperty date = new SimpleStringProperty();

    public TStudent() {
    }

    public IntegerProperty lessonIdProperty() {
        return lessonId;
    }

    public Integer getLessonId()  {
        return lessonId.get();
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId.set(lessonId);
    }

    public StringProperty lessonProperty() {
        return lesson;
    }

    public String getLesson() {
        return lesson.get();
    }

    public void setLesson(String lesson) {
        this.lesson.set(lesson);
    }

    public StringProperty lessonAssessProperty() {
        return lessonAssess;
    }

    public String getLessonAssess() {
        return lessonAssess.get();
    }

    public void setLessonAssess(String lessonAssess) {
        this.lessonAssess.set(lessonAssess);
    }

    public IntegerProperty reviewIdProperty() {
        return reviewId;
    }

    public Integer getReviewId() {
        return reviewId.get();
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId.set(reviewId);
    }

    public StringProperty reviewProperty() {
        return review;
    }

    public String getReview() {
        return review.get();
    }

    public void setReview(String review) {
        this.review.set(review);
    }

    public StringProperty reviewAssessProperty() {
        return reviewAssess;
    }

    public String getReviewAssess() {
        return reviewAssess.get();
    }

    public void setReviewAssess(String reviewAssess) {
        this.reviewAssess.set(reviewAssess);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    @Override
    public String toString() {
        return "TStudent{" + "lessonId=" + lessonId + ", lesson=" + lesson + ", lessonAssess=" + lessonAssess + ", reviewId=" + reviewId + ", review=" + review + ", reviewAssess=" + reviewAssess + ", date=" + date + '}';
    }

}
