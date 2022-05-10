/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.EvenementCalendrier;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
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
    private MediaView mediaView;
    @FXML
    private Label articlefxid;
    @FXML
    private BorderPane cell;
    @FXML
    private HBox hboxsport1;
    @FXML
    private HBox hboxsport11;
    @FXML
    private GridPane grid;
    @FXML
    private Label participationfxid;
    @FXML
    private Label evenementfxid;
    @FXML
    private Line line1;
    @FXML
    private Label a1;
    @FXML
    private Line line2;
    @FXML
    private Label a2;
    @FXML
    private Line line3;
    @FXML
    private Label a3;
    @FXML
    private Label a4;
    @FXML
    private Label a5;
    @FXML
    private Label a6;
    @FXML
    private Line line4;
    @FXML
    private Label a7;
    @FXML
    private Line line5;
    @FXML
    private Label a8;
    @FXML
    private Label a9;
    @FXML
    private Label a10;
    @FXML
    private Label a11;

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
            // changes
        }
        mainpane.setOpacity(0);
        makeFadeInTransition();
            VCalendar vCalendar = new VCalendar();
    
     Agenda a=new Agenda();
     a.applyCss();
        List<EvenementCalendrier> elements = participationToAppoitments();
        a.appointments().addAll(elements);
      /*ICalendarAgenda agenda = new ICalendarAgenda(vCalendar);
        BorderPane root = new BorderPane();*/
        
       cell.setCenter(a);
          EventService ths = new EventService();
        List<Event> listTH = ths.afficher2();

        int colonne = 0;
        int row = 1;
        try {
            for (Event t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventItemHome.fxml"));

                Pane anchorPane = fxmlLoader.load();

                EventItemControllerHome controller = fxmlLoader.getController();
                controller.setData(t);
                if (colonne == 3) {
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
    private  List<EvenementCalendrier> participationToAppoitments(){
        EventService participationDao=new EventService();
        List<Event> evenements=participationDao.fetchParticipationByUser(MainFX.UserconnectedC.getId());
        return EvenementCalendrier.appointmentsList(evenements);
        
    }
    private void makeFadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

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
    private void home(MouseEvent event) throws IOException {
        makeFadeInTransition("HomeFront.fxml");

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeInTransition("Evenement.fxml");
    }

    /*  private void makeFadeOut(String a) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(100));
        fadeTransition.setNode(mainpane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished((event) -> {
            try {
                Stage stage = (Stage) mainpane.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource(a)); Exception 
                Scene scene = new Scene(root);
                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(HomeFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        fadeTransition.play();
    }*/
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
        makeFadeInTransition("Salle.fxml");
    }

    @FXML
    private void produit(MouseEvent event) throws IOException {
        makeFadeInTransition("Produit.fxml");
    }

    @FXML
    private void reclamation(MouseEvent event) {
        makeFadeInTransition("ReclamationFront.fxml");
    }

    @FXML
    private void profile(MouseEvent event) {
    }

    @FXML
    private void article(MouseEvent event) {
                        makeFadeInTransition("Article.fxml");

    }


}
