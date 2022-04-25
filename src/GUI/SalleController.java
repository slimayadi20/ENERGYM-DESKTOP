/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reclamation;
import Entities.Salle;
import Services.SalleService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
    private AnchorPane DashboardUtilis;
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnCustomers;
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
    private Label repondufid;
    private Label encoursfid;
 
    @FXML
    private Label currentTimeTF1;
    ObservableList<Salle> data = FXCollections.observableArrayList();
    SalleService ss = new SalleService() ; 
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
    private ImageView add;
    @FXML
    private ImageView refresh;

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
        recherche_avance();
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
                            loader.setLocation(getClass().getResource("/GUI/addSalle.fxml"));
                            try {
                                loader.load();
                            } catch (Exception ex) {
                                ex.getMessage();
                            }

                            AddSalleController addSalleController = loader.getController();
                               

                         addSalleController.setTextField(salle.getId(), salle.getNom(),salle.getAdresse(),salle.getTel(),salle.getMail(),salle.getDescription(),salle.getImage(),salle.getPrix1(),salle.getPrix2(),salle.getPrix3(),salle.getHeureo(),salle.getHeuref());
                            System.out.println(salle.getPrix2());
                            System.out.println(salle.getPrix3());
                          addSalleController.setUpdate(true) ; 
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

    @FXML
    private void add(MouseEvent event) {
          salle = tableviewsalle.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/GUI/addSalle.fxml"));
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
    private void refreshfct(MouseEvent event) {
        loadDate();

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

    @FXML
    private void PDFSalle(MouseEvent event) throws FileNotFoundException, DocumentException {
           SalleService ec = new SalleService();
        String message = "Voici la liste des salles \n\n";

        String file_name = "src/PDF/liste_Salle.pdf";
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
        PdfPCell cl4 = new PdfPCell(new Phrase("photo"));
        table.addCell(cl4);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Salle.size(); i++) {
            
            table.addCell("" + Salle.get(i).getNom());
            table.addCell("" + Salle.get(i).getDescription());
            table.addCell("" + Salle.get(i).getAdresse());
            table.addCell("" + Salle.get(i).getMail());
            table.addCell("" + Salle.get(i).getImage());

        }
        document.add(table);

        document.close();

    }
    }

