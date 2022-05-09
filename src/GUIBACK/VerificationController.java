/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.lowagie.text.DocumentException;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class VerificationController implements Initializable {

    @FXML
    private AnchorPane logoutpane;
    private JFXButton closebtn;
    @FXML
    private JFXButton yesbtn;
    @FXML
    private JFXButton nobtn;
    public static boolean confirmed = false;      //used in loggin out
    UserService us = new UserService();
    @FXML
    private JFXButton exit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nobtn.setOnMouseEntered(e -> {
            nobtn.setStyle("-fx-background-color: #FF9A00;");
            nobtn.setEffect(new Bloom(0.8));
        });
        nobtn.setOnMouseExited(e -> {
            nobtn.setStyle("-fx-background-color:   #f00020;");
            nobtn.setEffect(new Bloom(1));
        });

        yesbtn.setOnMouseEntered(e -> {
            yesbtn.setStyle("-fx-background-color: #FF9A00;");
            yesbtn.setEffect(new Bloom(0.8));
        });
        yesbtn.setOnMouseExited(e -> {
            yesbtn.setStyle("-fx-background-color:  #008000;");
            yesbtn.setEffect(new Bloom(1));
        });

    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void handleConfirmation(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        TranslateTransition t1 = new TranslateTransition(Duration.millis(270), btn);
        t1.setToY(-17d);
        PauseTransition p1 = new PauseTransition(Duration.millis(40));
        TranslateTransition t2 = new TranslateTransition(Duration.millis(270), btn);
        t2.setToY(0d);

        SequentialTransition d = new SequentialTransition(btn, t1, p1, t2);
        d.play();
        d.setOnFinished(event1 -> {
            if (btn.getId().equals("yesbtn")) {
                confirmed = true;
                handleClose(event);
                System.out.println("yes");
                System.out.println("pdf");
                UserService u = new UserService();
                try {
                    us.RapportHCbyUser(UserconnectedC);
                } catch (IOException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Stage stage = (Stage) yesbtn.getScene().getWindow();
                stage.close();
            } else if (btn.getId().equals("nobtn")) {
                confirmed = false;
                System.out.println("no");
                Stage stage = (Stage) nobtn.getScene().getWindow();
                stage.close();
                handleClose(event);
            }
        });
    }

    @FXML
    private void exit(MouseEvent event) {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}
