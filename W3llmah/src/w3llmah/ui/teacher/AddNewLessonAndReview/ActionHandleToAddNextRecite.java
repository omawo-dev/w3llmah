/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.teacher.AddNewLessonAndReview;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import w3llmah.ui.teacher.dashboard.TeacherDashboardController;
import w3llmah.ui.teacher.AddNewLessonAndReview.AddNextReciteController;
import w3llmah.services.LocalStorage;

/**
 *
 * @author 96655
 */
public class ActionHandleToAddNextRecite {

    TeacherDashboardController teacherController;
    
    public void addLessonAndReview(ActionEvent event) {
        Button btn = (Button) event.getSource();
        System.out.println(btn.getId());
        LocalStorage.setItem("studentIdBtn", btn.getId());
        System.out.println(LocalStorage.getItem("studentIdBtn"));
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddNextRecite.fxml"));
            Parent root = loader.load();

            AddNextReciteController addNRCtrl = loader.getController();
            addNRCtrl.setTeacherDashboard(teacherController);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    
    public void setTeacherController(TeacherDashboardController tController){
        
        this.teacherController = tController;
    }
    
}
