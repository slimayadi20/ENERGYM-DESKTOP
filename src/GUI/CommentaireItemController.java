/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commentaire;
import Services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CommentaireItemController implements Initializable {

    @FXML
    private AnchorPane thematiqueItem;
    private Label id_commentaire;
    @FXML
    private Label lbl_commentaire;
    @FXML
    private Label userfxid;
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setData(Commentaire c){
        lbl_commentaire.setText(c.getContenu());
       // id_commentaire.setText(String.valueOf(c.getId()));
        userfxid.setText(us.findById(c.getUser_id()));
    }
} 
