/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.services;

import java.util.Hashtable;

/**
 *
 * @author mapvsnp
 */
public class LocalStorage {
    
    private static Hashtable<String, Object> table = new Hashtable<String, Object>();
    
    public static void setItem(String key, Object value) {
        table.put(key, value);
    }
    
    public static Object getItem(String key) {
        return table.get(key);
    }
    
    public static void clear() {
        table.clear();
    }
}
