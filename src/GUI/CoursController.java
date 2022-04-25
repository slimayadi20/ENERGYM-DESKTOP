/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Cours;
import Entities.Salle;
import Services.CoursService;
import Services.SalleService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
public class CoursController implements Initializable {

    @FXML
    private AnchorPane DashboardUtilis;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnsalle;
    @FXML
    private Button btncours;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnSignout;
    @FXML
    private Pane pnlCustomer;
    @FXML
    private Pane pnlOrders;
    @FXML
    private Pane pnlMenus;
    @FXML
    private Pane pnlOverview;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView add;
    @FXML
    private TableView<Cours> tableviewsalle;
    @FXML
    private TableColumn<?, ?> nomfx;
    @FXML
    private TableColumn<?, ?> nomcoachfx;
    @FXML
    private TableColumn<Cours,String> sallefx;
    @FXML
    private TableColumn<?, ?> nombrefx;
    @FXML
    private TableColumn<?, ?> jourfx;
    @FXML
    private TableColumn<?, ?> heuredebutfx;
    @FXML
    private TableColumn<?, ?> heurefinfx;
    @FXML
    private TableColumn<Cours, String> actionsfx;
    @FXML
    private Label currentTimeTF1;
    @FXML
    private TextField recherchetf;

    ObservableList<Cours> data = FXCollections.observableArrayList();
    CoursService cs = new CoursService();
    SalleService ps = new SalleService();
    
    ObservableList<Cours> CoursList = FXCollections.observableArrayList();
     Cours cours = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          tableviewsalle.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // to load data 
        loadData();
         
        recherche_avance();
        initClock();
    }    
      private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            currentTimeTF1.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private void refreshfct(MouseEvent event) {
         loadData();

    }

    @FXML
    private void add(MouseEvent event) {
          cours = tableviewsalle.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/addCours.fxml"));
        try {
            loader.load();
        } catch (Exception ex) {
            ex.getMessage();
        }

        AddCoursController addCoursController = loader.getController();
        addCoursController.setUpdate(false);

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    private void recherche_avance() {
         System.out.println("*******************");

        FilteredList<Cours> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((Cours cours) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (cours.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (cours.getNom_coach().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } 
                 else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<Cours> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewsalle.comparatorProperty());
        tableviewsalle.setItems(filtereddata);
    }
       private void loadData() {
        data.clear();
        data = FXCollections.observableArrayList(cs.afficher());
        
           nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
           nomcoachfx.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
           sallefx.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getSalle(cellData.getValue().getSalleassocie_id())));
           nombrefx.setCellValueFactory(new PropertyValueFactory<>("nombre"));
           jourfx.setCellValueFactory(new PropertyValueFactory<>("jour"));
           heuredebutfx.setCellValueFactory(new PropertyValueFactory<>("heure_d"));
           heurefinfx.setCellValueFactory(new PropertyValueFactory<>("heure_f"));
     
      

        //add cell of button edit 
        Callback<TableColumn<Cours, String>, TableCell<Cours, String>> cellFoctory = (TableColumn<Cours, String> param) -> {
  
            // make cell containing buttons
            final TableCell<Cours, String> cell = new TableCell<Cours, String>() {
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
                                cours = tableviewsalle.getSelectionModel().getSelectedItem();
                                cs.supprimer((int) cours.getId());

                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            cours = tableviewsalle.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/AddCours.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddCoursController addCoursController = loader.getController();
                            addCoursController.setUpdate(true);
                            addCoursController.setTextField(cours.getId(), cours.getNom(), cours.getSalleassocie_id(), cours.getNom_coach(),cours.getNombre(),cours.getDescription(), cours.getImage(), cours.getJour(), cours.getHeure_d(),cours.getHeure_f());
               
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
        public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(cs.afficher());
        
        nomfx.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomcoachfx.setCellValueFactory(new PropertyValueFactory<>("nom_coach"));
        sallefx.setCellValueFactory(cellData -> new SimpleStringProperty(ps.getSalle(cellData.getValue().getSalleassocie_id())));
        nombrefx.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        jourfx.setCellValueFactory(new PropertyValueFactory<>("jour"));
        heuredebutfx.setCellValueFactory(new PropertyValueFactory<>("heure_d"));
         heurefinfx.setCellValueFactory(new PropertyValueFactory<>("heure_f"));
        tableviewsalle.setItems(data);
    }
        
   

    @FXML
    private void handleClicks(MouseEvent actionEvent) throws IOException {
        
      
        if (actionEvent.getSource() == btncours) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Cours.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
        if (actionEvent.getSource() == btnsalle) {
           AnchorPane panee = FXMLLoader.load(getClass().getResource("Salle.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
    }
    
     
  

}

 

