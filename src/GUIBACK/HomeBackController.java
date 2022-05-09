/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class HomeBackController implements Initializable {

    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    @FXML
    private AnchorPane toppane;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton closebtn;
    @FXML
    private JFXTabPane tabpane;
    @FXML
    private JFXButton s1issuebtn;
    @FXML
    private AnchorPane s1tile6;
    @FXML
    private AnchorPane s1tile5;
    @FXML
    private Label s1timeslot;
    @FXML
    private AnchorPane s1tile4;
    @FXML
    private Label s1timeremaining;
    @FXML
    private AnchorPane s1tile3;
    @FXML
    private Label s1rating;
    @FXML
    private AnchorPane s1tile2;
    @FXML
    private Label s1repeatsleft;
    @FXML
    private AnchorPane s1tile1;
    @FXML
    private Label s1availableseats;
    @FXML
    private AnchorPane s1infopane;
    @FXML
    private Label s1moviename;
    @FXML
    private Label s1movieduration;
    @FXML
    private Label s1nextmovie;
    @FXML
    private Label s1status;
    @FXML
    private ImageView s1movieimage;
    @FXML
    private JFXButton s2issuebtn;
    @FXML
    private AnchorPane s2infopane;
    @FXML
    private Label s2moviename;
    @FXML
    private Label s2movieduration;
    @FXML
    private Label s2nextmovie;
    @FXML
    private Label s2status;
    @FXML
    private AnchorPane s2tile1;
    @FXML
    private Label s2availableseats;
    @FXML
    private AnchorPane s2tile4;
    @FXML
    private Label s2timeremaining;
    @FXML
    private AnchorPane s2tile2;
    @FXML
    private Label s2repeatsleft;
    @FXML
    private AnchorPane s2tile5;
    @FXML
    private Label s2timeslot;
    @FXML
    private AnchorPane s2tile3;
    @FXML
    private Label s2rating;
    @FXML
    private AnchorPane s2tile6;
    @FXML
    private ImageView s2movieimage;
    @FXML
    private JFXButton s3issuebtn;
    @FXML
    private AnchorPane s3infopane;
    @FXML
    private Label s3moviename;
    @FXML
    private Label s3movieduration;
    @FXML
    private Label s3nextmovie;
    @FXML
    private Label s3status;
    @FXML
    private AnchorPane s3tile1;
    @FXML
    private Label s3availableseats;
    @FXML
    private AnchorPane s3tile4;
    @FXML
    private Label s3timeremaining;
    @FXML
    private AnchorPane s3tile2;
    @FXML
    private Label s3repeatsleft;
    @FXML
    private AnchorPane s3tile5;
    @FXML
    private Label s3timeslot;
    @FXML
    private AnchorPane s3tile3;
    @FXML
    private Label s3rating;
    @FXML
    private AnchorPane s3tile6;
    @FXML
    private ImageView s3movieimage;
    @FXML
    private AnchorPane p1;
    @FXML
    private AnchorPane p1shadow;
    @FXML
    private AnchorPane p2;
    @FXML
    private AnchorPane p2shadow;
    @FXML
    private AnchorPane p3;
    @FXML
    private AnchorPane p3shadow;
    @FXML
    private Label datelabel;
    @FXML
    private Label timelabel;
    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane leftpane1;
    @FXML
    private JFXButton logoutbtn1;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
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
            mainmoviespane.getChildren().setAll(panee);

        }
        if (actionEvent.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnReply) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reply.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnsalle) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Salle.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btncours) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Cours.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
         if (actionEvent.getSource() == btncategories) {
             AnchorPane panee = FXMLLoader.load(getClass().getResource("Categories.fxml"));
             mainmoviespane.getChildren().setAll(panee);
         }
         if (actionEvent.getSource() == btnproduit) {
             AnchorPane panee = FXMLLoader.load(getClass().getResource("Produit.fxml"));
             mainmoviespane.getChildren().setAll(panee);
         }
                if (actionEvent.getSource() == btnevenement) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("EvenementBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btncategoriesevent) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("CategoriesEventBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnparticipation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Participation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }

    }
    @FXML
    private void logOut(ActionEvent event) {
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void viewMovieScreens(MouseEvent event) {
    }

    @FXML
    private void tileExit(MouseEvent event) {
    }

    @FXML
    private void tileHover(MouseEvent event) {
    }

    @FXML
    private void rotatePane(MouseEvent event) {
    }

 
    
}
