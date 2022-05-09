/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Produit;
import Entities.Salle;
import Services.ProduitService;
import Services.SalleService;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ProduitController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    private Label quotes;
    @FXML
    private Label quote;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProduitService ths = new ProduitService();
        List<Produit> listTH = ths.afficher();

        int colonne = 0;
        int row = 1;
        try {
            for (Produit t : listTH) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitItem.fxml"));

                Pane anchorPane = fxmlLoader.load();

                ProduitItemController controller = fxmlLoader.getController();
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
        List<String> givenList = Arrays.asList("“If you want something you've never had, you must be willing to do something you've never done.”",
                " “The body achieves what the mind believes.”", "“Once you are exercising regularly, the hardest thing is to stop it.” ",
                "“If you don't make time for exercise, you'll probably have to make time for illness.”", "“Dead last finish is greater than did not finish, which trumps did not start.”",
                "“All progress takes place outside the comfort zone.”", "“What seems impossible today will one day become your warm-up.”",
                "“Go the extra mile. It’s never crowded.”");
        Random rand = new Random();
        String randomElement = givenList.get(rand.nextInt(givenList.size()));
        quote.setText(String.valueOf(randomElement));
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
    private void reclamation(MouseEvent event) throws IOException {
        makeFadeOut("ReclamationFront.fxml");

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
        makeFadeOut("Evenement.fxml");

    }

    @FXML
    private void home(MouseEvent event) {
        makeFadeOut("HomeFront.fxml");

    }

    @FXML
    private void salle(MouseEvent event) {
        makeFadeOut("Salle.fxml");

    }

    @FXML
    private void produit(MouseEvent event) {
        makeFadeOut("Produit.fxml");

    }

    @FXML
    private void profile(MouseEvent event) {
        makeFadeOut("ProfileFront.fxml");

    }


    @FXML
    private void btnEditModeToggle(MouseEvent event) {
    }

}
