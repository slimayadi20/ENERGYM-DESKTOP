/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.LoginAttempt;
import Entities.User;
import static Services.CryptWithMD5.cryptWithMD5;
import Tools.MyConnexion;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.http.client.utils.DateUtils;

public class UserService {

    Connection cnx;

    public UserService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

    public void ajouter(User t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `user`( `nom`, `prenom`, `email`, `roles`, `phone`, `password`, `created_at`, `status`, `image_file` ,`activation_token`) "
                    + "VALUES ('" + t.getNom() + "','" + t.getPrenom() + "','" + t.getEmail() + "','" + t.getRoles() + "','" + t.getPhone() + "','" + cryptWithMD5(t.getPassword()) + "','" + t.getCreated_at() + "','" + t.getStatus() + "','" + t.getImageFile() + "','" + t.getActivation_token()+ "')";
            st.executeUpdate(query);
            System.out.println("user ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(long id_amodifier, User t) {
        try {
            System.out.println("1");

            PreparedStatement st;
            st = cnx.prepareStatement("UPDATE `user` SET `nom`=?,`prenom`=?, `email`=?,`phone`=?,"
                    + "`image_file`=? ,`birthday`=? WHERE id=?");
            System.out.println("2");

            st.setString(1, t.getNom());
            st.setString(2, t.getPrenom());
            st.setString(3, t.getEmail());
            st.setString(4, t.getPhone());
            st.setString(5, t.getImageFile());
            st.setString(6, t.getBirthday());
            st.setLong(7, id_amodifier);
            if (st.executeUpdate() == 1) {
                System.out.println("user modifier avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimer(long id) {
        try {
            Statement st = cnx.createStatement();
            String query = "delete from user where id=" + id;
            if (st.executeUpdate(query) == 1) {
                System.out.println("suppression avec success");
            } else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkloginbyAttempts(String email, String password) throws ParseException, SQLException {

        Statement st = cnx.createStatement();
        String query = "SELECT * FROM `user` WHERE `email`='" + email + "' AND `password`='" + password + "'";
        ResultSet rs = st.executeQuery(query);
        if (countRecentLoginAttempts(email) < 3) {
            System.out.println("ooo");
            return rs.next();
        } else {
            // neksa date time immutable wel condition besh mayodkholch 
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            LoginAttempt t = new LoginAttempt("127.0.0.1", date, email);
            ajouterLoginAttempt(t);
            return false;
        }

    }

    public void ajouterLoginAttempt(LoginAttempt t) {
        try {
            Statement st;
            st = cnx.createStatement();
            String query = "INSERT INTO `login_attempt`( `ip_address`, `date`, `username`) "
                    + "VALUES ('" + t.getIpaddress() + "','" + t.getDate() + "','" + t.getEmail() + "')";
            st.executeUpdate(query);
            System.out.println("login attempt ajouter avec success");
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checklogin(String username, String password) {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + username + "' AND `password`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean checklogina(String email, String password) throws ParseException {
        try {
            Statement st = cnx.createStatement();
            String query = "SELECT * FROM `user` WHERE `email`='" + email + "' AND `password`='" + password + "'";
            ResultSet rs = st.executeQuery(query);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            LoginAttempt t = new LoginAttempt("127.0.0.1", date, email);
            ajouterLoginAttempt(t);
            if (countRecentLoginAttempts(email) < 3) {
                System.out.println("ooo");
                return rs.next();
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int countRecentLoginAttempts(String email) throws ParseException {
        Date date = new Date(System.currentTimeMillis());
        final String current = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = df.parse(current);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, 3);
        String after = df.format(cal.getTime());
        //    System.out.println("Current time now : " + current);
        //  System.out.println("After adding 3 mins : " + after);

        try {
            Statement st = cnx.createStatement();
            String query = "SELECT count(*) FROM `login_attempt` WHERE `date` BETWEEN'" + "2022-04-11 00:00:00" + "' AND'" + after + "' AND `username`='" + email + "'";
            //  System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("number of attempts : ");
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return 0;
    }

    public List<User> afficher() {
        List<User> lu = new ArrayList<>();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                User u = new User();
                u.setPhone(rs.getString("phone"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setStatus(rs.getInt("status"));
                u.setNom(rs.getString("nom"));
                u.setImageFile(rs.getString("image_file"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setRoles(rs.getString("roles"));
                u.setBirthday(rs.getString("birthday"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }

    public List<User> sortByDate() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getCreated_at)).collect(Collectors.toList());
        return resultat;

    }

    public List<User> sortByStatus() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getStatus)).collect(Collectors.toList());
        return resultat;
    }

    public List<User> sortByRoles() {
        List<User> users = afficher();
        List<User> resultat = users.stream().sorted(Comparator.comparing(User::getRoles)).collect(Collectors.toList());
        return resultat;
    }

    public User findByUsername(String username) {
        User u = new User();
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where email='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                u.setPhone(rs.getString("phone"));
                u.setCreated_at(rs.getDate("created_at"));
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setStatus(rs.getInt("status"));
                u.setNom(rs.getString("nom"));
                u.setImageFile(rs.getString("image_file"));
                u.setPassword(cryptWithMD5(rs.getString("password")));
                u.setPrenom(rs.getString("prenom"));
                u.setRoles(rs.getString("roles"));
                u.setBirthday(rs.getString("birthday"));
                u.setActivation_token(rs.getString("activation_token"));
                System.out.println("found");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }
    public String findById(int username) {
        User u = new User();
        String email = " " ;
        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where id='" + username + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
        
              email = rs.getString("email");
             
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return email;
    }

    public boolean emailExist(String email) {

        try {
            Statement st = cnx.createStatement();
            String query = "select * from user where email='" + email + "'";
            ResultSet rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public void updatemdp(String email, String mdp) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "UPDATE user SET password= '" + cryptWithMD5(mdp) + "' WHERE email='" + email + "'";
        stm.executeUpdate(query);

    }

    public int affichernumber(String role) {
        if (role.equals("ROLE_GERANT")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from user where roles='ROLE_GERANT'";
                ResultSet rs = st.executeQuery(query);
                if (rs.next()) {
                    int theCount = rs.getInt(1);
                    System.out.println(theCount);
                    return theCount;

                }
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (role.equals("ROLE_CLIENT")) {
            try {
                Statement st = cnx.createStatement();
                String query = "select count(*) from user where roles='ROLE_CLIENT'";
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
