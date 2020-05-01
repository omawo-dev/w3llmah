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
public class TRingManager {
    public TRingManager(Integer ringId, Integer schoolId, Integer managerId, Integer teacherId, Integer studentCount, String ringName, String teacherName) {
        this.ringId.set(ringId);
        this.schoolId.set(schoolId);
        this.managerId.set(managerId);
        this.teacherId.set(teacherId);
        this.studentCount.set(studentCount);
        this.ringName.set(ringName);
        this.teacherName.set(teacherName);
    }
    
    private IntegerProperty ringId = new SimpleIntegerProperty();
    private IntegerProperty schoolId = new SimpleIntegerProperty();
    private IntegerProperty managerId = new SimpleIntegerProperty();
    private IntegerProperty teacherId = new SimpleIntegerProperty();
    private IntegerProperty studentCount = new SimpleIntegerProperty();
    private StringProperty ringName = new SimpleStringProperty();
    private StringProperty teacherName = new SimpleStringProperty();
    private StringProperty teacherUsername = new SimpleStringProperty();
    private Button button = new Button();

    public TRingManager() {
    }

    public Integer getRingId() {
        return ringId.get();
    }

    public void setRingId(Integer ringId) {
        this.ringId.set(ringId);
    }
    
    public IntegerProperty ringIdProperty() {
        return ringId;
    }
    
    public Integer getSchoolId() {
        return schoolId.get();
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId.set(schoolId);
    }
    
    public IntegerProperty schoolIdProperty() {
        return schoolId;
    }
    
    public Integer getManagerId() {
        return managerId.get();
    }

    public void setManagerId(Integer managerId) {
        this.managerId.set(managerId);
    }
    
    public IntegerProperty managerIdProperty() {
        return managerId;
    }
    
    public Integer getTeacherId() {
        return teacherId.get();
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId.set(teacherId);
    }
    
    public IntegerProperty teacherIdProperty() {
        return teacherId;
    }
    
    public Integer getStudentCount() {
        return studentCount.get();
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount.set(studentCount);
    }
    
    public IntegerProperty studentCountProperty() {
        return studentCount;
    }

    public String getRingName() {
        return ringName.get();
    }

    public void setRingName(String ringName) {
        this.ringName.set(ringName);
    }
    
    public StringProperty ringNameProperty() {
        return ringName;
    }
    
    public String getTeacherName() {
        return teacherName.get();
    }

    public void setTeacherName(String teacherName) {
        this.teacherName.set(teacherName);
    }
    
    public StringProperty teacherNameProperty() {
        return teacherName;
    }
    
    public String getTeacherUserename() {
        return teacherUsername.get();
    }

    public void setTeacherUsername(String teacherName) {
        this.teacherUsername.set(teacherName);
    }
    
    public StringProperty teacherUsernameProperty() {
        return teacherUsername;
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
        return "TRingManager{" + "ringId=" + ringId + ", schoolId=" + schoolId + ", managerId=" + managerId + ", teacherId=" + teacherId + ", ringName=" + ringName + ", teacherName=" + teacherName + ", button=" + button + '}';
    }
    
    
    
}
