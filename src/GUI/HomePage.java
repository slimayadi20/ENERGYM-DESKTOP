/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author nouri
 */
public class HomePage extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
  try{
      
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Salle.fxml"));
            //      Parent root = FXMLLoader.load(getClass().getResource("/GUI/UpdatePassword.fxml"));
            //    Parent root = FXMLLoader.load(getClass().getResource("/GUI/User.fxml"));

            Scene scene = new Scene(root);
            /*  String musicFile = "C:/Users/MSI/Downloads/Starsailor.mp3";     // For example

Media sound = new Media(new File(musicFile).toURI().toString());
MediaPlayer mediaPlayer = new MediaPlayer(sound);
mediaPlayer.play();*/

            primaryStage.setTitle("ENERGYM app!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

  
    

