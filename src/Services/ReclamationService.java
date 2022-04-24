/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reclamation;
import Entities.Reply;
import static Services.CryptWithMD5.cryptWithMD5;
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

public class ReclamationService {

    Connection cnx;

    public ReclamationService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(Reclamation t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `reclamation`( `titre`, `date_creation`, `contenu`, `statut`,`nom_user_id`) "
                    + "VALUES ('" + t.getTitre() + "','" + t.getDate() + "','" + t.getContenu() + "','" + "encours" + "','" + t.getNomUser() + "')";
            st.executeUpdate(query);
            System.out.println("Reclamation ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterReply(Reply t,int id) {
        try {
            Statement st;
            st = cnx.createStatement();
                        PreparedStatement pt;

            String query = "INSERT INTO `reply`( `contenu`, `email_receiver`, `email_sender`, `reclamation_id`) "
                    + "VALUES ('" + t.getContenu() + "','" + t.getEmail_receiver() + "','" + t.getEmail_sender() + "','" + t.getReclamation() + "')";
            pt = cnx.prepareStatement("UPDATE `reclamation` SET `statut`=? WHERE id=?");
            String etat= "traite" ;
            pt.setString(1, etat);
            pt.setInt(2,id);
            if (pt.executeUpdate() == 1) {
                System.out.println("Reclamation modifier avec success");
            } else {
                System.out.println("Reclamation n'existe pas");
            }
            st.executeUpdate(query);
            System.out.println("reponse ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reply> afficherReply() {
        List<Reply> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from reply";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                Reply u = new Reply();
                u.setEmail_receiver(rs.getString("email_receiver"));
                u.setEmail_sender(rs.getString("email_sender"));
                u.setContenu(rs.getString("contenu"));
                u.setReclamation(rs.getInt("reclamation_id"));
                u.setId(rs.getInt("id"));

                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public Reclamation afficherbyid(int id) {
        Reclamation u = new Reclamation();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from reclamation where id='" + id + "'";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                u.setTitre(rs.getString("titre"));
                u.setDate(rs.getDate("date_creation"));
                u.setContenu(rs.getString("contenu"));
                u.setStatut(rs.getString("statut"));
                u.setId(rs.getInt("id"));
                u.setNomUser(rs.getInt("nom_user_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public void modifier(long id_amodifier, Reclamation t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `reclamation` SET `titre`=?,`contenu`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getTitre());
            st.setString(2, t.getContenu());

            st.setLong(3, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("Reclamation modifier avec success");
            } else {
                System.out.println("Reclamation n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(int id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from reclamation where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Reclamation n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerReply(int id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from reply where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("Reclamation n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Reclamation> afficher() {
        List<Reclamation> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from Reclamation";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                //  System.out.println(rs);
                Reclamation u = new Reclamation();
                u.setTitre(rs.getString("titre"));
                u.setDate(rs.getDate("date_creation"));
                u.setContenu(rs.getString("contenu"));
                u.setStatut(rs.getString("statut"));
                u.setId(rs.getInt("id"));
                u.setNomUser(rs.getInt("nom_user_id"));

                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public int affichernumber(String role) {
        if (role.equals("encours")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from reclamation where statut='encours'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (role.equals("repondu")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from reclamation where statut='repondu'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

}
