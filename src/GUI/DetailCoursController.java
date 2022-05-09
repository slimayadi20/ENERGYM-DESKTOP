/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Salle;
import Services.CoursService;
import Services.SalleService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class DetailCoursController implements Initializable {

    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton closebtn;
    @FXML
    private AnchorPane tablepane;
    @FXML
    private AnchorPane SCREEN1;
    @FXML
    private AnchorPane SCREEN16;
    @FXML
    private GridPane mardi;
    @FXML
    private GridPane mercredi;
    @FXML
    private GridPane jeudi;
    @FXML
    private GridPane vendredi;
    @FXML
    private GridPane samedi;
    @FXML
    private GridPane dimanche;
    @FXML
    private AnchorPane SCREEN161;
    @FXML
    private AnchorPane SCREEN1614;
    @FXML
    private AnchorPane SCREEN1613;
    @FXML
    private AnchorPane SCREEN1612;
    @FXML
    private AnchorPane SCREEN1611;
    int salleid;
    private GridPane pane;
    @FXML
    private GridPane lundi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void tileExit(MouseEvent event) {
    }

    @FXML
    private void tileHover(MouseEvent event) {
    }

    public void setThematiqueId(int id) {
        this.salleid = id;

    }

    public void loadData() {
        //System.out.println("initData"+thematique_id);
        CoursService ths = new CoursService();

        List<Cours> listTH = ths.afficherbusalleid(salleid);
        int colonne = 0;
        int row = 1;
        int colonne1 = 0;
        int row1 = 1;
        int colonne2 = 0;
        int row2 = 1;
        int colonne3 = 0;
        int row3 = 1;
        int colonne4 = 0;
        int row4 = 1;
        int colonne5 = 0;
        int row5 = 1;
        int colonne6 = 0;
        int row6 = 1;

        System.out.println(listTH);

        try {

            for (Cours t : listTH) {
                if ("Lundi".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne == 3) {

                        colonne = 0;
                        row++;
                    }
                    lundi.add(anchorPane, colonne++, row);
                    lundi.setMinWidth(Region.USE_COMPUTED_SIZE);
                    lundi.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    lundi.setMaxWidth(Region.USE_PREF_SIZE);
                    lundi.setMinHeight(Region.USE_COMPUTED_SIZE);
                    lundi.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    lundi.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
                if ("Mardi".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne1 == 3) {

                        colonne1 = 0;
                        row1++;

                    }
                    mardi.add(anchorPane, colonne1++, row1);
                    mardi.setMinWidth(Region.USE_COMPUTED_SIZE);
                    mardi.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    mardi.setMaxWidth(Region.USE_PREF_SIZE);
                    mardi.setMinHeight(Region.USE_COMPUTED_SIZE);
                    mardi.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    mardi.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
                if ("Mercredi".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne2 == 3) {

                        colonne2 = 0;
                        row2++;

                    }
                    mercredi.add(anchorPane, colonne1++, row1);
                    mercredi.setMinWidth(Region.USE_COMPUTED_SIZE);
                    mercredi.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    mercredi.setMaxWidth(Region.USE_PREF_SIZE);
                    mercredi.setMinHeight(Region.USE_COMPUTED_SIZE);
                    mercredi.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    mercredi.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
                if ("Jeudi".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne3 == 3) {

                        colonne3 = 0;
                        row3++;

                    }
                    jeudi.add(anchorPane, colonne1++, row1);
                    jeudi.setMinWidth(Region.USE_COMPUTED_SIZE);
                    jeudi.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    jeudi.setMaxWidth(Region.USE_PREF_SIZE);
                    jeudi.setMinHeight(Region.USE_COMPUTED_SIZE);
                    jeudi.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    jeudi.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
                if ("Vendredi".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne4 == 3) {

                        colonne4 = 0;
                        row4++;

                    }
                    vendredi.add(anchorPane, colonne1++, row1);
                    vendredi.setMinWidth(Region.USE_COMPUTED_SIZE);
                    vendredi.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    vendredi.setMaxWidth(Region.USE_PREF_SIZE);
                    vendredi.setMinHeight(Region.USE_COMPUTED_SIZE);
                    vendredi.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    vendredi.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
                if ("Samedi".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne5 == 3) {

                        colonne5 = 0;
                        row5++;

                    }
                    samedi.add(anchorPane, colonne1++, row1);
                    samedi.setMinWidth(Region.USE_COMPUTED_SIZE);
                    samedi.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    samedi.setMaxWidth(Region.USE_PREF_SIZE);
                    samedi.setMinHeight(Region.USE_COMPUTED_SIZE);
                    samedi.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    samedi.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
                if ("Dimanche".equals(t.getJour())) {
                    System.out.println(t);

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("CoursItem.fxml"));

                    Pane anchorPane = fxmlLoader.load();

                    CoursItemController controller = fxmlLoader.getController();
                    controller.setData(t);
                    if (colonne6 == 3) {

                        colonne6 = 0;
                        row6++;

                    }
                    dimanche.add(anchorPane, colonne1++, row1);
                    dimanche.setMinWidth(Region.USE_COMPUTED_SIZE);
                    dimanche.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    dimanche.setMaxWidth(Region.USE_PREF_SIZE);
                    dimanche.setMinHeight(Region.USE_COMPUTED_SIZE);
                    dimanche.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    dimanche.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(12));
                    //break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void home(MouseEvent event) {
                Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Salle.fxml")); /* Exception */
              Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
