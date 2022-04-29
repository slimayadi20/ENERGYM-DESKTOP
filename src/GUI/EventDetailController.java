/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Event;
import Entities.User;
import Services.EventService;
import Tools.JavaMailUtilUser;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import javafx.event.ActionEvent;
import javax.swing.JFileChooser;
    
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javassist.NotFoundException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class EventDetailController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int Eventid;
    @FXML
    private Label namefxid;
    @FXML
    private ImageView imagefxid;
    @FXML
    private Label nbrfxid;
    @FXML
    private Label lieufxid;
    @FXML
    private Label descriptionfxid;
    @FXML
    private Label datefxid;
    @FXML
    private JFXButton participerfxid;
    @FXML
    private Label errorfxid;

    Random rand = new Random();
    int randomcode = rand.nextInt(9999);
    String code = String.valueOf(randomcode);
    @FXML
    private ImageView qrView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        // TODO
    }

    public void setEventId(int id) {
        this.Eventid = id;
    }

    public void loadData() {
        //System.out.println("initData"+thematique_id);
        EventService ths = new EventService();
        Event e = ths.EventDetailFront(Eventid);
        namefxid.setText(e.getNomEvent());
        nbrfxid.setText(e.getNbrPlacesEvent());
        lieufxid.setText(e.getLieuEvent());
        descriptionfxid.setText(e.getDescriptionEvent());
        /*   String pattern = "yyyy-mm-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String todayAsString = df.format(e.getDateEvent());
        datefxid.setText(todayAsString);
        System.out.println(e.getDateEvent());*/
        imagefxid.setImage(new Image("file:C:\\xampp\\htdocs\\img\\" + e.getImageFile()));

    }

    @FXML
    private void participer(MouseEvent event) throws Exception {
        EventService ths = new EventService();
        User u = new User(37, "slim", "ayadi", "azertyuiop", "95590010", "slim.ayadi@esprit.tn", "admiinnslm1");
        Event e = ths.EventDetailFront(Eventid);
        JavaMailUtilUser mail = new JavaMailUtilUser();

        if (ths.check(Eventid, u.getId()) == true) {
            errorfxid.setText("vous avez deja participé");
        } else {
            ths.Participer(e, u, code);
            String content = "Bonjour mr/mme " + u.getNom() + "\n"
                    + "Merci pour votre participation et voici votre pass pour l'evenement\n"
                    + "on vous attend chaleurheusement ! ";
            mail.sendMail("Confirmation de participation!", content, u.getEmail());
qr();
        }
    }
public void qr(){
      
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "http://java-buddy.blogspot.com/";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            ByteMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                        graphics.fillRect(i, j, 1, 1);
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
        }
        
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
      
}
}
