/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Livraison;
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

public class LivraisonService {

    Connection cnx;

    public LivraisonService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouterLivraison(Livraison t) {
        try {
            Statement st;
            st = cnx.createStatement();
            System.out.println("id commande = "+t.getCommandeid());
            String query = "INSERT INTO `livraison`(`id_commande_id`,`nom_livreur`, `date_livraison`, `etat`) "
                    + "VALUES ('" + t.getCommandeid()+ "','" + t.getNom()+ "','" + t.getDate()+ "','" + t.getEtat()+ "')";
            st.executeUpdate(query);
            System.out.println("Livraison ajoutée avec success");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(long id_amodifier, Livraison t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `livraison` SET `id_commande_id`=?,`nom_livreur`=?, `date_livraison`=?,`etat`=? WHERE id=?");
            System.out.println("2");

            st.setInt(1, t.getCommandeid());
            st.setString(2, t.getNom());
            st.setDate(3, (Date) t.getDate());
            st.setString(4, t.getEtat());
            
            st.setLong(5, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Livraison modifiée avec success");
            } else {
                System.out.println("Livraison n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from livraison where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("evenement n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Livraison> afficher() {
        List<Livraison> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from livraison";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Livraison u = new Livraison();

                u.setDate(rs.getDate("date_livraison"));
                u.setCommandeid(rs.getInt("id_commande_id"));
                u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom_livreur"));
                u.setEtat(rs.getString("etat"));
              
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

}
