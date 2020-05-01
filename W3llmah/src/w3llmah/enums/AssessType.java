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
public enum AssessType {
    EXELLENT ("ممتاز"),
    VERY_GOOD ("جيدجدا"),
    GOOD ("جيد"),
    BAD ("لم يحفظ"),
    NOT_EVAL ("-");

    private final String name;       

    private AssessType(String s) {
        this.name = s;
    }

    public boolean equalsName(String otherName) {
        return this.name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
    
    public static AssessType typeOf(String s) {
        if(s.equals(EXELLENT.name)) {
            return EXELLENT;
        } else if (s.equals(VERY_GOOD.name)) {
            return VERY_GOOD;
        } else if (s.equals(GOOD.name)) {
            return GOOD;
        } else if (s.equals(BAD.name)) {
            return BAD;
        } else if (s.equals(NOT_EVAL.name)) {
            return NOT_EVAL;
        } else {
            throw new IllegalArgumentException(s + " not found in AssessType");
        }
    }
}
