/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Livraison;
import Services.LivraisonService;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class LivraisonController implements Initializable {

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
    private TableView<Livraison> tableviewLivraison;
    @FXML
    private TableColumn<?, ?> commandecol;
    @FXML
    private TableColumn<?, ?> nomlivreurcol;
    @FXML
    private TableColumn<?, ?> datelivcol;
    @FXML
    private TableColumn<?, ?> etatcol;
    @FXML
    private TableColumn<Livraison, String> action_col;
    @FXML
    private AnchorPane leftpane1;
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
    @FXML
    private JFXButton btncommande;
    LivraisonService rs = new LivraisonService();
    Livraison Livraison = null;
    ObservableList<Livraison> data = FXCollections.observableArrayList();
    ObservableList<Livraison> LivraisonList = FXCollections.observableArrayList();
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private JFXButton btnlivraison;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
        initClock();
        // TODO
    }

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    @FXML
    private void refreshfct(MouseEvent event) {
    }

    @FXML
    private void btnExit(MouseEvent event) {
    }

    @FXML
    private void btnHover(MouseEvent event) {
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



    @FXML
    private void logOut(ActionEvent event) {
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            currentTimeTF1.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());

        datelivcol.setCellValueFactory(new PropertyValueFactory<>("date"));
        commandecol.setCellValueFactory(new PropertyValueFactory<>("commandeid"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nomlivreurcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableviewLivraison.setItems(data);
    }

    private void loadDate() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());
        System.out.println(data);
//        dateLivraison_col.setCellValueFactory(new PropertyValueFactory<>("dateLivraison"));
        datelivcol.setCellValueFactory(new PropertyValueFactory<>("date"));
        commandecol.setCellValueFactory(new PropertyValueFactory<>("commandeid"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        nomlivreurcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        //    lieuLivraison_col.setCellValueFactory(new PropertyValueFactory<>("lieuLivraison"));
        //  nbrplaceLivraison_col.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesLivraison"));

        //add cell of button edit 
        Callback<TableColumn<Livraison, String>, TableCell<Livraison, String>> cellFoctory = (TableColumn<Livraison, String> param) -> {
            // make cell containing buttons
            final TableCell<Livraison, String> cell = new TableCell<Livraison, String>() {
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
                        deleteIcon.setOnMouseClicked((MouseEvent Event) -> {

                            try {
                                Livraison = tableviewLivraison.getSelectionModel().getSelectedItem();
                                LivraisonService rs = new LivraisonService();
                                rs.supprimer((int) Livraison.getId());

                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent Event) -> {

                            Livraison = tableviewLivraison.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUIBACK/AddLivraison.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddLivraisonController addLivraisonController = loader.getController();

                            addLivraisonController.setTextField(Livraison.getId(), Livraison.getCommandeid(), Livraison.getNom(), Livraison.getDate(), Livraison.getEtat());
                            System.out.println(Livraison.getId());
                            System.out.println(Livraison.getEtat());
                            System.out.println(Livraison.getNom());
                            addLivraisonController.setUpdate(true);
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
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
        action_col.setCellFactory(cellFoctory);
        tableviewLivraison.setItems(data);

    }

    private void loadData(MouseEvent Event) {
        refreshlist();
    }

    @FXML
    private void add(MouseEvent event) {
        Livraison = tableviewLivraison.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIBACK/AddLivraison.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddLivraisonController addCommandeController = loader.getController();
        addCommandeController.setUpdate(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

}
