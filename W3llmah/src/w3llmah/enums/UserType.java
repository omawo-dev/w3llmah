/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.enums;

/**
 *
 * @author mapvsnp
 */
public enum UserType {
    ADMIN ("ADMIN"),
    TEACHER ("TEACHER"),
    STUDENT ("STUDENT");

    private final String name;       

    private UserType(String s) {
        this.name = s;
    }

    public boolean equalsName(String otherName) {
        return this.name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
    
    public static UserType typeOf(String s) {
        if(s.equals(ADMIN.name)) {
            return ADMIN;
        } else if (s.equals(TEACHER.name)) {
            return TEACHER;
        } else if (s.equals(STUDENT.name)) {
            return STUDENT;
        } else {
            throw new IllegalArgumentException(s + " not found in UserType");
        }
    }
}