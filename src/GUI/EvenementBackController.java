/** *****************************************************************************
 * Controller class and logic implementation for movies.fxml
 ***************************************************************************** */
package GUI;

import Entities.Reclamation;
import GUI.*;
import Entities.User;
import Entities.Event;
import Services.CategoriesEventService;
import Services.EventService;
import Services.ReclamationService;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.text.Font;

public class EvenementBackController implements Initializable {

    @FXML
    private JFXButton closebtn;
    @FXML
    private JFXButton minimisebtn;
    @FXML
    private JFXButton logoutbtn;
    @FXML
    private JFXButton homebtn;
    @FXML
    private JFXButton addbtn;

    @FXML
    private AnchorPane mainmoviespane;
    @FXML
    private AnchorPane leftpane;
    @FXML
    private AnchorPane toppane;
    @FXML
    private AnchorPane moviepane;
    @FXML
    private Label movietitle;
    @FXML
    private Label userlabel;

    private String[] movienames;
    private boolean faded = false, showingall = false;
    public static String currentmovie = "";
    private int currentslots = 0;
    public static int slotseatNo = -1;

    @FXML
    private ImageView userimage;
    @FXML
    private JFXTextField recherchetf;
    ObservableList<String> ss = FXCollections.observableArrayList();
    EventService rs = new EventService();
    Event Event = null;
    ObservableList<Event> data = FXCollections.observableArrayList();
    ObservableList<Event> EventList = FXCollections.observableArrayList();
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
    @FXML
    private JFXTextField nomfxid;
    @FXML
    private JFXTextField categfxid;
    @FXML
    private JFXTextField lieufxid;
    @FXML
    private JFXTextField nbrfxid;
    @FXML
    private JFXTextField descriptionfxid;
    @FXML
    private JFXDatePicker datefxid;
    @FXML
    private AnchorPane name_last_name;
    @FXML
    private ImageView nomCheckmark;
    @FXML
    private ImageView nombreCheckmark;
    @FXML
    private ImageView dateCheckmark;
    @FXML
    private ImageView DescriptionCheckmark;
    @FXML
    private ImageView adressecheck;
    @FXML
    private ImageView categcheck;
    @FXML
    private Label labelnom;
    @FXML
    private Label labelnbr;
    @FXML
    private Label labeldate;
    @FXML
    private Label labeldescription;
    @FXML
    private Label labeladdresse;
    @FXML
    private Label labelcateg;
    @FXML
    private JFXToggleButton btnEditMode;
    @FXML
    private TableView<Event> tableviewevent;
    @FXML
    private TableColumn<?, ?> nomevent_col;
    @FXML
    private TableColumn<?, ?> Categorie_col;
    @FXML
    private TableColumn<?, ?> dateevent_col;
    @FXML
    private TableColumn<?, ?> lieuevent_col;
    @FXML
    private TableColumn<?, ?> nbrplaceevent_col;
    @FXML
    private TableColumn<Event, String> action_col;
    @FXML
    private JFXButton clear;
    @FXML
    private Label image;
    File selectedFile;
    private String path;
    private boolean update;
    int categorieseventid;
    int eventid;
    @FXML
    private TableColumn<Event, Integer> image_col;
    @FXML
    private TableColumn<?, ?> etat_col;
    String[] words = {"bibi", "ok", "menzah", "nasser"};
    Set<String> possibleWordSet = new HashSet<>();
    AutoCompletionBinding<String> autoCompletionBinding;
    @FXML
    private JFXButton exportbtn;

    /**
     * Initialise method required for implementing initializable and, sets up
     * and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Collections.addAll(possibleWordSet, words);
        autoCompletionBinding = TextFields.bindAutoCompletion(lieufxid, possibleWordSet);

        lieufxid.setOnKeyPressed(
                (KeyEvent e) -> {
                    switch (e.getCode()) {
                        case ENTER:
                            learnWord((lieufxid.getText()));
                            break;
                        default:
                            break;
                    }
                }
        );

        loadDate();
        recherche_avance();
        btnEditMode.setStyle("");
        nomfxid.setEditable(false);
        categfxid.setEditable(false);
        datefxid.setEditable(false);
        lieufxid.setEditable(false);
        nbrfxid.setEditable(false);
        descriptionfxid.setEditable(false);

        setCellValueFromTableToTextField();
        nomfxid.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[a-zA-Z]*";

            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);

            if (controler.matches() && newValue.length() > 0) {
                nomCheckmark.setImage(new Image("images/checkMark.png"));
                labelnom.setText("");

            } else {
                nomCheckmark.setImage(new Image("images/erreurCheckMark.png"));
                labelnom.setText("remplir le nom qu'avec des caracteres ");

            }
        });
        nbrfxid.textProperty().addListener((observable, oldValue, newValue) -> {
            String masque = "[0-9]*";
            Pattern pattern = Pattern.compile(masque);
            Matcher controler = pattern.matcher(newValue);
            if (controler.matches()) {
                if (newValue.length() > 0) {
                    nombreCheckmark.setImage(new Image("images/checkMark.png"));
                    labelnbr.setText("");

                }

            } else {
                nombreCheckmark.setImage(new Image("images/erreurCheckMark.png"));
                labelnbr.setText("remplir le nombre qu'avec des chiffres ");

            }
        });
        descriptionfxid.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 0) {
                DescriptionCheckmark.setImage(new Image("images/checkMark.png"));
                labeldescription.setText("");

            } else {
                labeldescription.setText("ce champ doit etre rempli ");
                DescriptionCheckmark.setImage(new Image("images/erreurCheckMark.png"));

            }
        });
        lieufxid.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 0) {
                adressecheck.setImage(new Image("images/checkMark.png"));
                labeladdresse.setText("");

            } else {
                labeladdresse.setText("ce champ doit etre rempli ");

                adressecheck.setImage(new Image("images/erreurCheckMark.png"));

            }
        });
        categfxid.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 0) {
                categcheck.setImage(new Image("images/checkMark.png"));
                labelcateg.setText("");

            } else {
                labelcateg.setText("ce champ doit etre rempli ");

                categcheck.setImage(new Image("images/erreurCheckMark.png"));

            }
        });
        // date 

        exportbtn.setOnAction((actionEvent -> {
            exportbtn.setFont(Font.font("Sansserif", 15));
            String query = "select * from evenement";
            try {
                EventService e = new EventService();
                List<Event> event = e.afficher();

                XSSFWorkbook wb = new XSSFWorkbook();
                XSSFSheet sheet = wb.createSheet("List of events");
                XSSFRow header = sheet.createRow(0);

                header.createCell(0).setCellValue("nom :");
                header.createCell(1).setCellValue("date : ");
                header.createCell(2).setCellValue("etat : ");
                header.createCell(3).setCellValue("nbr de place : ");

                sheet.autoSizeColumn(1);
                sheet.autoSizeColumn(2);
                sheet.setColumnWidth(3, 256 * 25);//256-character width
                sheet.setZoom(150); //scale(150%)

                int index = 1;
                for (Event c : event) {
                    XSSFRow row = sheet.createRow(index);

                    row.createCell(0).setCellValue(c.getNomEvent());
                    row.createCell(1).setCellValue(c.getDateEvent());
                    row.createCell(2).setCellValue(c.getEtatEvent());
                    row.createCell(3).setCellValue(c.getNbrPlacesEvent());

                    index++;

                }
                FileOutputStream fileOut = new FileOutputStream("src/css/ListOfEvents.xlsx");
                wb.write(fileOut);
                fileOut.close();
//
//

            } catch (IOException ex) {
                System.err.println(ex.getMessage());

            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Products details exported to excel Sheet");
            alert.show();
        }));

    }

    private void learnWord(String text) {
        possibleWordSet.add(text);
        if (autoCompletionBinding != null) {
            autoCompletionBinding.dispose();
        }
        autoCompletionBinding = TextFields.bindAutoCompletion(lieufxid, possibleWordSet);

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficher());

        dateevent_col.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        Categorie_col.setCellValueFactory(new PropertyValueFactory<>("categories"));
        lieuevent_col.setCellValueFactory(new PropertyValueFactory<>("lieuEvent"));
        nbrplaceevent_col.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesEvent"));
        tableviewevent.setItems(data);
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
    public void recherche_avance() {
        System.out.println("*******************");

        FilteredList<Event> filtereddata = new FilteredList<>(data, b -> true);
        System.out.println(recherchetf.getText());
        recherchetf.textProperty().addListener((observable, oldvalue, newValue) -> {
            filtereddata.setPredicate((Event Event) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowercasefilter = newValue.toLowerCase();
                if (Event.getnomEvent().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                }
                if (Event.getLieuEvent().toLowerCase().indexOf(lowercasefilter) != -1) {
                    return true;
                } else if (String.valueOf(Event.getDateEvent()).indexOf(lowercasefilter) != -1) {
                    return true;

                } else if (String.valueOf(Event.getNbrPlacesEvent()).indexOf(lowercasefilter) != -1) {
                    return true;
                } else {
                    return false;
                }

            });

        });
        //System.out.println(filtereddata);
        SortedList<Event> sorteddata = new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(tableviewevent.comparatorProperty());
        tableviewevent.setItems(filtereddata);
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

//        dateevent_col.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        nomevent_col.setCellValueFactory(new PropertyValueFactory<>("nomEvent"));
        dateevent_col.setCellValueFactory(new PropertyValueFactory<>("dateEvent"));
        lieuevent_col.setCellValueFactory(new PropertyValueFactory<>("lieuEvent"));
        nbrplaceevent_col.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesEvent"));
        Categorie_col.setCellValueFactory(new PropertyValueFactory<>("categories"));
        etat_col.setCellValueFactory(new PropertyValueFactory<>("etatEvent"));
        image_col.setCellValueFactory(new PropertyValueFactory<>("id"));

        Callback<TableColumn<Event, Integer>, TableCell<Event, Integer>> cellFactoryImage
                = new Callback<TableColumn<Event, Integer>, TableCell<Event, Integer>>() {
            @Override
            public TableCell call(final TableColumn<Event, Integer> param) {
                final TableCell<Event, Integer> cell = new TableCell<Event, Integer>() {

                    @Override
                    public void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            EventService event = new EventService();
                            String image = event.findByIdimage(item);
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
        image_col.setCellFactory(cellFactoryImage);
        //    lieuevent_col.setCellValueFactory(new PropertyValueFactory<>("lieuEvent"));
        //  nbrplaceevent_col.setCellValueFactory(new PropertyValueFactory<>("nbrPlacesEvent"));

        //add cell of button edit 
        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFoctory = (TableColumn<Event, String> param) -> {
            // make cell containing buttons
            final TableCell<Event, String> cell = new TableCell<Event, String>() {
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
                                Event = tableviewevent.getSelectionModel().getSelectedItem();
                                EventService rs = new EventService();
                                rs.supprimer((int) Event.getId());

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
        tableviewevent.setItems(data);

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
        tableviewevent.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Event u = tableviewevent.getItems().get(tableviewevent.getSelectionModel().getSelectedIndex());
                nomfxid.setText(u.getNomEvent());
                lieufxid.setText(u.getLieuEvent());
                nbrfxid.setText(u.getNbrPlacesEvent());
                descriptionfxid.setText(u.getDescriptionEvent());
                LocalDate localDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(u.getDateEvent()));
                datefxid.setValue(localDate);
                setUpdate(true);
                categorieseventid = u.getCategories();
                CategoriesEventService ces = new CategoriesEventService();
                String c = ces.getbynom(categorieseventid);
                eventid = u.getId();
                categfxid.setText(c);
                userimage.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + u.getImageFile()));
                image.setText(u.getImageFile());

                //  movietitle.setText(user.getNom() + " " + user.getPrenom());
                //senderlabel.setText(email);
                //titrelabel.setText(u.getTitre());
                //contenulabel.setText(u.getContenu());
                //produitlabel.setText("produit");
                //userimage.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + image));
            }
        });
    }

    @FXML
    private void btnEditModeToggle(MouseEvent event) {
        if (btnEditMode.isSelected()) {
            nomfxid.setEditable(true);
            categfxid.setEditable(true);
            datefxid.setEditable(true);
            lieufxid.setEditable(true);
            nbrfxid.setEditable(true);
            descriptionfxid.setEditable(true);

        } else {
            btnEditMode.setStyle("");
            nomfxid.setEditable(false);
            categfxid.setEditable(false);
            datefxid.setEditable(false);
            lieufxid.setEditable(false);
            nbrfxid.setEditable(false);
            descriptionfxid.setEditable(false);
        }
    }

    @FXML
    private void add(ActionEvent event) {
        StringBuilder errors = new StringBuilder();

        if (nomfxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
        if (nbrfxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a name\n");
        }
        if (descriptionfxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a description\n");
        }
        if (lieufxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a addresse\n");
        }
        if (categfxid.getText().trim().isEmpty()) {
            errors.append("- Please enter a categorie\n");
        }

        /*   if (imageevent.getImage() == null) {
            errors.append("- Please enter a imageevent \n");
        }*/
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = nomfxid.getText();
            String nbr = nbrfxid.getText();
            String desc = descriptionfxid.getText();
            String addr = lieufxid.getText();
            String categ = categfxid.getText();
            LocalDate datee = datefxid.getValue();
            String imagee = image.getText();

            getQuery(nom, nbr, desc, addr, categ, datee, imagee);
            nomfxid.setText("");
            nbrfxid.setText("");
            descriptionfxid.setText("");
            lieufxid.setText("");
            categfxid.setText("");
            image.setText("");
            datefxid.setValue(null);
            dateCheckmark.setVisible(false);
            labeldate.setText("");
            nomCheckmark.setVisible(false);
            labelnom.setText("");
            nombreCheckmark.setVisible(false);
            labelnbr.setText("");
            DescriptionCheckmark.setVisible(false);
            labeldescription.setText("");
            adressecheck.setVisible(false);
            labeladdresse.setText("");
            categcheck.setVisible(false);
            userimage.setImage(new Image("file:C:\\Users\\user\\Desktop\\Git slim\\ENERGYM-DESKTOP\\src\\Resources\\Icons\\icons8-nom-100.png"));

            labelcateg.setText("");
            btnEditMode.setStyle("");

        }
    }

    @FXML
    private void uploadimage(MouseEvent event) throws MalformedURLException {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Desktop"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//                path = selectedFile.toURI().toURL().toExternalForm();
            userimage.setImage(new Image(selectedFile.toURI().toURL().toString()));
            userimage.setFitHeight(150);
            userimage.setFitWidth(250);
            image.setText(path);

        }
        if (selectedFile != null) {
            try {
                File source = new File(selectedFile.toString());
                File dest = new File("C:\\xampp\\htdocs\\img");
                FileUtils.copyFileToDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getQuery(String nom, String nombre, String desc, String addr, String categ, LocalDate datee, String image) {

        if (update == false) {
            CategoriesEventService ces = new CategoriesEventService();
            EventService es = new EventService();
            int c = ces.getbyid(categ);
            java.util.Date date = java.sql.Date.valueOf(datee);

            Event e = new Event(nom, c, date, desc, addr, nombre, image);
            es.ajouterEvent(e);
            loadDate();
            clearr();
        } else {
            EventService es = new EventService();
            CategoriesEventService ces = new CategoriesEventService();
            int c = ces.getbyid(categ);
            java.util.Date date = java.sql.Date.valueOf(datee);
            Event e = new Event(eventid, c, date, nom, desc, addr, nombre, image);
            es.modifier(eventid, e);
            loadDate();
            clearr();
        }

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

    @FXML
    private void clear(ActionEvent event) {
        nomfxid.setText("");
        nbrfxid.setText("");
        descriptionfxid.setText("");
        lieufxid.setText("");
        categfxid.setText("");
        image.setText("");
        datefxid.setValue(null);
        dateCheckmark.setVisible(false);
        labeldate.setText("");
        nomCheckmark.setVisible(false);
        labelnom.setText("");
        nombreCheckmark.setVisible(false);
        labelnbr.setText("");
        DescriptionCheckmark.setVisible(false);
        labeldescription.setText("");
        adressecheck.setVisible(false);
        labeladdresse.setText("");
        categcheck.setVisible(false);
        userimage.setImage(new Image("file:C:\\Users\\user\\Desktop\\Git slim\\ENERGYM-DESKTOP\\src\\Resources\\Icons\\icons8-nom-100.png"));

        labelcateg.setText("");
        btnEditMode.setStyle("");

        this.update = false;

    }

    private void clearr() {
        nomfxid.setText("");
        nbrfxid.setText("");
        descriptionfxid.setText("");
        lieufxid.setText("");
        categfxid.setText("");
        image.setText("");
        datefxid.setValue(null);
        //   dateCheckmark.setImage(new Image(""));
        labeldate.setText("");
        //   nomCheckmark.setImage(new Image(""));
        labelnom.setText("");
        //    nombreCheckmark.setImage(new Image(""));
        labelnbr.setText("");
//            DescriptionCheckmark.setImage(new Image(""));
        labeldescription.setText("");
        //     adressecheck.setImage(new Image(""));
        labeladdresse.setText("");
        //     categcheck.setImage(new Image(""));
        labelcateg.setText("");
        btnEditMode.setStyle("");
        ;
        this.update = false;

    }

    @FXML
    private void Categorie(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/CategoriesEventBack.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
        //stage.close();  
        CategoriesEventBack ct = new CategoriesEventBack();
        //System.out.println(ct.getCat());
        categfxid.setText(ct.getCat());

    }

}
