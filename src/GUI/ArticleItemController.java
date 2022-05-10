/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Article;
import Entities.Salle;
import Services.SalleService;
import static energym.desktop.MainFX.UserconnectedC;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class ArticleItemController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private AnchorPane vboxfx;
    @FXML
    private Label salleid;
    @FXML
    private Label name;
    Article article;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Article t) {
        article = t;
        name.setText(t.getTitre());
        image.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + t.getImageFile()));

        vboxfx.setOnMouseClicked((MouseEvent event) -> {
//**************************

            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ArticleDetail.fxml"));
                root = fxmlLoader.load();
                System.out.println("Hello World! " + t);

                ArticleDetailController controller = fxmlLoader.getController();

                controller.setArticleId(t.getId());
                controller.loadData();
                Stage window = (Stage) salleid.getScene().getWindow();
                window.setScene(new Scene(root));

            } catch (IOException ex) {
                Logger.getLogger(SalleItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );

       
    }

}
