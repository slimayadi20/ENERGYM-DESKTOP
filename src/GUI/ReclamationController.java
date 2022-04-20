/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.User;
import Services.ReclamationService;
import Services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import static energym.desktop.MainClass.UserconnectedC;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ReclamationController implements Initializable {

    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnReclamation;
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
    private TextField recherchetf;
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
    private AnchorPane DashboardUtilis;
    @FXML
    private Label currentTimeTF1;
    ReclamationService rs = new ReclamationService();
    Reclamation reclamation = null;
    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    @FXML
    private ImageView add;
    ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation, String> produit_col1;
    @FXML
    private Label repondufid;
    @FXML
    private Label encoursfid;
    @FXML
    private Button btnProfile;
    private ImageView image;
    @FXML
    private Label emailtf;
    @FXML
    private Circle circleu1;
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));
        encoursfid.setText((Integer.toString(rs.affichernumber("encours"))));
        File file = new File("C:\\xampp\\htdocs\\img\\" + UserconnectedC.getImageFile());

        try {
            circleu1.setFill(new ImagePattern(new Image(file.toURI().toURL().toExternalForm())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        emailtf.setText(UserconnectedC.getNom() + " " + UserconnectedC.getPrenom());
        loadDate();
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

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());
        repondufid.setText((Integer.toString(rs.affichernumber("repondu"))));
        encoursfid.setText((Integer.toString(rs.affichernumber("encours"))));
      //  sender_col.setCellValueFactory(cellData -> new SimpleStringProperty(us.findById(cellData.getValue().getId())));

         sender_col.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        titre_col.setCellValueFactory(new PropertyValueFactory<>("titre"));

        contenu_col.setCellValueFactory(new PropertyValueFactory<>("contenu"));
//String age =calculateAge(new PropertyValueFactory<>("birthday")) ;
        created_at_col_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
        statut_col.setCellValueFactory(new PropertyValueFactory<>("statut"));
        tableviewreclamation.setItems(data);
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
    private void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("User.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //  pnlCustomer.setStyle("-fx-background-color : #1620A1");
            //  pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnReclamation) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Reclamation.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
            //    btnReclamation.setStyle("-fx-background-color :#02030A");
            //    pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnProfile) {
            AnchorPane panee = FXMLLoader.load(getClass().getResource("Profile.fxml"));
            DashboardUtilis.getChildren().setAll(panee);
        }
    }

    @FXML
    private void getAddView(MouseEvent event) {
    }

    private void loadDate() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());
        sender_col.setCellValueFactory(new PropertyValueFactory<>("NomUser"));
        titre_col.setCellValueFactory(new PropertyValueFactory<>("titre"));
     //   sender_col.setCellValueFactory(cellData -> new SimpleStringProperty(us.findById(cellData.getValue().getId())));

        contenu_col.setCellValueFactory(new PropertyValueFactory<>("contenu"));
//String age =calculateAge(new PropertyValueFactory<>("birthday")) ;
        created_at_col_rec.setCellValueFactory(new PropertyValueFactory<>("date"));
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
                                ReclamationService rs = new ReclamationService();
                                rs.supprimer((int) reclamation.getId());

                                refreshlist();
                            } catch (Exception ex) {
                                System.out.println(ex.getMessage());
                            }

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {

                            reclamation = tableviewreclamation.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/GUI/addReclamation.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddReclamationController addReclamationController = loader.getController();
                            addReclamationController.parameter(reclamation.getId(), reclamation.getNomUser());
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
        produit_col1.setCellFactory(cellFoctory);
        tableviewreclamation.setItems(ReclamationList);

    }

    @FXML
    private void logout(MouseEvent event) {
    }
}
