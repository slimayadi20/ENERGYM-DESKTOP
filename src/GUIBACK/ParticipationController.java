/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUIBACK;

import Entities.Event;
import Entities.Participation;
import Services.EventService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

import java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Pagination;

public class ParticipationController implements Initializable {

    @FXML
    private JFXButton homebtn;

    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane toppane;

    private boolean faded = false, showingall = false;
    public static String currentmovie = "";
    private int currentslots = 0;
    public static int slotseatNo = -1;

    ObservableList<String> ss = FXCollections.observableArrayList();
    EventService rs = new EventService();
    Event Participation = null;
    ObservableList<Participation> data = FXCollections.observableArrayList(rs.afficherParticipants());
    ObservableList<Participation> EventList = FXCollections.observableArrayList();
    UserService us = new UserService();
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    @FXML
    private TableView<Participation> tableviewevent;
    @FXML
    private TableColumn<?, ?> nomevent_col;

    File selectedFile;
    int categorieseventid;
    int eventid;

    String[] words = {"bibi", "ok", "menzah", "nasser"};
    Set<String> possibleWordSet = new HashSet<>();
    AutoCompletionBinding<String> autoCompletionBinding;
    @FXML
    private TableColumn<Participation, String> user_col;
    @FXML
    private TableColumn<Participation, String> event_col;
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
    @FXML
    private Pagination pagination;
    private final static int rowsPerPage = 2;

    /**
     * Initialise method required for implementing initializable and, sets up
     * and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user_col.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        nomevent_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        event_col.setCellValueFactory(new PropertyValueFactory<>("idevent"));
        pagination.setPageFactory(this::createPage);
        int nbr = (rs.affichernbParticipants() / rowsPerPage) + 1;
        pagination.setPageCount(nbr);
        //  loadDate();
    }

    private Node createPage(int pageIndex) {
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, data.size());

        tableviewevent.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
        return tableviewevent;
    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficherParticipants());

        user_col.setCellValueFactory(new PropertyValueFactory<>("iduser"));
        nomevent_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        event_col.setCellValueFactory(new PropertyValueFactory<>("idevent"));
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
        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
            UserconnectedC = null;
        } catch (IOException ex) {
            System.err.println("Erreur");
        }
    }

    private void loadDate() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficherParticipants());
        UserService us = new UserService();
        EventService es = new EventService();
        nomevent_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        user_col.setCellValueFactory(cellData -> new SimpleStringProperty(us.findById(cellData.getValue().getIduser())));
        event_col.setCellValueFactory(cellData -> new SimpleStringProperty(es.findByIdnom(cellData.getValue().getIdevent())));
        tableviewevent.setItems(data);

    }

    @FXML
    private void btnExit(MouseEvent event) {
    }

    @FXML
    private void btnHover(MouseEvent event) {
    }

}
