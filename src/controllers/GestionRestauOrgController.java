/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class GestionRestauOrgController implements Initializable {

    @FXML
    private StackPane rootPaneM;
    @FXML
    private BorderPane border_pane;
    @FXML
    private StackPane stackSide;
    @FXML
    private Pane stackSide2;
    @FXML
    private Pane paneSide;
    @FXML
    private Pane PublicitePane;
    @FXML
    private Label idPub1;
    @FXML
    private ImageView pubImage1;
    @FXML
    private StackPane ajouterStackPane1;
    @FXML
    private Label ajouterOfrreDemandeLabel1;
    @FXML
    private JFXTextField titreInput1;
    @FXML
    private JFXTextField nbrePlatInput1;
    @FXML
    private JFXTextArea descpritionInput1;
    @FXML
    private JFXButton ajouterPublicationDon1;
    @FXML
    private StackPane ajouterStackPane;
    @FXML
    private Label ajouterOfrreDemandeLabel;
    @FXML
    private JFXTextField nbrePlatInput;
    @FXML
    private JFXTextArea descpritionInput;
    @FXML
    private JFXTextField titreInput;
    @FXML
    private JFXButton ajouterPublicationDon;
    @FXML
    private FlowPane FlowPanePublications;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OpenSidebar(MouseEvent event) {
    }

    @FXML
    private void traitementPublicite1(MouseEvent event) {
    }

    @FXML
    private void pubClickedOn(MouseEvent event) {
    }

    @FXML
    private void modifierPublication(ActionEvent event) {
    }

    @FXML
    private void ajouterPublication(ActionEvent event) {
    }

    @FXML
    private void transtitionPublicite(MouseEvent event) {
    }

    @FXML
    private void CloseSideBar(MouseEvent event) {
    }
    
}
