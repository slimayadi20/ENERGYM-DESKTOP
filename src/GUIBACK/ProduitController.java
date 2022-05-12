/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Categories;
import Entities.Reclamation;
import Entities.Produit;
import Services.ProduitService;
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
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nouri
 */
public class ProduitController implements Initializable {

    private Button btnCustomers;
    @FXML
    private Button btnReclamation;
    private Label repondufid;
    private Label encoursfid;

    @FXML
    private Label currentTimeTF1;
    ObservableList<Produit> data = FXCollections.observableArrayList();
    ProduitService ss = new ProduitService();
    @FXML
    private TableColumn<?, ?> nomfx;
    @FXML
    private TableColumn<?, ?> descriptionfx;
    @FXML
    private TableColumn<?, ?> prixfx;
    @FXML
    private TableColumn<Produit, Integer> imagefx;
    @FXML
    private TableColumn<Produit, Integer> quantitéfx;
    @FXML
    private TableColumn<Produit, String> NomCategoriesfx;
    @FXML
    private TableView<Produit> tableviewproduit;
    @FXML
    private Button btnproduit;
    @FXML
    private Button btncours;
    Produit produit = null;

    ObservableList<Produit> ProduitList = FXCollections.observableArrayList();
    @FXML
    private JFXButton add;
    @FXML
    private ImageView refresh;
    @FXML
    private TableColumn<Produit, String> actionfx;
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
    private AnchorPane leftpane1;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReply;
    @FXML
    private JFXButton btnsalle;
    @FXML
    private JFXButton btncategories;
    ProduitService ps = new ProduitService();
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
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        loadDate();

        initClock();

        // to load data 
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
        data = FXCollections.observableArrayList(ss.afficher());

        nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));

        imagefx.setCellValueFactory(new PropertyValueFactory<>("id"));

        prixfx.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantitéfx.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        NomCategoriesfx.setCellValueFactory(new PropertyValueFactory<>("NomCategories"));

        tableviewproduit.setItems(data);
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

    private void loadDate() {
        data.clear();
        data = FXCollections.observableArrayList(ss.afficher());
        nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));
        quantitéfx.setCellValueFactory(new PropertyValueFactory<>("id"));

        quantitéfx.setCellFactory(new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
            @Override
            public TableCell<Produit, Integer> call(
                    TableColumn<Produit, Integer> param) {
                return new TableCell<Produit, Integer>() {
                    protected void updateItem(Integer item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ProduitService Produit = new ProduitService();
                            int qte = Produit.getProduitqte(item);
                            System.out.println(qte);
                            if (qte > 50) {
                                setStyle("-fx-font-weight: bold");
                                setStyle("-fx-background-color: green");
                                setText(String.valueOf(qte));
                            } else if (qte < 50) {
                                setStyle("-fx-font-weight: bold");
                                setStyle("-fx-background-color: red");
                                setText(String.valueOf(qte));
                            }
                        }
                    }
                };
            }
        });

        imagefx.setCellValueFactory(new PropertyValueFactory<>("id"));

        Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>> cellFactoryImage
                = new Callback<TableColumn<Produit, Integer>, TableCell<Produit, Integer>>() {
            @Override
            public TableCell call(final TableColumn<Produit, Integer> param) {
                final TableCell<Produit, Integer> cell = new TableCell<Produit, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            ProduitService Produit = new ProduitService();
                            String image = Produit.findByIdimage(item);
                            ImageView imagev = new ImageView(new Image("file:C:\\xampp\\htdocs\\img\\" + image));
                            imagev.setFitHeight(90);
                            imagev.setFitWidth(150);
                            setGraphic(imagev);
                            setText(null);
                            //System.out.println(item);
                        }
                    }
                };
                return cell;
            }
        };
        imagefx.setCellFactory(cellFactoryImage);
        System.out.println("data" + data);
        prixfx.setCellValueFactory(new PropertyValueFactory<>("prix"));
        NomCategoriesfx.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getCategories(cellData.getValue().getNomCategories())));
        //    NomCategoriesfx.setCellValueFactory(new PropertyValueFactory<>("NomCategories"));
        // NomCategoriesfx.setCellValueFactory(cellData -> new SimpleStringProperty(ss.getProduit(cellData.getValue().getId())));

        //add cell of button edit 
        Callback<TableColumn<Produit, String>, TableCell<Produit, String>> cellFoctory = (TableColumn<Produit, String> param) -> {
            // make cell containing buttons
            final TableCell<Produit, String> cell = new TableCell<Produit, String>() {
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
                                produit = tableviewproduit.getSelectionModel().getSelectedItem();
                                ProduitService rs = new ProduitService();
                                rs.supprimer((int) produit.getId());

                                System.out.println(produit.getId());
                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            produit = tableviewproduit.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUIBACK/AddProduit.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddProduitController addProduitController = loader.getController();
                            addProduitController.setTextField(produit.getId(), produit.getNom(), produit.getDescription(), produit.getImage(), produit.getPrix(), produit.getQuantité(), produit.getNomCategories());
                            addProduitController.setUpdate(true);
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

        actionfx.setCellFactory(cellFoctory);
        tableviewproduit.setItems(data);
    }

    @FXML
    private void add(MouseEvent event) {
        produit = tableviewproduit.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIBACK/AddProduit.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddProduitController addProduitController = loader.getController();
        addProduitController.setUpdate(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void refreshfct(MouseEvent event) {
        loadDate();

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
    private void logOut(ActionEvent event) {
    }

}
