/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUIBACK;

import Entities.Reclamation;
import Entities.Reply;
import GUI.*;
import Entities.User;
import Services.ReclamationService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ReplyController implements Initializable {

    @FXML
    private AnchorPane mainmoviespane;
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
    private AnchorPane moviepane;
    @FXML
    private AnchorPane name_last_name;
    @FXML
    private Label movietitle;
    @FXML
    private Label senderlabel;
    @FXML
    private Label titrelabel;
    @FXML
    private Label contenulabel;
    @FXML
    private Label produitlabel;
    @FXML
    private ImageView userimage;
    @FXML
    private JFXTextField recherchetf;
    @FXML
    private Label repondufid;
    @FXML
    private TableView<Reply> tableviewreclamation;
    @FXML
    private TableColumn<?, ?> reclamation_col;
    @FXML
    private TableColumn<?, ?> contenu_col;
    @FXML
    private TableColumn<?, ?> email_rec_col;
    @FXML
    private TableColumn<?, ?> email_sender_col;

    ReclamationService rs = new ReclamationService();
    Reply reclamation = null;
    ObservableList<Reply> data = FXCollections.observableArrayList();

    ObservableList<Reply> ReclamationList = FXCollections.observableArrayList();

    UserService us = new UserService();
    @FXML
    private JFXButton btnsalle;
    @FXML
    private JFXButton btncours;
    @FXML
    private JFXButton btnproduit;
    @FXML
    private JFXButton btncategories;
    @FXML
    private AnchorPane leftpane1;
    @FXML
    private JFXButton logoutbtn1;
    @FXML
    private JFXButton btnevenement;
    @FXML
    private JFXButton btncategoriesevent;
    @FXML
    private JFXButton btnparticipation;

    /**
     * Initialise method required for implementing initializable and, sets up
     * and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));

        loadDate();
        recherche_avance();
        setCellValueFromTableToTextField();

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficherReply());
        System.out.println(rs.afficherReply());
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));
        //  sender_col.setCellValueFactory(cellData -> new SimpleStringProperty(us.findById(cellData.getValue().getId())));

        reclamation_col.setCellValueFactory(new PropertyValueFactory<>("reclamation"));
        contenu_col.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        email_rec_col.setCellValueFactory(new PropertyValueFactory<>("email_receiver"));
        email_sender_col.setCellValueFactory(new PropertyValueFactory<>("email_sender"));

        tableviewreclamation.setItems(data);
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
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<Reply> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((Reply reply) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (reclamation.getContenu().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getEmail_receiver().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getEmail_sender().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;

                } else if (String.valueOf(reclamation.getReclamation()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<Reply> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewreclamation.comparatorProperty());
        tableviewreclamation.setItems(filtereddata);
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
        data = FXCollections.observableArrayList(rs.afficherReply());
        reclamation_col.setCellValueFactory(new PropertyValueFactory<>("reclamation"));
        contenu_col.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        email_rec_col.setCellValueFactory(new PropertyValueFactory<>("email_receiver"));
        email_sender_col.setCellValueFactory(new PropertyValueFactory<>("email_sender"));
        System.out.println(rs.afficherReply());

        //add cell of button edit 
        Callback<TableColumn<Reply, String>, TableCell<Reply, String>> cellFoctory = (TableColumn<Reply, String> param) -> {
            // make cell containing buttons
            final TableCell<Reply, String> cell = new TableCell<Reply, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PLUS);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                reclamation = tableviewreclamation.getSelectionModel().getSelectedItem();
                                ReclamationService rs = new ReclamationService();
                                rs.supprimerReply((int) reclamation.getId());

                                loadDate();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );

                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            /*    reclamation = tableviewreclamation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/addReclamation.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            DetailrecController replyController = loader.getController();
                            replyController.parameter(reclamation.getId());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();*/
                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        tableviewreclamation.setItems(data);

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

    private void setCellValueFromTableToTextField() {
        tableviewreclamation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Reply u = tableviewreclamation.getItems().get(tableviewreclamation.getSelectionModel().getSelectedIndex());
                Reclamation r = rs.afficherbyid(u.getReclamation());
                UserService us = new UserService();
                String email = us.findById(r.getNomUser());
                String image = us.findByIdimage(r.getNomUser());
                User user = us.findByUsername(email);
                movietitle.setText(user.getNom() + " " + user.getPrenom());
                senderlabel.setText(email);
                titrelabel.setText(r.getTitre());
                contenulabel.setText(r.getContenu());
                produitlabel.setText("produit");
                userimage.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + image));

            }
        });
    }

}
