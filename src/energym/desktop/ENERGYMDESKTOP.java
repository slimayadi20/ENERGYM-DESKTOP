/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package energym.desktop;

import Services.PanierService;
import Services.ReclamationService;
import Services.UserService;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author MSI
 */
public class ENERGYMDESKTOP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException, LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException {
      PanierService ps = new PanierService();
        ps.ajouterPanier(8,32);
        ps.ajouterPanier(9,32);
        ps.panier( 32);
        
        
        UserService us = new UserService();
        ReclamationService rs = new ReclamationService();
        // Date date = new Date(System.currentTimeMillis());
        //LocalDate d = LocalDate.(date); 
        // ************************************ age calculator *************************************
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String firstdate = "02/05/2000";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String todaysdate = dateFormat.format(date);
        String seconddate = todaysdate;
        System.out.println(todaysdate);
        LocalDate date1 = LocalDate.parse(firstdate, formatter);
        LocalDate date2 = LocalDate.parse(todaysdate, formatter);
        long yearsBetween = ChronoUnit.YEARS.between(date1, date2);
        System.out.println(yearsBetween);
        // ************************************ age calculator *************************************

        //Smsapi.sendSMS("hello");
        //    System.out.println(us.sortByDate());
        // System.out.println(us.checklogin("slim.ayadii@esprit.tn", "azertyuiop"));
        //  System.out.println(us.countRecentLoginAttempts("slim.ayadi@esprit.tn"));
        //   User u = new User("slim", "ayadi", "azertyuiop", "95590010", "slim.ayadii@esprit.tn", "admiinnslm1", "ROLE_ADMIN", 1, date);
        // user
        // us.ajouter(u);
        // User um = new User(11, "yasmine", "bettaieb", "hhh", "95590010", "slim.ayadii@esprit.tn", "ooo");
        //us.modifier(11, um);
        //us.supprimer(12);
        // System.out.println(us.sortByDate());
// reclamation 
        //   public Reclamation(String titre, String contenu, User NomUser, Date date) {
//Reclamation r= new Reclamation("mezz","med",11,date) ;
//rs.ajouter(r);
     //   System.out.println(rs.afficher());
        
    }

}
