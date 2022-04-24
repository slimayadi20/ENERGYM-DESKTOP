/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import static GUI.ForgetPasswordController.cc;
import Services.CryptWithMD5;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class UpdatePasswordController implements Initializable {
    
    @FXML
    private AnchorPane mainLayer;
    
    private TextField emailTF;
    
    private JFXButton uploadLogoBtn;
    private JFXButton nextBtn;
    @FXML
    private TextField loginTF;
    @FXML
    private PasswordField passwordTF;
    private Label settingLB1;
    @FXML
    private Button valSignInBtn;
    @FXML
    private Label closeLB;
    @FXML
    private ImageView userIcon;
    @FXML
    private ImageView passwordIcon;
    @FXML
    private ImageView numTel2Icon;
    private ImageView matFIcon;
    @FXML
    private ImageView deviseIcon;
    @FXML
    private ImageView numtel1Icon;
    @FXML
    private ImageView emailIcon;
    private ImageView paysIcon;
    private ImageView adresseIcon;
    private ImageView codePostalIcon;
    private ImageView villeIcon;
    private ImageView rsIcon;
    @FXML
    private Circle reduceBtn;
    @FXML
    private Circle closeBtn;
    private Label fileName;
    @FXML
    private AnchorPane animLayer;
    @FXML
    private Label varLB;
    @FXML
    private Button signUpBtn;
    private JFXButton signInBtn;
    @FXML
    private Label helloLB;
    private DatePicker dpdate;
    @FXML
    private AnchorPane defaultLayer;
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    boolean x = false;
    @FXML
    private Button swapbtn;
    
    private TextField numTelTF;
    private PasswordField passwordPF;
    private TextField prenomtf;
    private Button signupbtn;
    private Button btnfp;
    public static long idglobal;
    
    private TextField nameTF;
    private ImageView passwordicon1;
    @FXML
    private Button verifbtn;
    @FXML
    private TextField passTF;
    @FXML
    private PasswordField verifpassPF;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        varLB.setVisible(false);
        loginTF.setVisible(false);
        passwordTF.setVisible(false);
        valSignInBtn.setVisible(false);
        userIcon.setVisible(false);
        passwordIcon.setVisible(false);
        signUpBtn.setVisible(false);
        swapbtn.setText("Login");
    }
    
    private void resetSignUpTF() {
        numTelTF.setText("");
        emailTF.setText("");
        nameTF.setText("");
        prenomtf.setText("");
        dpdate.setValue(null);
        passwordPF.setText("");
        
    }
    
    @FXML
    private void closeScene(MouseEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void reduceOnClick(MouseEvent event) {
        Stage currentStage;
        currentStage = (Stage) ((Node) (event.getSource())).getScene().getWindow().getScene().getWindow();
        currentStage.setIconified(true);
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle("Energym application ");
        tray.setMessage("Application is still running at the backround.");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(5000));
    }
    
    @FXML
    private void closeOnClick(MouseEvent event) {
        System.exit(0);
    }
    
    private void resetSignInTF() {
        loginTF.setText("");
        passwordTF.setText("");
    }
    
    private void setIconVisibility(boolean state) {
//        rsIcon.setVisible(state);
        numTel2Icon.setVisible(state);
//        matFIcon.setVisible(state);
        deviseIcon.setVisible(state);
        numtel1Icon.setVisible(state);
        emailIcon.setVisible(state);
//        paysIcon.setVisible(state);
        adresseIcon.setVisible(state);
        passwordicon1.setVisible(state);
        codePostalIcon.setVisible(state);
        villeIcon.setVisible(state);
        
    }
    
    @FXML
    private void swapForm(MouseEvent event) {
        
          try {
                  Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("reset password");
            stage.setScene(scene);
            stage.show();
             
            } catch (IOException ex) {
                System.err.println("Erreur");
            }
    }
    
    private void makeStageDragable() {
        
    }
    
    
    
    @FXML
    private void performLogIn(MouseEvent event) throws ParseException {
  
    }
    
    @FXML
    private void signUpForm(MouseEvent event) {
        
    }

    

    @FXML
    private void changer(MouseEvent event) throws SQLException {
               String p1 = passTF.getText();
        String p2 = verifpassPF.getText();
            System.out.println("done");


        if (p1.equals(p2)) {

            us.updatemdp(cc, p1);
           // us.updatemdp("slim.ayadi@esprit.tnhh", p1);
            System.out.println("done");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Mot de passe modifié !");
            alert.show();

            try {
                 Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("reset password");
            stage.setScene(scene);
            stage.show();
            } catch (IOException ex) {
                System.out.println("Erreur chh !!!");
            }

        } else {
 Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("les mots de passe ne sont pas conformes !");
            alert.show();        }
    }
    
}
