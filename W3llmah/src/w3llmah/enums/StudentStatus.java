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
public enum StudentStatus {
    ACTIVE ("نشط"),
    DEACTIVATED ("ملغي");

    private final String name;       

    private StudentStatus(String s) {
        this.name = s;
    }

    public boolean equalsName(String otherName) {
        return this.name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
    
    public static StudentStatus typeOf(String s) {
        if(s.equals(ACTIVE.name)) {
            return ACTIVE;
        } else if (s.equals(DEACTIVATED.name)) {
            return DEACTIVATED;
        } else {
            throw new IllegalArgumentException(s + " not found in StudentStatus");
        }
    }
}
