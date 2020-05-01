/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah;

import com.github.fxrouter.FXRouter;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 *
 * @author mapvsnp
 */
public class W3llmah extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXRouter.bind(this, stage, "وعلمه");
        FXRouter.setAnimationType("fade");
        setRoutes();
        FXRouter.goTo("login");
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private static void setRoutes() {
        FXRouter.when("login", "ui/login/login.fxml", 600.0, 500.0);
        FXRouter.when("teacherDashboard", "ui/teacher/dashboard/teacherDashboard.fxml", 811.0, 600.0);
        FXRouter.when("studentDashboard", "ui/student/dashboard/studentDashboard.fxml", 811.0, 600.0);
        FXRouter.when("studentChart", "ui/student/chart/studentChart.fxml", 811.0, 600.0);
        FXRouter.when("teacherChart", "ui/teacher/charts/teacherChart.fxml");
        FXRouter.when("adminAddRing", "ui/admin/addRing/AddRing.fxml", 600.0, 500.0);
        FXRouter.when("adminAddStudent", "ui/admin/addStudent/AddStudent.fxml", 600.0, 500.0);
        FXRouter.when("adminRings", "ui/admin/rings/rings.fxml", 811.0, 600.0);
        FXRouter.when("adminStudentsRing", "ui/admin/studentsRing/studentRing.fxml", 811.0, 600.0);
        FXRouter.when("adminRingsChart", "ui/admin/ringsChart/adminRingsChart.fxml", 811.0, 600.0);
    }
    
}
