/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUIBACK;

import GUI.ReplyReclamationController;
import Entities.Reclamation;
import Entities.User;
import Services.ReclamationService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static energym.desktop.MainFX.UserconnectedC;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

import java.util.*;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class ReclamationController implements Initializable {

    @FXML
    private JFXButton closebtn;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton homebtn;
    private JFXButton addbtn;

    private JFXTextField searchfield;
    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane toppane;
    @FXML
    private AnchorPane moviepane;
    private AnchorPane tablepane;
    private ImageView movieimage;
    @FXML
    private Label movietitle;
    private Label movierating;
    private Label movieduration;

    private String[] movienames;
    private boolean faded = false, showingall = false;
    public static String currentmovie = "";
    private int currentslots = 0;
    public static int slotseatNo = -1;

    @FXML
    private ImageView userimage;
    @FXML
    private JFXTextField recherchetf;
    private Label emaillabel;
    private Label dn;
    private Label role;
    private Label createdat;
    ObservableList<String> ss = FXCollections.observableArrayList();
    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    UserService us = new UserService();
    private TableColumn<?, ?> createdat_col;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    ReclamationService rs = new ReclamationService();
    Reclamation reclamation = null;
    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, String> produit_col1;
    @FXML
    private Label senderlabel;
    @FXML
    private Label titrelabel;
    @FXML
    private Label contenulabel;
    @FXML
    private Label produitlabel;
    @FXML
    private TableColumn<User, String> sender_col;
    @FXML
    private TableColumn<?, ?> titre_col;
    @FXML
    private TableColumn<?, ?> created_at_col_rec;
    @FXML
    private TableColumn<?, ?> contenu_col;
    @FXML
    private TableColumn<?, ?> statut_col;
    @FXML
    private TableColumn<?, ?> produit_col;
    @FXML
    private TableView<Reclamation> tableviewreclamation;
    @FXML
    private Label repondufid;
    @FXML
    private Label encoursfid;
    @FXML
    private AnchorPane name_last_name;
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
    private JFXButton btnevenement;
    @FXML
    private JFXButton btncategoriesevent;
    @FXML
    private JFXButton btnparticipation;
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private JFXButton btnarticle;
    @FXML
    private JFXButton btncommentaire;
    @FXML
    private JFXButton btncommande;
    @FXML
    private JFXButton btnlivraison;

    /**
     * Initialise method required for implementing initializable and, sets up
     * and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (UserconnectedC.getRoles().equals("ROLE_GERANT")) {
           btnevenement.setVisible(false);
            btnproduit.setVisible(false);
            btncategories.setVisible(false);
            btncategoriesevent.setVisible(false);
            btnparticipation.setVisible(false);
            btncommande.setVisible(false);
            btnlivraison.setVisible(false);
            btncommentaire.setVisible(false);
            btnarticle.setVisible(false);
        }
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));
        encoursfid.setText((Integer.toString(rs.affichernumber("encours"))));

        loadDate();
        recherche_avance();
        setCellValueFromTableToTextField();

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));
        encoursfid.setText((Integer.toString(rs.affichernumber("encours"))));
        //  sender_col.setCellValueFactory(cellData -> new SimpleStringProperty(us.findById(cellData.getValue().getId())));
        sender_col.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        titre_col.setCellValueFactory(new PropertyValueFactory<>("titre"));

        contenu_col.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        produit_col.setCellValueFactory(new PropertyValueFactory<>("produit"));
//String age =calculateAge(new PropertyValueFactory<>("birthday")) ;
        created_at_col_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
        statut_col.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tableviewreclamation.setItems(data);
    }

    @FXML
    private void handleClicks(ActionEvent event) throws IOException {
        if (event.getSource() == btnUsers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Users.fxml"));
            mainmoviespane.getChildren().setAll(panee);

        }
        if (event.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnReply) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reply.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("HomeBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnsalle) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Salle.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncours) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Cours.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncategories) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Categories.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnproduit) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Produit.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnevenement) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("EvenementBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncategoriesevent) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("CategoriesEventBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnparticipation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Participation.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnarticle) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Article.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncommentaire) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Commentaire.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btncommande) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Commande.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (event.getSource() == btnlivraison) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Livraison.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
    }

    public void disableAllFocus(Pane pane) {
        for (Node n : pane.getChildren()) {
            if (n instanceof JFXButton) {
                ((JFXButton) n).setDisableVisualFocus(true);
            } else if ((n instanceof AnchorPane) || (n instanceof HBox)) {
                disableAllFocus((Pane) n);
            }
        }
    }

    @FXML
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<Reclamation> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((Reclamation reclamation) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (reclamation.getTitre().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getStatut().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (reclamation.getContenu().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<Reclamation> sorteddata = new SortedList<>(filtereddata);
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
        data = FXCollections.observableArrayList(rs.afficher());
        sender_col.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        titre_col.setCellValueFactory(new PropertyValueFactory<>("titre"));
        contenu_col.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        created_at_col_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
        produit_col.setCellValueFactory(new PropertyValueFactory<>("produit"));

        statut_col.setCellValueFactory(new PropertyValueFactory<>("statut"));
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));
        encoursfid.setText((Integer.toString(rs.affichernumber("encours"))));
        //add cell of button edit 
        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                reclamation = tableviewreclamation.getSelectionModel().getSelectedItem();
                                Alert alert = new Alert(AlertType.CONFIRMATION);
                                alert.setTitle("Confirmation Dialog");
                                alert.setContentText("Etes vous sure de supprimer cette element ?");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {

                                    ReclamationService rs = new ReclamationService();
                                    rs.supprimer((int) reclamation.getId());

                                    refreshlist();
                                } else {

                                    Alert alert1 = new Alert(AlertType.WARNING);
                                    alert1.setTitle("Information Dialog");
                                    alert1.setHeaderText(null);
                                    alert1.setContentText("Veuillez sélectionner un element à supprimer.");

                                    alert1.showAndWait();
                                }
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            reclamation = tableviewreclamation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/ReplyReclamation.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            ReplyReclamationController addReclamationController = loader.getController();
                            System.out.println("rec" + reclamation.getId() + "user" + reclamation.getNomUser());
                            addReclamationController.parameter((int) reclamation.getId(), reclamation.getNomUser());
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            stage = (Stage) editIcon.getScene().getWindow();
                            stage.close();
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
        produit_col1.setCellFactory(cellFoctory);
        tableviewreclamation.setItems(ReclamationList);

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
                Reclamation u = tableviewreclamation.getItems().get(tableviewreclamation.getSelectionModel().getSelectedIndex());
                UserService us = new UserService();
                String email = us.findById(u.getNomUser());
                String image = us.findByIdimage(u.getNomUser());
                User user = us.findByUsername(email);
                movietitle.setText(user.getNom() + " " + user.getPrenom());
                senderlabel.setText(email);
                titrelabel.setText(u.getTitre());
                contenulabel.setText(u.getContenu());
                produitlabel.setText("produit");
                userimage.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + image));

            }
        });
    }

}
