/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.models;

import java.util.Date;

/**
 *
 * @author 96655
 */
public class Attend {
    
    int attendId , studentId , ringId  ;
    Date date ;

    public Attend() {
    }

    public Attend(int studentId, int ringId, Date date) {
        this.studentId = studentId;
        this.ringId = ringId;
        this.date = date;
    }

    public Attend(int attendId, int studentId, int ringId, Date date) {
        this.attendId = attendId;
        this.studentId = studentId;
        this.ringId = ringId;
        this.date = date;
    }

    public int getAttendId() {
        return attendId;
    }

    public void setAttendId(int attendId) {
        this.attendId = attendId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getRingId() {
        return ringId;
    }

    public void setRingId(int ringId) {
        this.ringId = ringId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Attend{" + "attendId=" + attendId + ", studentId=" + studentId + ", ringId=" + ringId + ", date=" + date + '}';
    }
    
    
    
}
