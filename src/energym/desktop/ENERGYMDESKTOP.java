/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package energym.desktop;

import Entities.User;
import Entities.Salle;
import Entities.Cours;
import Services.UserService;
import Services.SalleService;
import Services.CoursService;
import Services.PDFSalle;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author MSI
 */
public class ENERGYMDESKTOP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        //TEST SALLE
        
      // SalleService us = new SalleService();
       
     //Salle u = new Salle("slim", "sfax", "97431155", "omar@esprit.tn", "test test test", "admiinnslm1","40","50","60","dd","dd");
     //us.ajouter(u);
     //Salle um = new Salle(1, "giga gym", "sfax", "6452187", "omargg@esprit.tn", "testetste","11","20","20","ee","dd","ee");
     // us.modifier(1, um);
    // us.supprimer(2);
    //System.out.println(us.afficher()); 
    //***************************************************
   //System.out.println(us.findByNom("teseet"));
      // PDFSalle a=new PDFSalle() ;
       // a.liste_SallePDF();
    // TEST COURS
   CoursService us = new CoursService();
     Cours u = new Cours("test", 13, "hamdi", 50, "tes tes tes", "image","lundi","8","30");
    
    us.ajouter(u);
     //Cours um = new Cours(12,"osssimato", 12, "hamdi", 50, "tes tes tes", "aaaa","bbb","dd","dd");
     // us.modifier(12, um);
       //us.supprimer(13);
    //System.out.println(us.afficher()); 
    }

}
