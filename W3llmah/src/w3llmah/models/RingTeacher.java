/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.models;

/**
 *
 * @author 96655
 */
public class RingTeacher {
    String ringTeacherName;
    int maxStudent ;
    int ringId,teacherId;

    public RingTeacher() {
    }

    
    
    public RingTeacher(String ringTeacher, int maxStudent, int ringId, int teacherId) {
        this.ringTeacherName = ringTeacher;
        this.maxStudent = maxStudent;
        this.ringId = ringId;
        this.teacherId = teacherId;
    }

    
    
    
    public String getRingTeacherName() {
        return ringTeacherName;
    }

    public void setRingTeacherName(String ringTeacherName) {
        this.ringTeacherName = ringTeacherName;
    }

    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    public int getRingId() {
        return ringId;
    }

    public void setRingId(int ringId) {
        this.ringId = ringId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return  ringTeacherName ;
    }
    
    
    
    
    
    
    
}
