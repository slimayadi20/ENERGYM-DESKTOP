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
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ReclamationFrontController implements Initializable {

    List<User> liste = new ArrayList<User>();
    UserService us = new UserService();
    @FXML
    private GridPane grid;
    @FXML
    private JFXTextField titrefxid;
    @FXML
    private JFXComboBox<String> produitfxid;
    @FXML
    private TextArea contenufxid;
    ObservableList<String> ss = FXCollections.observableArrayList();
    @FXML
    private Circle image;
    private Button name;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private JFXButton send;
    StringBuilder errors = new StringBuilder();
    @FXML
    private HBox hboxnavbar;
    @FXML
    private Label sallefxid;
    @FXML
    private Label eventfxid;
    @FXML
    private Label produitfxid1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(UserconnectedC);
        namefxid.setText(UserconnectedC.getNom());
        setImage();
        ProduitService ps = new ProduitService();
        String a = ps.afficherSLIM();
        ss.add(a);
        produitfxid.setItems(ss);
        ReclamationService ths = new ReclamationService();
        List<Reclamation> listTH = ths.afficherbyuser(UserconnectedC.getId());

        int colonne = 0;
        int row = 1;
        try {
            for (Reclamation t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReclamationItem.fxml"));

                Pane anchorPane = fxmlLoader.load();

                ReclamationItemController controller = fxmlLoader.getController();
                controller.setData(t);
                if (colonne == 2) {
                    colonne = 0;
                    row++;
                }
                grid.add(anchorPane, colonne++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(12));
                //break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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

    public void setImage() {
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());
        try {
            System.out.println(new Image(file.toURI().toURL().toExternalForm()));
            image.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleHome(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Front.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    private void handleProfile(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ProfileFront.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();

    }

    @FXML
    private void sendreclamation(MouseEvent event) {
        ProduitService ps = new ProduitService();
        Reclamation r = new Reclamation();
        r.setContenu(contenufxid.getText());
        r.setTitre(titrefxid.getText());
        System.out.println(ps.findid(produitfxid.getValue()));
        r.setProduit(ps.findid(produitfxid.getValue()));
        //     r.setNomUser(UserconnectedC.getId());
        r.setNomUser(32);
        Date date = new Date(System.currentTimeMillis());
        r.setDate(date);
        ReclamationService rs = new ReclamationService();
        if (titrefxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a Phone number\n");
        }
        if (contenufxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a Phone number\n");
        }
        if (produitfxid.getValue() == null) {
            errors.append("- Please enter a Birthday\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            errors.setLength(0);
        } else {
            rs.ajouter(r);
            contenufxid.setText("");
            titrefxid.setText("");
            produitfxid.setValue("");

            TrayNotification tray = new TrayNotification();

            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Reclamation ajoutée avec succes");
            tray.setMessage("You successufuly added your reclamation we ll be taking care of it");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(1000));

            ReclamationService ths = new ReclamationService();
            List<Reclamation> listTH = ths.afficher();

            int colonne = 0;
            int row = 1;
            try {
                for (Reclamation t : listTH) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ReclamationItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    ReclamationItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne == 2) {
                        colonne = 0;
                        row++;
                    }
                    grid.add(anchorPane, colonne++, row);
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
    private void profile(MouseEvent event) throws IOException {
             makeFadeInTransition("ProfileFront.fxml") ;


    }

    @FXML
    private void salle(MouseEvent event) {
           makeFadeInTransition("Salle.fxml") ;

    }

    @FXML
    private void produit(MouseEvent event) throws IOException {
           makeFadeInTransition("Produit.fxml") ;

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
              makeFadeInTransition("Evenement.fxml") ;

    }

    @FXML
    private void home(MouseEvent event) {
               makeFadeInTransition("HomeFront.fxml") ;

    }

    @FXML
    private void reclamation(MouseEvent event) {
               makeFadeInTransition("ReclamationFront.fxml") ;

    }
    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
                        makeFadeInTransition("Article.fxml");

    }
}
