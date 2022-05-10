/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Commentaire;
import Services.CommentaireService;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CommentaireController implements Initializable {

    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane toppane;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton closebtn;
    @FXML
    private AnchorPane moviepane;
    @FXML
    private ImageView refresh;
    @FXML
    private Label currentTimeTF1;
    @FXML
    private TableView<Commentaire> tableviewcommentaire;
    @FXML
    private TableColumn<?, ?> articlecolumn;
    @FXML
    private TableColumn<?, ?> usercolumn;
    @FXML
    private TableColumn<?, ?> datecolumn;
    @FXML
    private TableColumn<?, ?> contenucolumn;
    @FXML
    private AnchorPane leftpane1;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton logoutbtn1;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    @FXML
    private JFXButton btnsalle;
    @FXML
    private JFXButton btncours;
    @FXML
    private JFXButton btnproduit;
    @FXML
    private JFXButton btncategories;
    @FXML
    private JFXButton btnevenement;
    @FXML
    private JFXButton btncategoriesevent;
    @FXML
    private JFXButton btnparticipation;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btncommentaire;
    ObservableList<Commentaire> data = FXCollections.observableArrayList();
    CommentaireService rs = new CommentaireService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshlist();
        // TODO
    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());

        articlecolumn.setCellValueFactory(new PropertyValueFactory<>("article_id"));
        usercolumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        contenucolumn.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        tableviewcommentaire.setItems(data);
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void refreshfct(MouseEvent event) {
        refreshlist();
    }

    @FXML
    private void btnExit(MouseEvent event) {
    }

    @FXML
    private void btnHover(MouseEvent event) {
    }

    @FXML
    private void handleClicks(ActionEvent event) {
    }

    @FXML
    private void logOut(ActionEvent event) {
    }

}
