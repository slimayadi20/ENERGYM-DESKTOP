/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.Produit;
import Entities.Reply;
import Entities.User;
import Services.ProduitService;
import Services.ReclamationService;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.xml.ws.Action;

/**
 * FXML Controller class
 *
 * @author eyaba
 */
public class ReclamationItemController {

    private Label lbl_thematique;
    private ImageView image_thematique;
    private Label id_thematique;
    private Parent root;
    @FXML
    private AnchorPane thematiqueItem;
    @FXML
    private Label descriptionfxid;
    @FXML
    private Label datefxid;
    @FXML
    private Label titrefxid;
    @FXML
    private ImageView image;
    @FXML
    private ImageView reply;

    public void setData(Reclamation t) {

        titrefxid.setText(t.getTitre());
        ProduitService ps = new ProduitService();
        Produit p = ps.afficherbyid(t.getProduit());
        image.setImage(new Image("file:\\C:\\xampp\\htdocs\\img\\" + p.getImage()));

        datefxid.setText(String.valueOf(t.getDate()));
        descriptionfxid.setText(String.valueOf(t.getContenu()));
        ReclamationService rs = new ReclamationService();
        Reply r = rs.afficherReplybyid(t.getId());
        if (r.getReclamation()!= t.getId()) {
            reply.setVisible(false);
        }
        

    }
}
