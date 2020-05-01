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
 * @author mapvsnp
 */
public class TStudentRing {
    private IntegerProperty studentId = new SimpleIntegerProperty();
    private StringProperty studentName = new SimpleStringProperty();
    private StringProperty studentStatus = new SimpleStringProperty();
    private StringProperty studentUserName = new SimpleStringProperty();

    public TStudentRing() {
    }

    public IntegerProperty getStudentId() {
        return studentId;
    }

    public Integer studentIdProperty() {
        return studentId.get();
    }

    public void setStudentId(Integer studentId) {
        this.studentId.set(studentId);
    }

    public StringProperty studentNameProperty() {
        return studentName;
    }

    public String getStudentName() {
        return studentName.get();
    }

    public void setStudentName(String studentName) {
        this.studentName.set(studentName);
    }

    public StringProperty studentStatusProperty() {
        return studentStatus;
    }

    public String getStudentStatus() {
        return studentStatus.get();
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus.set(studentStatus);
    }

    public StringProperty studentUsernameProperty() {
        return studentUserName;
    }

    public String getStudentUsername() {
        return studentUserName.get();
    }

    public void setStudentUsername(String studentStatus) {
        this.studentUserName.set(studentStatus);
    }
    
    

    @Override
    public String toString() {
        return "TStudentRing{" + "studentId=" + studentId + ", studentName=" + studentName + ", studentState=" + studentStatus + '}';
    }

}
