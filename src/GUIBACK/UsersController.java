/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUIBACK;

import Entities.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UsersController implements Initializable {

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
    private JFXComboBox<String> combotri;
    @FXML
    private TableView<User> tableviewuser;
    @FXML
    private TableColumn<User, String> nom_col;
    @FXML
    private TableColumn<User, String> prenom_col;
    @FXML
    private TableColumn<User, String> email_col;
    @FXML
    private TableColumn<User, String> date_col;
    @FXML
    private TableColumn<User, String> role_col;
    @FXML
    private TableColumn<User, Integer> image;

    @FXML
    private ImageView userimage;
    @FXML
    private JFXTextField recherchetf;
    @FXML
    private Label emaillabel;
    @FXML
    private Label dn;
    @FXML
    private Label role;
    @FXML
    private Label createdat;
    ObservableList<String> ss = FXCollections.observableArrayList();
    ObservableList<User> data = FXCollections.observableArrayList();
    UserService us = new UserService();
    @FXML
    private TableColumn<?, ?> createdat_col;
    @FXML
    private JFXButton btnProfile;
    @FXML
    private JFXButton btnUsers;
    @FXML
    private JFXButton btnReclamation;
    @FXML
    private JFXButton btnReply;
    @FXML
    private JFXButton btntri;
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
        if (UserconnectedC.getRoles().equals("ROLE_GERANT"))
{
    btnevenement.setVisible(false);
    btnproduit.setVisible(false);
    btncategories.setVisible(false);
    btncategoriesevent.setVisible(false);
    btnparticipation.setVisible(false);
}
        
        
        ss.add("Par role");
        ss.add("Par date");
        ss.add("Par status");
        //   userlabel.setText(UserconnectedC.getNom() + "  " + UserconnectedC.getPrenom());
        combotri.setItems(ss);
        refreshlist();
        recherche_avance();
        setCellValueFromTableToTextField();

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(us.afficher());
        nom_col.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
//slim.ayadi@esprit.tn
        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
        createdat_col.setCellValueFactory(new PropertyValueFactory<>("created_at"));
        //String age =calculateAge(new PropertyValueFactory<>("birthday")) ;
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        //Date date = new Date(System.currentTimeMillis());
        //    date_col.setCellValueFactory(cellData -> new SimpleStringProperty(getDateDiff(cellData.getValue().getBirthday(),date,TimeUnit.MINUTES)));
        //   System.out.println(date);
        date_col.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        image.setCellValueFactory(new PropertyValueFactory<>("id"));

        Callback<TableColumn<User, Integer>, TableCell<User, Integer>> cellFactoryImage
                = new Callback<TableColumn<User, Integer>, TableCell<User, Integer>>() {
            @Override
            public TableCell call(final TableColumn<User, Integer> param) {
                final TableCell<User, Integer> cell = new TableCell<User, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            UserService user = new UserService();
                            String image = user.findByIdimage(item);
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
        image.setCellFactory(cellFactoryImage);

        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactoryTime
                = //
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell call(final TableColumn<User, String> param) {
                final TableCell<User, String> cell = new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if ((empty) || (item == null)) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

                            try {
                                Calendar cal = Calendar.getInstance();
                                java.util.Date date = cal.getTime();
                                String todaysdate = dateFormat.format(date);

                                //  String db = dateFormat.format(item);
                                System.out.println(todaysdate);
                                System.out.println(item);
                                System.out.println("up");
                                LocalDate date1 = LocalDate.parse(item, formatter);
                                LocalDate date2 = LocalDate.parse(todaysdate, formatter);
                                long yearsBetween = ChronoUnit.YEARS.between(date1, date2);
                                setText(Long.toString(yearsBetween));
                            } catch (Exception ex) {
                                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                };
                return cell;
            }
        };

        date_col.setCellFactory(cellFactoryTime);
        role_col.setCellValueFactory(new PropertyValueFactory<>("Roles"));
        tableviewuser.setItems(data);
        tableviewuser.setStyle("-fx-font-weight: bold; -fx-font-size: 1.05em; ");

    }

    @FXML
    private void trilist(ActionEvent event) {
        if (combotri.getValue().equals("Par role")) {
            ObservableList<User> tri1 = FXCollections.observableArrayList();
            tri1 = FXCollections.observableArrayList(us.sortByRoles());
            tableviewuser.setItems(tri1);

        } else if (combotri.getValue().equals("Par date")) {
            ObservableList<User> tri2 = FXCollections.observableArrayList();
            tri2 = FXCollections.observableArrayList(us.sortByDate());
            tableviewuser.setItems(tri2);
        } else if (combotri.getValue().equals("Par status")) {
            ObservableList<User> tri3 = FXCollections.observableArrayList();
            tri3 = FXCollections.observableArrayList(us.sortByStatus());
            tableviewuser.setItems(tri3);
        }
    }

    @FXML
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<User> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((User user) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getPrenom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getNom().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getEmail().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(user.getPhone()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (user.getRoles().toString().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<User> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewuser.comparatorProperty());
        tableviewuser.setItems(filtereddata);
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
        tableviewuser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                User u = tableviewuser.getItems().get(tableviewuser.getSelectionModel().getSelectedIndex());
                movietitle.setText(u.getNom() + u.getPrenom());
                emaillabel.setText(u.getEmail());
                dn.setText(u.getBirthday());
                role.setText(u.getRoles());
                createdat.setText(u.getCreated_at().toString());
                userimage.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + u.getImageFile()));

            }
        });
    }

    @FXML
    private void fillforum(MouseEvent event) {
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
