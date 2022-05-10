/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Salle;
import Entities.User;
import Services.SalleService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.xml.ws.Action;
import static energym.desktop.MainFX.UserconnectedC;

/**
 * FXML Controller class
 *
 * @author eyaba
 */
public class SalleItemController {

    private Label lbl_thematique;
    private ImageView image_thematique;
    private Label id_thematique;
    private Parent root;
    @FXML
    private ImageView image;
    @FXML
    private Label name;
    private Label prename;
    @FXML
    private AnchorPane vboxfx;
    @FXML
    private Label salleid;
    @FXML
    private ImageView like;
    @FXML
    private Label nblike;
    private Salle salle;

    public void setData(Salle t) {
        salle = t;
        System.out.println("name" + t.getNom());
        System.out.println("image " + t.getImage());
        name.setText(t.getNom());
        image.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\" + t.getImage()));

        name.setOnMouseClicked((MouseEvent event) -> {
//**************************

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("detailSalleCours.fxml"));
                root = fxmlLoader.load();
                System.out.println("Hello World! " + t);

                DetailSalleCoursController controller = fxmlLoader.getController();

                controller.setThematiqueId(t.getId());
                controller.loadData();
                Stage window = (Stage) salleid.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(SalleItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

        nblike.setText(Integer.toString(t.getNblike()));
        SalleService ss = new SalleService();
        if (!ss.checkLike(UserconnectedC.getId(), t.getId())) {
            like.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\like.png"));
        } else {
            like.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\unlike.png"));
        }

    }

    @FXML
    private void like(MouseEvent event) {
        SalleService ss = new SalleService();
        if (!ss.checkLike(UserconnectedC.getId(), salle.getId())) {
            ss.ajouterLike(UserconnectedC.getId(), salle.getId());
            like.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\unlike.png"));
            nblike.setText(Integer.toString(ss.modifiernblike(salle.getId())));

        } else {
            like.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\like.png"));

            ss.supprimerLike(UserconnectedC.getId(), salle.getId());
            nblike.setText(Integer.toString(ss.modifiernblikeminus(salle.getId())));

        }
    }

}
