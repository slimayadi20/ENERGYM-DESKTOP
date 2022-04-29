/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.CategoriesEvent;
import Entities.Event;
import Services.CategoriesEventService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CategoriesEventBack implements Initializable {

    @FXML
    private AnchorPane mainmoviespane;
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
    private AnchorPane toppane;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton closebtn;
    @FXML
    private AnchorPane moviepane;
    @FXML
    private JFXTextField contenufid;
    @FXML
    private ImageView nomCheckmark;
    @FXML
    private Label labelnom;
    @FXML
    private JFXTextField recherchetf;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton clear;
    @FXML
    private Label image;
    @FXML
    private TableView<CategoriesEvent> tableviewCategoriesEvent;
    @FXML
    private TableColumn<?, ?> Id_col;
    @FXML
    private TableColumn<?, ?> nom_col;
    @FXML
    private TableColumn<CategoriesEvent, String> action_col;
    CategoriesEventService rs = new CategoriesEventService();
    CategoriesEvent CategoriesEvent = null;
    ObservableList<CategoriesEvent> data = FXCollections.observableArrayList();
    ObservableList<CategoriesEvent> CategoriesEventList = FXCollections.observableArrayList();
    static String cat;
    private boolean update;
    CategoriesEventService ces = new CategoriesEventService();
    String query = null;
    int categorieseventid;
    @FXML
    private ImageView Ar;
    @FXML
    private ImageView Fr;

    public static String Langue = "Fr";
    @FXML
    private Label NomLabel;
    @FXML
    private Label categ;
    @FXML
    private Label energym;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCellValueFromTableToTextField();
        loadDate();
        contenufid.setEditable(false);
        

        // TODO
    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());

        Id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomCategories"));

        tableviewCategoriesEvent.setItems(data);
    }

    @FXML
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<CategoriesEvent> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((CategoriesEvent CategoriesEvent) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (CategoriesEvent.getNomCategories().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<CategoriesEvent> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewCategoriesEvent.comparatorProperty());
        tableviewCategoriesEvent.setItems(filtereddata);
    }

    // for add event
    public static String getCat() {
        return cat;
    }

    private void loadDate() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());
        try {
            tableviewCategoriesEvent.setOnMousePressed((MouseEvent event) -> {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 3) {
                    cat = tableviewCategoriesEvent.getSelectionModel().getSelectedItem().getNomCategories();
                    Stage stage = (Stage) tableviewCategoriesEvent.getScene().getWindow();
                    stage.close();
                }
            });
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(data);
        Id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nomCategories"));

        //add cell of button edit 
        Callback<TableColumn<CategoriesEvent, String>, TableCell<CategoriesEvent, String>> cellFoctory = (TableColumn<CategoriesEvent, String> param) -> {
            // make cell containing buttons
            final TableCell<CategoriesEvent, String> cell = new TableCell<CategoriesEvent, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                CategoriesEvent = tableviewCategoriesEvent.getSelectionModel().getSelectedItem();
                                CategoriesEventService rs = new CategoriesEventService();
                                rs.supprimer((int) CategoriesEvent.getId());

                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
        action_col.setCellFactory(cellFoctory);
        tableviewCategoriesEvent.setItems(data);

    }

    private void loadData(MouseEvent event) {
        refreshlist();
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

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
        if (btnEditMode.isSelected()) {
            contenufid.setEditable(true);

        } else {
            btnEditMode.setStyle("");
            contenufid.setEditable(false);

        }

    }

    @FXML
    private void add(ActionEvent event) {
        StringBuilder errors = new StringBuilder();

        if (contenufid.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = contenufid.getText();

            getQuery(nom);
            contenufid.setText("");

        }
    }

    @FXML
    private void clear(ActionEvent event) {
        contenufid.setText("");

    }

    private void getQuery(String nom) {

        if (update == false) {

            ces.ajouterCategoriesEvent(nom);
        } else {
            ces.modifier(categorieseventid, nom);
        }

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    private void setCellValueFromTableToTextField() {
        tableviewCategoriesEvent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                CategoriesEvent u = tableviewCategoriesEvent.getItems().get(tableviewCategoriesEvent.getSelectionModel().getSelectedIndex());
                categorieseventid = u.getId();
                contenufid.setText(u.getNomCategories());
                setUpdate(true);
            }
        });
    }

    @FXML
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnUsers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Users.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //  pnlCustomer.setStyle("-fx-background-color : #1620A1");
            //  pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("EvenementBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#02030A");
            //    pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnReply) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("CategoriesEventBack.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == homebtn) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("home.fxml"));
            mainmoviespane.getChildren().setAll(panee);
        }

    }

    @FXML
    private void LangueAr(MouseEvent event) {
        
                this.Langue="Ar";
                Traduction();

    }

    @FXML
    private void LangueFr(MouseEvent event) {
        
        this.Langue="Fr";
        Traduction();
             
    }
    
    public void Traduction(){
        if ("Fr".equals(this.Langue))
        {
                NomLabel.setText("Nom:");
                recherchetf.setText("rechercher");
                btnEditMode.setText("Appuyer pour éditer");
                addbtn.setText("Ajouter");
                clear.setText("Nettoyer");
                homebtn.setText("Acceuil");
                btnProfile.setText("Profile");
                btnUsers.setText("Utilisateurs");
                btnReclamation.setText("Evénement");
                btnReply.setText("Catégorie des événements");
                logoutbtn.setText("Quitter");           
                userlabel.setText("Connecté avec:");
                categ.setText("Catégorie des événements");
                Id_col.setText("identifiant");
                nom_col.setText("Nom");
                action_col.setText("Actions");
                energym.setText("Energym");     
            
        }
        else {
              NomLabel.setText("اسم:");
                recherchetf.setText("للبحث");
                btnEditMode.setText("انقر للتعديل");
                addbtn.setText("يضيف");
                clear.setText("لينظف");
                homebtn.setText("ترحيب");
                btnProfile.setText("حساب تعريفي");
                btnUsers.setText("المستخدمون");
                btnReclamation.setText("الأحداث");
                btnReply.setText("فئة الأحداث");
                logoutbtn.setText("خروج");           
                userlabel.setText("متصل ب:");
                categ.setText("فئة الأحداث");
                Id_col.setText("المعرف");
                nom_col.setText("اسم");
                action_col.setText("أجراءات");
                energym.setText("إينر جيم");
        }
    }

}
