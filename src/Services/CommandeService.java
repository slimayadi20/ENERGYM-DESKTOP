/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandeService {

    Connection cnx;

    public CommandeService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouterCommande(Commande t,int userid) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `commande`(`user_id`,`prenom`, `nom`, `email`) "
                    + "VALUES ('" + userid + "','" + t.getPrenom()+ "','" + t.getNom()+ "','" + t.getEmail()+ "')";
            st.executeUpdate(query);
            System.out.println("Commande ajoutée avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from commande where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("evenement n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Commande> afficher() {
        List<Commande> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from commande";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Commande u = new Commande();

                u.setPrenom(rs.getString("prenom"));
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setEmail(rs.getString("email"));
                
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
 
    
}
