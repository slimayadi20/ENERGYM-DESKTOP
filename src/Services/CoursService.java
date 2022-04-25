/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Cours;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CoursService  {

    Connection cnx;

    public CoursService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(Cours t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `Cours`( `nom`, `salleassocie_id`, `nom_coach`, `nombre`, `description`, `image`, `jour`, `heure_d`, `heure_f`) "
                    
                    + "VALUES ('" + t.getNom()+ "','" + t.getSalleassocie_id()+ "','" + t.getNom_coach() + "','" + t.getNombre()+ "','" + t.getDescription()+ "','"  + t.getImage() +"','" + t.getJour()+ "','"+t.getHeure_d() +"','" + t.getHeure_f()+"')";
            st.executeUpdate(query);
            System.out.println("Cours ajoutée avec succes");
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(long id_amodifier, Cours t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `Cours` SET `nom`=?,`salleassocie_id`=?, `nom_coach`=?,`nombre`=?,`description`=?,`image`=?,`jour`=?,`heure_d`=?,`heure_f`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setLong(2, t.getSalleassocie_id());
            st.setString(3, t.getNom_coach());
            st.setLong(4, t.getNombre());
            st.setString(5, t.getDescription());
            st.setString(6, t.getImage());
            st.setString(7, t.getJour());
            st.setString(8, t.getHeure_d());
            st.setString(9, t.getHeure_f());
            
            st.setLong(10, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Cours modifié avec success");
            } else {
                System.out.println("Cours n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from Cours where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Cours n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    

 
   public List<Cours> afficher() {
        List<Cours> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Cours";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Cours u = new Cours();
                  u.setId(rs.getInt("id"));
                u.setNom(rs.getString("nom"));
                u.setSalleassocie_id(rs.getInt("salleassocie_id"));
                u.setNom_coach(rs.getString("nom_coach"));
                u.setNombre(rs.getInt("nombre"));
                u.setDescription(rs.getString("description"));
                u.setImage(rs.getString("image"));
                u.setJour(rs.getString("jour"));
                u.setHeure_d(rs.getString("heure_d"));
                u.setHeure_f(rs.getString("heure_f"));
               
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CoursService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
   

}
