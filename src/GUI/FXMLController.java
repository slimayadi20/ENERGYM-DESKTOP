/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.CryptWithMD5;
import Services.UserService;
import Tools.JavaMailUtilUser;
import com.jfoenix.controls.JFXButton;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import static energym.desktop.MainClass.UserconnectedC;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FXMLController implements Initializable {

    @FXML
    private AnchorPane mainLayer;

    @FXML
    private TextField emailTF;

    private JFXButton uploadLogoBtn;
    private JFXButton nextBtn;
    @FXML
    private TextField loginTF;
    @FXML
    private PasswordField passwordTF;
    @FXML
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
    @FXML
    private ImageView matFIcon;
    @FXML
    private ImageView deviseIcon;
    @FXML
    private ImageView numtel1Icon;
    @FXML
    private ImageView emailIcon;
    private ImageView paysIcon;
    @FXML
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
    @FXML
    private DatePicker dpdate;
    @FXML
    private AnchorPane defaultLayer;
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    boolean x = true;
    @FXML
    private Button swapbtn;

    @FXML
    private TextField numTelTF;
    @FXML
    private PasswordField passwordPF;
    @FXML
    private TextField prenomtf;
    @FXML
    private Button signupbtn;
    @FXML
    private Button btnfp;
    public static long idglobal;

    @FXML
    private TextField nameTF;
    @FXML
    private ImageView passwordicon1;
    @FXML
    private ImageView birthdayicon;
    @FXML
    private ImageView image;
    @FXML
    private Button upload;
    User o = new User();
    File selectedFile;

    private String path;
    @FXML
    private Label e_mail;
    @FXML
    private Label e_password;
    @FXML
    private Label e_phone;
    @FXML
    private Label e_nom;
    @FXML
    private Label e_prenom;
    @FXML
    private Label e_prenom1;
    private static final String VOICENAME = "kevin16";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

        Voice voice;
        VoiceManager vm = VoiceManager.getInstance();
        voice = vm.getVoice(VOICENAME);

        voice.allocate();
        try {
            //voice.speak("Welcome Back to Energym");
            System.out.println("welcome");
        } catch (Exception e) {

        }
        System.out.println("form swapped");
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(animLayer);
        loginTF.setText("slim.ayadi@esprit.tn");
        mainLayer.setTranslateX(-400);

        resetSignInTF();

        //Hiding Icons
        setIconVisibility(false);

        //TODO : Hide old cmpts show login cpts
        swapbtn.setText("SignUp");
        // SAI_Téléphone.MasqueSaisie = MasqueNuméroDeTéléphone("ES")

        settingLB1.setText("Login");
        nameTF.setVisible(false);
        numTelTF.setVisible(false);
        dpdate.setVisible(false);
        emailTF.setVisible(false);
        prenomtf.setVisible(false);
        passwordPF.setVisible(false);
        signupbtn.setVisible(false);
        varLB.setVisible(true);
        helloLB.setVisible(false);
        birthdayicon.setVisible(false);
        upload.setVisible(false);
        image.setVisible(false);
        e_mail.setVisible(false);
        e_phone.setVisible(false);
        e_nom.setVisible(false);
        e_prenom.setVisible(false);
        e_password.setVisible(false);

        //Login and password TF
        userIcon.setVisible(true);
        passwordIcon.setVisible(true);
        loginTF.setVisible(true);
        passwordTF.setVisible(true);
        valSignInBtn.setVisible(true);
        btnfp.setVisible(true);
        slide.setToX(689);
        slide.play();
        slide.setOnFinished((e -> {

        }));
        emailTF.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_mail.setText("");

            } else {
                e_mail.setText("*mail invalide ");
            }
        });
        numTelTF.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[0-9]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                if (newValue.length() == 8) {
                    e_phone.setText("");
                }

            } else {
                e_phone.setText("*le numero du telephone est invalid");
            }
        });
        nameTF.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_nom.setText("");
            } else {
                e_nom.setText("*votre nom est invalid");
            }
        });
        prenomtf.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_prenom.setText("");

            } else {
                e_prenom.setText("*votre prenom est invalid");
            }
        });
        passwordPF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 5) {
                e_password.setText("");
            } else {
                e_password.setText("*Votre mot de passe est invalid");
            }

        });
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
        numTel2Icon.setVisible(state);
        matFIcon.setVisible(state);
        deviseIcon.setVisible(state);
        numtel1Icon.setVisible(state);
        emailIcon.setVisible(state);
        adresseIcon.setVisible(state);
        passwordicon1.setVisible(state);
        birthdayicon.setVisible(state);
        upload.setVisible(state);
        image.setVisible(state);
        e_mail.setVisible(state);
        e_phone.setVisible(state);
        e_nom.setVisible(state);
        e_prenom.setVisible(state);
        e_password.setVisible(state);
    }

    @FXML
    private void swapForm(MouseEvent event) {

        if (x == false) {

            System.out.println("form swapped");
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(animLayer);

            mainLayer.setTranslateX(-400);
            //Resetting textFields
            resetSignInTF();
            //Hiding Icons
            setIconVisibility(false);

            //TODO : Hide old cmpts show login cpts
            swapbtn.setText("SignUp");
            settingLB1.setText("Login");
            nameTF.setVisible(false);
            numTelTF.setVisible(false);
            dpdate.setVisible(false);
            emailTF.setVisible(false);
            prenomtf.setVisible(false);
            passwordPF.setVisible(false);
            signupbtn.setVisible(false);
            varLB.setVisible(true);
            helloLB.setVisible(false);
            upload.setVisible(false);
            image.setVisible(false);
            e_mail.setVisible(false);
            e_phone.setVisible(false);
            e_nom.setVisible(false);
            e_prenom.setVisible(false);
            e_password.setVisible(false);
            //Login and password TF
            userIcon.setVisible(true);
            passwordIcon.setVisible(true);
            loginTF.setVisible(true);
            passwordTF.setVisible(true);
            valSignInBtn.setVisible(true);
            btnfp.setVisible(true);

            slide.setToX(689);
            slide.play();
            slide.setOnFinished((e -> {

            }));
            x = true;
        } else {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.7));
            slide.setNode(animLayer);

            mainLayer.setTranslateX(0);

            resetSignUpTF();

            //Shwoing Icons
            setIconVisibility(true);
            swapbtn.setText("Login");
            settingLB1.setText("SignUP");

            signUpBtn.setVisible(false);
            numTelTF.setVisible(true);

            emailTF.setVisible(true);
            nameTF.setVisible(true);
            prenomtf.setVisible(true);
            passwordPF.setVisible(true);
            signupbtn.setVisible(true);
            dpdate.setVisible(true);
            varLB.setVisible(false);
            helloLB.setVisible(true);
            upload.setVisible(true);
            image.setVisible(true);

            loginTF.setVisible(false);
            passwordTF.setVisible(false);
            valSignInBtn.setVisible(false);
            btnfp.setVisible(false);
            userIcon.setVisible(false);
            passwordIcon.setVisible(false);
            //fileName.setVisible(true);

            //applyValidators();
            slide.setToX(0);

            slide.play();

            slide.setOnFinished((e -> {

            }));
            x = false;
        }

    }

    private void makeStageDragable() {

    }

    @FXML
    private void forgotpassword(ActionEvent event) {
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/ForgetPassword.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("reset password");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void performeSignup(MouseEvent event) {
        StringBuilder errors = new StringBuilder();
        if (nameTF.getText().trim().isEmpty()) {
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if (prenomtf.getText().trim().isEmpty()) {

            errors.append("- Please enter a Last Name\n");
        }
        if (emailTF.getText().trim().isEmpty()) {
            errors.append("- Please enter a Email\n");
        }
        if (!isAddressValid(emailTF.getText())) {
            errors.append("- Please enter a valid Email\n");
        }

        if (passwordPF.getText().trim().isEmpty()) {
            errors.append("- Please enter a Password\n");
        }
        if (isSpace(passwordPF.getText())) {
            errors.append("- Please enter a Password without space \n");
        }
        if (hasSpecialCharacter(passwordPF.getText())) {
            errors.append("- delete the special character in the password \n");
        }

        if (numTelTF.getText().trim().isEmpty()) {
            errors.append("- Please enter a Phone number\n");
        }
        if (dpdate.getValue() == null) {
            errors.append("- Please enter a Birthday\n");
        }
        try {
            Integer.parseInt(numTelTF.getText());
        } catch (NumberFormatException e) {
            errors.append("- Please enter a valid number\n");
        }
        if (us.emailExist(emailTF.getText())) {
            errors.append("- email already exist");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            User u = new User();
            if (selectedFile != null) {
                try {
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\xampp\\htdocs\\img");
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Date date = new Date(System.currentTimeMillis());

            u.setBirthday(Date.valueOf(dpdate.getValue()).toString());
            u.setEmail(emailTF.getText());
            u.setNom(nameTF.getText());
            u.setPhone((numTelTF.getText()));
            u.setPassword(passwordPF.getText());
            u.setPrenom(prenomtf.getText());
            u.setStatus(0);
            u.setImageFile(path);
            u.setCreated_at(date);
            u.setRoles("ROLE_GERANT");
            u.setActivation_token( UUID.randomUUID().toString());
          
            
              JavaMailUtilUser mail = new JavaMailUtilUser();
            try {
                String content = "Activation de votre compte\n"
                        + "veuillez clicker sur le lien ci-dessus pour l'activer  votre compte\n"
                        + "http://127.0.0.1:8000/activation/" + u.getActivation_token();
                u.setActivation_token(null);
                mail.sendMail("Mail Verification", content, emailTF.getText());
                 try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            UserconnectedC = null;
        } catch (IOException ex) {
            System.err.println("Erreur");
        }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
              us.ajouter(u);
            
            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Signup Success");
            tray.setMessage("You successufuly signin in our application");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

        }

    }

    @FXML
    private void performLogIn(MouseEvent event) throws ParseException {
        
        User lu = us.findByUsername(loginTF.getText());

        if (us.checklogin(loginTF.getText(), CryptWithMD5.cryptWithMD5(passwordTF.getText()))) {
            //7ell interface
                if ("null".equals(lu.getActivation_token())) {
                    System.out.println("activated");
            User u = us.findByUsername(loginTF.getText());
            UserconnectedC = u;
            u.toString();
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
                        Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
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

                        stage.setTitle("ENERGYM APP");
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

                        stage.setTitle("ENERGYM APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        //  Logger.getLogger(AuthentificationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }
                }
                else {
                      Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login fail");
            alert.setContentText("activate your account");
            alert.showAndWait();
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

    public static boolean isSpace(String password) {
        for (char currentChar : password.toCharArray()) {
            if (Character.isWhitespace(currentChar)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpecialCharacter(String password) {
        Pattern sPattern = Pattern.compile("[a-zA-z0-9]*");
        Matcher sMatcher = sPattern.matcher(password);
        if (!sMatcher.matches()) {
            return true;
        }
        return false;
    }

    public boolean isAddressValid(String address) {
        // Find the separator for the domain name
        int pos = address.indexOf('@');

        // If the address does not contain an '@', it's not valid
        if (pos == -1) {
            return false;
        }
        return true;

    }

    @FXML
    private void uploadimage(MouseEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            image.setImage(new Image(selectedFile.toURI().toURL().toString()));
            image.setFitHeight(150);
            image.setFitWidth(250);
            upload.setText(path);

        }
    }
}
