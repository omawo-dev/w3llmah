/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package w3llmah.ui.login;

import com.github.fxrouter.FXRouter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import w3llmah.dto.LoginDto;
import w3llmah.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import w3llmah.enums.UserType;
import w3llmah.services.LocalStorage;

/**
 * FXML Controller class
 *
 * @author mapvsnp
 */
public class LoginController implements Initializable {

    //  Components    //
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXDialog dlgError;
    
    //  validators   //
    private RequiredFieldValidator required;
    @FXML
    private AnchorPane pnAnchorPane;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        required = new RequiredFieldValidator("هذا الحقل مطلوب");
        this.setValidators();
    }    

    @FXML
    private void onLoginClick(ActionEvent event) {
        loginAction();
    }

    @FXML
    private void onRigetserClick(ActionEvent event) {
        showComingSoonMsg();
        
    }
    
    @FXML
    private void onEnterClicked(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
              loginAction();
          }
    }
    
    public void loginAction(){
        if(!this.validInputs()) {
            return;
        }
        LoginDto loginDto = new LoginDto(
            this.txtUserName.getText(),
            this.txtPassword.getText()
        );
        User user = User.login(loginDto);
        if(user == null) {
            this.showWrongInputMsg();
        } else {
            try {
                storeUserInfo(user);
                goToUserMainPage(user.getUserKind());
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private boolean validInputs() {
        return 
            this.txtUserName.validate()
            & this.txtPassword.validate();
    }
    
    private void setValidators() {
        this.txtUserName.setValidators(required);
        this.txtPassword.setValidators(required);
    }
    
    private void showWrongInputMsg() {
        Label label = new Label("اسم المستخدم أو كلمة المرور غير صحيحة");
        label.getStyleClass().add("login-error-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }
    
    private void showComingSoonMsg() {
        Label label = new Label("قريباً");
        label.getStyleClass().add("coming-msg");
        Pane pane = pnAnchorPane;
        JFXSnackbar bar = new JFXSnackbar(pane);
        bar.enqueue(new JFXSnackbar.SnackbarEvent(label));
    }
    
    private void goToUserMainPage(String userType) throws IOException {
        UserType type = UserType.typeOf(userType);
        switch (type) {
            case ADMIN:
                FXRouter.goTo("adminRings");
                break;
            case TEACHER:
                FXRouter.goTo("teacherDashboard");
                break;
            case STUDENT:
                FXRouter.goTo("studentDashboard");
                break;
            default:
                break;
        }
    }
    
    private void storeUserInfo(User user) {
        LocalStorage.setItem("userId", user.getUserId());
        LocalStorage.setItem("userType", user.getUserKind());
    }

    
}
