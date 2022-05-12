/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUIBACK.ProfileController;
import Entities.Produit;
import Entities.Reclamation;
import Entities.User;
import Services.ProduitService;
import Services.UserService;
import Services.ReclamationService;
import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.lowagie.text.DocumentException;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProfileFrontController implements Initializable {

    List<User> liste = new ArrayList<User>();
    UserService us = new UserService();
    ObservableList<String> ss = FXCollections.observableArrayList();
    private Circle image;
    private Button name;
    @FXML
    private Pane paneNoir;
    @FXML
    private Circle circleu;
    @FXML
    private TextField nom_i;
    @FXML
    private Label e_nom;
    @FXML
    private TextField email_i;
    @FXML
    private Label e_mail;
    @FXML
    private TextField telephone_i;
    @FXML
    private Label e_telephone;
    @FXML
    private TextField prenom_i;
    @FXML
    private Label e_prenom;
    @FXML
    private TextField tf_image;
    @FXML
    private Button importb;
    @FXML
    private Label e_prenom11;
    @FXML
    private DatePicker birthday;
    @FXML
    private Label e_prenom1;
    @FXML
    private JFXButton modifier;
    @FXML
    private Label Nomc;
    StringBuilder errors = new StringBuilder();
    @FXML
    private Hyperlink HyHistoCnx;
    @FXML
    private HBox hboxnavbar;
    @FXML
    private Label sallefxid;
    @FXML
    private Label eventfxid;
    @FXML
    private Label produitfxid;
    @FXML
    private Label reclamationfxid;
    @FXML
    private Button namefxid;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private Label articlefxid;
    @FXML
    private Circle circle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        namefxid.setText(UserconnectedC.getNom());
        setImage();
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        circleu.setRadius(55);

        try {
            circleu.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUser();
        mainpane.setOpacity(0);
        makeFadeInTransition();
    }
   private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

    }
    private void logOut(ActionEvent event) {
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
    }

    public void setImage(String name) {
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        circleu.setRadius(55);
        try {
            System.out.println(new Image(file.toURI().toURL().toExternalForm()));
            circleu.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser() {
        nom_i.setText(UserconnectedC.getNom());
        prenom_i.setText(UserconnectedC.getPrenom());
        telephone_i.setText(String.valueOf(UserconnectedC.getPhone()));
        email_i.setText(UserconnectedC.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = UserconnectedC.getBirthday();
        LocalDate localDate = LocalDate.parse(date, formatter);
        birthday.setValue(localDate);
        tf_image.setText(UserconnectedC.getImageFile());
        if (UserconnectedC.getImageFile() != null) {
            setImage(UserconnectedC.getImageFile());
        }
        Nomc.setText(prenom_i.getText() + " " + nom_i.getText());
        email_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                    + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_mail.setText("");

            } else {
                e_mail.setText("mail invalide ");
                errors.append("- votre email est invalid\n");//string s --- s+="erreur"

            }
        });
        tf_image.textProperty().addListener((observable, oldValue, newValue) -> {

            if (tf_image.getText().trim().isEmpty()) {
                e_prenom11.setText("image invalide ");
                errors.append("- votre image est invalid\n");//string s --- s+="erreur"

            }
        });

        telephone_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[0-9]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                if (newValue.length() == 8) {
                    e_telephone.setText("");
                }

            } else {
                e_telephone.setText("le numero du telephone est invalid");
                errors.append("- votre telephone est invalid\n");//string s --- s+="erreur"

            }
        });
        nom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_nom.setText("");
            } else {
                e_nom.setText("votre nom est invalid");
                errors.append("- votre nom est invalid\n");//string s --- s+="erreur"

            }
        });
        prenom_i.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                e_prenom.setText("");

            } else {
                e_prenom.setText("votre prenom est invalid");
                errors.append("- votre prenom est invalid\n");//string s --- s+="erreur"

            }
        });
        birthday.valueProperty().addListener((observable, oldValue, newValue) -> {

            LocalDate dateBefore18years = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(6570);

            boolean cond = (newValue.isBefore(dateBefore18years));
            System.out.println(cond);
            if (cond) {
                e_prenom1.setText("");

            } else {
                e_prenom1.setText("*vous devez avoir au moins 18 ans");
                errors.append("- Vous devez avoir au moins 18 ans \n");//string s --- s+="erreur"
            }
        });
    }

    @FXML
    private void modifierUtilisateur(ActionEvent event) {

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            UserService us = new UserService();
            User u = new User();
            u.setNom(nom_i.getText());
            u.setPrenom(prenom_i.getText());
            u.setEmail(email_i.getText());
            u.setPhone(telephone_i.getText());
            u.setBirthday(java.sql.Date.valueOf(birthday.getValue()).toString());
            u.setImageFile(tf_image.getText());
            u.setId(UserconnectedC.getId());
            us.modifier(UserconnectedC.getId(), u);
            System.out.println(UserconnectedC.getId());
            System.out.println("updated ! ");
        }
    }

    @FXML
    private void importer(ActionEvent event) {
        String fileName = null;
        FileChooser fc = new FileChooser();
        File SelectedFile = fc.showOpenDialog(null);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", ".jpg", ".png"));
        //  e.setImage_event(SelectedFile.getAbsolutePath());
        if (SelectedFile != null) {

            String fcs = SelectedFile.toString();
            int index = fcs.lastIndexOf('\\');
            if (index > 0) {
                fileName = fcs.substring(index + 1);
            }

        }
        String key = UUID.randomUUID().toString();
        String fcs = SelectedFile.getAbsolutePath();
        File source = new File(fcs);
        File destination = new File("C:\\xampp\\htdocs\\img\\" + key + fileName);
        String url = destination.getAbsolutePath();
        System.out.println(url);

        if (!destination.exists()) {
            try {
                FileUtils.copyFile(source, destination);
            } catch (Exception E) {

            }
            tf_image.setText(key + fileName);
        }
    }

    @FXML
    private void send(MouseEvent event) {
    }

    @FXML
    private void historique(MouseEvent event) {
        UserService u = new UserService();
        try {
            u.RapportHCbyUser(UserconnectedC);
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setImage() {
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        try {
            System.out.println(new Image(file.toURI().toURL().toExternalForm()));
            circle.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 
    private void makeFadeInTransition(String a) {

        try {
            Stage stage = (Stage) mainpane.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(a));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  @FXML
    private void reclamation(MouseEvent event) throws IOException {
        makeFadeInTransition("ReclamationFront.fxml");

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeInTransition("Evenement.fxml");

    }

    @FXML
    private void home(MouseEvent event) {
        makeFadeInTransition("HomeFront.fxml");

    }

    @FXML
    private void salle(MouseEvent event) {
        makeFadeInTransition("Salle.fxml");

    }

    @FXML
    private void produit(MouseEvent event) {
        makeFadeInTransition("Produit.fxml");

    }

    @FXML
    private void profile(MouseEvent event) {
        makeFadeInTransition("ProfileFront.fxml");

    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
                        makeFadeInTransition("Article.fxml");

    }
}
