/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.Reply;
import Entities.User;
import Services.ReclamationService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ReplyReclamationController implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private AnchorPane selectPane;
    @FXML
    private JFXButton checkoutbtn;
    @FXML
    private Label ticketPriceLabel;
    @FXML
    private Label errorlabel;
    @FXML
    private JFXCheckBox checkbox;
    @FXML
    private JFXButton resetbtn;
    @FXML
    private AnchorPane moviePane;
    @FXML
    private Label datelabel;
    @FXML
    private Label timelabel;
    @FXML
    private ImageView movieImage;
    @FXML
    private JFXComboBox<?> movieCombo;
    @FXML
    private JFXButton viewscreensbtn;
    @FXML
    private Rectangle startImage;
    @FXML
    private Label timelabel1;
    @FXML
    private Label sender;
    @FXML
    private Label time;
    @FXML
    private Label title;
    @FXML
    private AnchorPane toppane;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton closebtn;
    @FXML
    private AnchorPane leftpane;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private Label userlabel;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    @FXML
    private JFXTextField titlefxid;
    @FXML
    private JFXTextArea messagefxd;
    int id;
    int nomUser;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void resetPage(ActionEvent event) {
        messagefxd.setText("");
        titlefxid.setText("");
    }

    @FXML
    private void movieDetails(ActionEvent event) {
    }

    @FXML
    private void checkTimeslots(ActionEvent event) {
        ReclamationService rs = new ReclamationService();
        UserService us = new UserService();
        String email = us.findById(nomUser);
        User u = us.findByUsername(email);
        Reclamation r = rs.afficherbyid(id);
        sender.setText(email);
        image.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + u.getImageFile()));
        time.setText(r.getDate().toString());
        title.setText(r.getTitre());
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void btnExit(MouseEvent event) {
    }

    @FXML
    private void btnHover(MouseEvent event) {
    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnUsers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Users.fxml"));
            mainpane.getChildren().setAll(panee);
            //  pnlCustomer.setStyle("-fx-background-color : #1620A1");
            //  pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            mainpane.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#02030A");
            //    pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainpane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnReply) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reply.fxml"));
            mainpane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("home.fxml"));
            mainpane.getChildren().setAll(panee);
        }

    }

    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void send(MouseEvent event) {
        String contenu = messagefxd.getText();
        String title = titlefxid.getText();
        Date date = new Date(System.currentTimeMillis());
        ReclamationService rs = new ReclamationService();
        UserService us = new UserService();
        String email = us.findById(nomUser);
        Reply t = new Reply(contenu, email, UserconnectedC.getEmail(), id);
        rs.ajouterReply(t, id);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/Reclamation.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    public float getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNomUser() {
        return nomUser;
    }

    public void setNomUser(int nomUser) {
        this.nomUser = nomUser;
    }

    void parameter(int id, int nomUser) {
        this.id = id;
        this.nomUser = nomUser;
    }
}
