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
public class Manager {
    
    int managerId;
    String name;
    int userId;

    public Manager() {
    }

    public Manager(String name, int userId) {
        this.name = name;
        this.userId = userId;
    }

    
    
    
    public Manager(int managerId, String name, int userId) {
        this.managerId = managerId;
        this.name = name;
        this.userId = userId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Manager{" + "managerId=" + managerId + ", name=" + name + ", userId=" + userId + '}';
    }
    
    
    
    
    
}
