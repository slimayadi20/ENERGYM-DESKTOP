/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package energym.desktop;

import Entities.User;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.stage.Stage;

/**
 *
 * @author MSI
 */
public class MainClass extends Application {

    public static User UserconnectedC = new User();

    @Override
    public void start(Stage primaryStage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/GUI/Front.fxml"));
            //      Parent root = FXMLLoader.load(getClass().getResource("/GUI/UpdatePassword.fxml"));
            //   Parent root = FXMLLoader.load(getClass().getResource("/GUI/User.fxml"));

            Scene scene = new Scene(root);
            /*  String musicFile = "C:/Users/MSI/Downloads/Starsailor.mp3";     // For example

Media sound = new Media(new File(musicFile).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(sound);
mediaPlayer.play();*/

            primaryStage.setTitle("ENERGYM app!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
