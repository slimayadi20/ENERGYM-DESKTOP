/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.CryptWithMD5;
import Services.UserService;
import Tools.Smsapi;
import com.jfoenix.controls.JFXButton;
import static energym.desktop.MainClass.UserconnectedC;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.Random;
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
public class ForgetPasswordController implements Initializable {

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

    Random rand = new Random();
    int randomcode = rand.nextInt(9999);
    public static String cc;
    public static String c;
    String code = String.valueOf(randomcode);

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
    //  private Button btnfp;
    public static long idglobal;

    private TextField nameTF;
    private ImageView passwordicon1;
    @FXML
    private TextField phoneTF;
    @FXML
    private Button sendverifbtn;
    @FXML
    private PasswordField verifPF;
    @FXML
    private Button verifbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        varLB.setVisible(false);
        loginTF.setVisible(false);
        passwordTF.setVisible(false);
        valSignInBtn.setVisible(false);
        //     btnfp.setVisible(false);
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
        matFIcon.setVisible(state);
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
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage = new Stage();

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
        if (us.checklogin(loginTF.getText(), CryptWithMD5.cryptWithMD5(passwordTF.getText()))) {
            //7ell interface

            User u = us.findByUsername(loginTF.getText());
            UserconnectedC = u;
            switch (u.getRoles()) {
                case "ROLE_ADMIN":
                    try {
                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/User.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("Dashbord Admin");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "ROLE_GERANT":
                    System.out.println("bienveunue Passager");
                    try {

                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("INVICTUS APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        //       Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case "ROLE_CLIENT":
                    System.out.println("bienveunue EMPLOYE");
                    try {

                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("INVICTUS APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        //  Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login fail");
            alert.setContentText("Username or password invalid");
            alert.showAndWait();
        }
    }

    @FXML
    private void signUpForm(MouseEvent event) {

    }

    @FXML
    private void envoyercode(MouseEvent event) {
        cc = phoneTF.getText();
        if (us.emailExist(cc)) {
            // Smsapi.sendSMS("verification code" + randomcode);
            System.out.println(randomcode);
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("sms sent");
            tray.setMessage("You will receive a verification code in  few seconds");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));
        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous n'avez pas de compte, veuillez vous inscrire !");
            alert.show();

        }
    }

    @FXML
    private void getpass(MouseEvent event) {
        StringBuilder errors = new StringBuilder();
        cc = phoneTF.getText();
        c = verifPF.getText();
        if (cc.trim().isEmpty()) {
            errors.append("- Please enter your email\n");

        }
        if (c.trim().isEmpty()) {
            errors.append("- Please enter your verification code\n");

        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            if (code.equals(c)) {
                try {
                    Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stageclose.close();
                    Parent root = FXMLLoader.load(getClass().getResource("/GUI/UpdatePassword.fxml"));
                    Stage stage = new Stage();

                    Scene scene = new Scene(root);

                    stage.setTitle("reset password");
                    stage.setScene(scene);
                    stage.show();

                } catch (IOException ex) {
                    System.err.println("Erreur");
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Enter Correct Code !!!");
                alert.show();
            }
        }
    }

}
