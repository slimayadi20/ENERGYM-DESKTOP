/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Services.EventService;
import com.jfoenix.controls.JFXToggleButton;
import energym.desktop.MainFX;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.Agenda;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomeFrontController implements Initializable {

    @FXML
    private MediaView mediaView;
    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private AnchorPane secondpane;
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
    private Label titlefxid;
    @FXML
    private HBox hboxsport;
    @FXML
    private HBox hboxnavbar;
    public static Boolean status = false;
    @FXML
    private StackPane mediaViewPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        file = new File("C:\\Users\\MSI\\Downloads\\opening.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setMute(true);
        mediaView.setMediaPlayer(mediaPlayer);
        InvalidationListener resizeMediaView = observable -> {
    mediaView.setFitWidth(mediaViewPane.getWidth());
    mediaView.setFitHeight(mediaViewPane.getHeight());

    // After setting a big fit width and height, the layout bounds match the video size. Not sure why and this feels fragile.
    Bounds actualVideoSize = mediaView.getLayoutBounds();
    mediaView.setX((1000 - actualVideoSize.getWidth()) / 2);
    mediaView.setY((200 - actualVideoSize.getHeight()) / 2);
};
mediaViewPane.heightProperty().addListener(resizeMediaView);
mediaViewPane.widthProperty().addListener(resizeMediaView);

        if (status == true) {
            mainpane.setStyle("-fx-background-color: #FFFFFF;");
            secondpane.setStyle("-fx-background-color: #FFFFFF;");
            btnEditMode.setText("switch to dark mode");
            btnEditMode.setStyle("-fx-text-fill: #000000");
            hboxnavbar.setStyle("-fx-background-color: #FFFFFF;");
            sallefxid.setStyle("-fx-text-fill:#000000;");
            eventfxid.setStyle("-fx-text-fill:#000000;");
            produitfxid.setStyle("-fx-text-fill: #000000;");
            reclamationfxid.setStyle("-fx-text-fill: #000000;");
            titlefxid.setStyle("-fx-text-fill: #000000;");
            hboxsport.setStyle("-fx-background-color: #000000;");
            namefxid.setStyle("-fx-background-color: #FFFFFF;");
            //  namefxid.setStyle("-fx-text-fill: #000000;");
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
    @FXML
    private void home(MouseEvent event) throws IOException {
         makeFadeOut("HomeFront.fxml");
    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeOut("Evenement.fxml");

    }

    private void makeFadeOut(String a) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            try {
                Stage stage = (Stage) mainpane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource(a));/* Exception */
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fadeTransition.play();
    }

    @FXML
    private void pause(MouseEvent event) {
        mediaPlayer.pause();
    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) throws IOException {
        if (btnEditMode.isSelected()) {
            status = true;
            mainpane.setStyle("-fx-background-color: #FFFFFF;");
            secondpane.setStyle("-fx-background-color: #FFFFFF;");
            btnEditMode.setText("switch to dark mode");
            btnEditMode.setStyle("-fx-text-fill: #000000");
            hboxnavbar.setStyle("-fx-background-color: #FFFFFF;");
            sallefxid.setStyle("-fx-text-fill:#000000;");
            eventfxid.setStyle("-fx-text-fill:#000000;");
            produitfxid.setStyle("-fx-text-fill: #000000;");
            reclamationfxid.setStyle("-fx-text-fill: #000000;");
            titlefxid.setStyle("-fx-text-fill: #000000;");
            hboxsport.setStyle("-fx-background-color: #000000;");
            namefxid.setStyle("-fx-background-color: #FFFFFF;");
            //  namefxid.setStyle("-fx-text-fill: #000000;");

        } else {
            status = false;

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("HomeFront.fxml"));/* Exception */
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        }
    }

    @FXML
    private void salle(MouseEvent event) {
         makeFadeOut("Salle.fxml");
    }

    @FXML
    private void produit(MouseEvent event) throws IOException {
        makeFadeOut("Produit.fxml");
    }

    @FXML
    private void reclamation(MouseEvent event) {
      makeFadeOut("ReclamationFront.fxml");
    }

    @FXML
    private void profile(MouseEvent event) {
    }

}
