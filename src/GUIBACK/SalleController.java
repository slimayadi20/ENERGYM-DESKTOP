/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIBACK;

import Entities.Reclamation;
import Entities.Salle;
import Services.SalleService;

import com.jfoenix.controls.JFXButton;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static energym.desktop.MainFX.UserconnectedC;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class SalleController implements Initializable {

    @FXML
    private Button btnReclamation;
    @FXML
    private TextField recherchetf;
    private Label repondufid;
    @FXML
    private Label encoursfid;

    private Label currentTimeTF1;
    ObservableList<Salle> data = FXCollections.observableArrayList();
    SalleService ss = new SalleService();
    @FXML
    private TableColumn<?, ?> nomfx;
    @FXML
    private TableColumn<?, ?> adressefx;
    @FXML
    private TableColumn<?, ?> telfx;
    @FXML
    private TableColumn<?, ?> mailfx;
    @FXML
    private TableColumn<?, ?> descriptionfx;
    @FXML
    private TableColumn<Salle, String> actionsfx;
    @FXML
    private TableView<Salle> tableviewsalle;
    @FXML
    private Button btnsalle;
    @FXML
    private Button btncours;
    Salle salle = null;

    ObservableList<Salle> SalleList = FXCollections.observableArrayList();
    @FXML
    private JFXButton add;
    @FXML
    private ImageView refresh;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
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
    private Label prix1fx;
    @FXML
    private Label prix2fx;
    @FXML
    private Label prix3fx;
    @FXML
    private Label heureofx;
    @FXML
    private Label heureffx;
    @FXML
    private ImageView imagefx;
    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private JFXButton btnReply;
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
     * Initializes the controller class.
     */
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        loadDate();
        recherche_avance();
        setCellValueFromTableToTextField();

        // to load data 
    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(ss.afficher());

        nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
        adressefx.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        telfx.setCellValueFactory(new PropertyValueFactory<>("tel"));

        mailfx.setCellValueFactory(new PropertyValueFactory<>("mail"));
        descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableviewsalle.setItems(data);
    }

    @FXML
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<Salle> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((Salle salle) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (salle.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (salle.getAdresse().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (salle.getTel().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<Salle> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewsalle.comparatorProperty());
        tableviewsalle.setItems(filtereddata);
    }

    private void loadDate() {
        data.clear();
        if (UserconnectedC.getRoles().equals("ROLE_GERANT")) {
            data = FXCollections.observableArrayList(ss.affichergerant());
            nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adressefx.setCellValueFactory(new PropertyValueFactory<>("adresse"));

            telfx.setCellValueFactory(new PropertyValueFactory<>("tel"));

            mailfx.setCellValueFactory(new PropertyValueFactory<>("mail"));
            descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));

            //add cell of button edit 
            Callback<TableColumn<Salle, String>, TableCell<Salle, String>> cellFoctory = (TableColumn<Salle, String> param) -> {
                // make cell containing buttons
                final TableCell<Salle, String> cell = new TableCell<Salle, String>() {
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
                                    salle = tableviewsalle.getSelectionModel().getSelectedItem();
                                    SalleService rs = new SalleService();
                                    rs.supprimer((int) salle.getId());

                                    System.out.println(salle.getId());
                                    refreshlist();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }

                            });
                            editIcon.setOnMouseClicked((MouseEvent event) -> {

                                salle = tableviewsalle.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/GUIBACK/addSalle.fxml"));
                                try {
                                    loader.load();
                                } catch (Exception ex) {
                                    ex.getMessage();
                                }
                                SalleService rs = new SalleService();
                                salle = rs.findById(salle.getId());
                                AddSalleController addSalleController = loader.getController();
                                System.out.println("salle controller =" + salle.getUrl());
                                addSalleController.setTextField(salle.getId(), salle.getNom(), salle.getAdresse(), salle.getTel(), salle.getMail(), salle.getDescription(), salle.getImage(), salle.getPrix1(), salle.getPrix2(), salle.getPrix3(), salle.getHeureo(), salle.getHeuref(), salle.getUrl());
                                System.out.println(salle.getPrix2());
                                System.out.println(salle.getPrix3());
                                addSalleController.setUpdate(true);
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
            actionsfx.setCellFactory(cellFoctory);
            tableviewsalle.setItems(data);
        } else {
            data = FXCollections.observableArrayList(ss.afficher());
            nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adressefx.setCellValueFactory(new PropertyValueFactory<>("adresse"));

            telfx.setCellValueFactory(new PropertyValueFactory<>("tel"));

            mailfx.setCellValueFactory(new PropertyValueFactory<>("mail"));
            descriptionfx.setCellValueFactory(new PropertyValueFactory<>("description"));

            //add cell of button edit 
            Callback<TableColumn<Salle, String>, TableCell<Salle, String>> cellFoctory = (TableColumn<Salle, String> param) -> {
                // make cell containing buttons
                final TableCell<Salle, String> cell = new TableCell<Salle, String>() {
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
                                    salle = tableviewsalle.getSelectionModel().getSelectedItem();
                                    SalleService rs = new SalleService();
                                    rs.supprimer((int) salle.getId());

                                    System.out.println(salle.getId());
                                    refreshlist();
                                } catch (Exception ex) {
                                    System.out.println(ex.getMessage());
                                }

                            });
                            editIcon.setOnMouseClicked((MouseEvent event) -> {

                                salle = tableviewsalle.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/GUIBACK/addSalle.fxml"));
                                try {
                                    loader.load();
                                } catch (Exception ex) {
                                    ex.getMessage();
                                }
                                SalleService rs = new SalleService();
                                salle = rs.findById(salle.getId());
                                AddSalleController addSalleController = loader.getController();
                                System.out.println("salle controller =" + salle.getUrl());
                                addSalleController.setTextField(salle.getId(), salle.getNom(), salle.getAdresse(), salle.getTel(), salle.getMail(), salle.getDescription(), salle.getImage(), salle.getPrix1(), salle.getPrix2(), salle.getPrix3(), salle.getHeureo(), salle.getHeuref(), salle.getUrl());
                                System.out.println(salle.getPrix2());
                                System.out.println(salle.getPrix3());
                                addSalleController.setUpdate(true);
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
            actionsfx.setCellFactory(cellFoctory);
            tableviewsalle.setItems(data);
        }

    }

    @FXML
    private void refreshfct(MouseEvent event) {
        loadDate();

    }

    @FXML
    private void PDFSalle(MouseEvent event) throws FileNotFoundException, DocumentException, IOException {
             String filename = "";

        SalleService ec = new SalleService();
        String message = "Voici la liste des salles \n\n";

        String file_name = "src/css/liste_Salle.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Salle> Salle = ec.afficher();
        PdfPTable table = new PdfPTable(5);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom de la salle"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Description"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("adresse"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("mail"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Affiche de l'événement"));
        table.addCell(cl4);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Salle.size(); i++) {
            
            table.addCell("" + Salle.get(i).getNom());
            table.addCell("" + Salle.get(i).getDescription());
            table.addCell("" + Salle.get(i).getAdresse());
            table.addCell("" + Salle.get(i).getMail());
       
                filename = "src\\images\\" + Salle.get(i).getImage();
                System.out.println(filename);
                com.lowagie.text.Image img = com.lowagie.text.Image.getInstance(filename);
                img.scalePercent(24);// Creating an Image object 
                PdfPCell cell = new PdfPCell();
                cell.addElement(new Chunk(img, 5, -5));

                cell.setFixedHeight(80);
                cell.setPaddingTop(60);
                table.addCell(cell);
            
          //  table.addCell("" + Salle.get(i).getImage());

        }
        document.add(table);

        document.close();

        document.close();
        Desktop.getDesktop().open(new File("src/css/liste_Salle.pdf"));
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

    @FXML
    private void minimiseWindow(ActionEvent event) {
    }

    @FXML
    private void handleClose(ActionEvent event) {
    }

    private void setCellValueFromTableToTextField() {
        tableviewsalle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Salle u = tableviewsalle.getItems().get(tableviewsalle.getSelectionModel().getSelectedIndex());
                SalleService us = new SalleService();
                System.out.println(u);
                movietitle.setText(u.getNom());
                prix2fx.setText(u.getPrix2());
                prix1fx.setText(u.getPrix1());
                prix3fx.setText(u.getPrix3());
                heureofx.setText(u.getHeureo());
                heureffx.setText(u.getHeuref());

                movietitle.setText(u.getNom());
                imagefx.setImage(new Image("file:D:\\Nouveau dossier\\SAUVGARDE\\ENERGYM\\public\\uploads\\salle\\" + u.getImage()));

            }
        });
    }

    @FXML
    private void add(MouseEvent event) {
        salle = tableviewsalle.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUIBACK/addSalle.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddSalleController addSalleController = loader.getController();
        addSalleController.setUpdate(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
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

}
