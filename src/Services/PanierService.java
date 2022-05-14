/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Panier;
import Entities.User;
import Tools.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class PanierService {
public static PanierService instance=null;
    Connection cnx;
 public static Map<Integer, Integer> hashMap
            = new HashMap<Integer, Integer>();
    public PanierService() {
        cnx = MyConnexion.getInstance().getCnx();

    }

     public static PanierService getInstance() {
        if (instance == null) {
            instance = new PanierService();
        }
        return instance;
    }
    
        public void ajoutercode(String nom,int reduction) {
          try {
            Statement st;
            st = cnx.createStatement();
             String query = "INSERT INTO `promo`( `code`,`reduction`) "
                    + "VALUES ('" + nom+ "','"+reduction+"')";     
            st.executeUpdate(query);
            System.out.println("categories ajouté avec success");
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Panier> panier(int id) {
        List<Panier> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM panier where user_id= " + id;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

            while (res.next()) {
                String someString = res.getString(3);
                long count = someString.chars().filter(ch -> ch == ':').count() - 2;
                for (int i = 0; i < count / 2; i++) {

                    Panier p = new Panier();
                    p.setId(res.getInt(1));
                    p.setUserid(res.getInt(2));
                    
                    // p.setUserpanier(res.getString(3));
                    String qt = res.getString(3);

                    int a = 11 + 8 * i;
                    int b = 12 + 8 * i;
                    p.setQuantite(Integer.parseInt(qt.substring(a, b)));

                    String rq = "SELECT * FROM produit where id=" + Integer.parseInt(qt.substring(7 + 8 * i, 8 + 8 * i));
                    Statement ss = MyConnexion.getInstance().getCnx().createStatement();
                    ResultSet rr = ss.executeQuery(rq);

                    while (rr.next()) {
                        p.setIdproduit(rr.getInt(1));
                        p.setProduit(rr.getString(2));
                        p.setImage(rr.getString(6));
                        p.setPrix(rr.getInt(4));
                    }

                    myList.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void ajouterPanier(int idproduit, int iduser) {
        try {
            String pp = "";

            String requete = "SELECT * FROM panier where user_id= " + iduser;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

// if empty
            if (res.next() == false) {

//System.out.println("panier id "+res.getInt(1));
                st = cnx.createStatement();
                String query = "INSERT INTO `panier`( `id`, `user_id`, `user_panier`, `produits`) "
                        + "VALUES ('" + 1 + "','" + iduser + "','" + "a:1:{i:" + idproduit + ";i:1;}" + "','" + pp + "," + idproduit + "')";
                st.executeUpdate(query);
                hashMap.put(idproduit, idproduit);
                System.out.println("hashmap 2" + hashMap);
            } else {

//System.out.println("panier id "+res.getInt(1));
                String qt = res.getString(3);
                String panier = res.getString(3);
                int count = Integer.parseInt(panier.substring(2, 3));

                String p = panier.substring(4, panier.length());

                String rq = "SELECT * FROM produit where id=" + idproduit;
                Statement ss = MyConnexion.getInstance().getCnx().createStatement();
                ResultSet rr = ss.executeQuery(rq);

                String requete1 = "SELECT * FROM panier where user_id= " + iduser;
                Statement st1 = MyConnexion.getInstance().getCnx().createStatement();
                ResultSet res1 = st1.executeQuery(requete1);
                for (int i = 0; i < count; i++) {
                    int a = 11 + 8 * i;
                    int b = 12 + 8 * i;
                    String qte = qt.substring(a, b);

                }
                List<String> strings = new ArrayList<String>();
                String test = panier.substring(5, panier.length() - 1);
                int index = 0;
                while (index < test.length()) {
                    strings.add(test.substring(index, Math.min(index + 8, p.length())));
                    index += 8;
                }
                System.out.println("TEST : " + strings);
                if (strings.contains("i:2;i:1;")) {
                    System.out.println("salut " + strings.indexOf("i:2;i:1;"));
                }

// if panier exists without this product
                while (res1.next()) {
                    if (!res1.getString(4).contains(Integer.toString(idproduit))) {
                        pp = res1.getString(4);
//System.out.println("panier id "+res.getInt(1));

                        st = cnx.createStatement();
                        count++;
                        String query = "UPDATE panier SET user_panier= '" + "a:" + count + ":" + p.replace("}", "i:" + idproduit + ";i:1;}") + "' WHERE id='" + res.getInt(1) + "'";
                        st.executeUpdate(query);
                        String query1 = "UPDATE panier SET produits= '" + pp + "," + idproduit + "' WHERE id='" + res.getInt(1) + "'";
                        st.executeUpdate(query1);
                    } else {
                        System.out.println("aaaa");
// if panier exists with this product
                        List<String> MySortStrings = new ArrayList<String>();
                        for (int i = 0; i < strings.size(); i++) {
                            if (strings.get(i).startsWith("i:" + idproduit + ";")) {
                                MySortStrings.add(strings.get(i));
                            }
                        }
                        System.out.println("sort : " + MySortStrings);
                        int kt = Integer.parseInt(MySortStrings.get(0).substring(6, 7));
                        int nbr = strings.indexOf(MySortStrings.get(0));
                        kt++;
                        strings.set(nbr, "i:" + idproduit + ";i:" + kt + ";");

                        String chart = "{";
                        for (int i = 0; i < strings.size(); i++) {
                            chart += strings.get(i);
                        }
                        chart += "}";
                        System.out.println("chart : " + chart);
                        while (rr.next())
                        {
                        if (kt<=rr.getInt(5))
                        {
                        String query = "UPDATE panier SET user_panier= '" + "a:" + count + ":" + chart + "' WHERE id='" + res.getInt(1) + "'";
                        st.executeUpdate(query);
                        } }
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deletePanier(int idproduit, int iduser) {
        try {
            String pp = "";

            String requete = "SELECT * FROM panier where user_id= " + iduser;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(requete);

// if empty
            if (res.next() == false) {

                System.out.println("NO PANIER");
            } else {

//System.out.println("panier id "+res.getInt(1));
                String qt = res.getString(3);
                String panier = res.getString(3);
                int count = Integer.parseInt(panier.substring(2, 3));

                String p = panier.substring(4, panier.length());

                String rq = "SELECT * FROM produit where id=" + idproduit;
                Statement ss = MyConnexion.getInstance().getCnx().createStatement();
                ResultSet rr = ss.executeQuery(rq);
                String requete1 = "SELECT * FROM panier where user_id= " + iduser;
                Statement st1 = MyConnexion.getInstance().getCnx().createStatement();
                ResultSet res1 = st1.executeQuery(requete1);
                for (int i = 0; i < count; i++) {
                    int a = 11 + 8 * i;
                    int b = 12 + 8 * i;
                    String qte = qt.substring(a, b);

                }
                List<String> strings = new ArrayList<String>();
                String test = panier.substring(5, panier.length() - 1);
                int index = 0;
                while (index < test.length()) {
                    strings.add(test.substring(index, Math.min(index + 8, p.length())));
                    index += 8;
                }
                System.out.println("TEST : " + strings);

// if panier exists without this product
                while (res1.next()) {

                    pp = res1.getString(4);
//System.out.println("panier id "+res.getInt(1));

                    List<String> MySortStrings = new ArrayList<String>();
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).startsWith("i:" + idproduit + ";")) {
                            MySortStrings.add(strings.get(i));
                        }
                    }
                    System.out.println("sort : " + MySortStrings);
                    int kt = Integer.parseInt(MySortStrings.get(0).substring(6, 7));
                    int nbr = strings.indexOf(MySortStrings.get(0));

                    if (kt == 1) {
                        if (strings.size() == 1) {
                            String query = "delete from  panier  where id='" + res.getInt(1) + "'";
                            st.executeUpdate(query);
                        } else {
                            strings.remove(nbr);
                            String chart = "{";
                            for (int i = 0; i < strings.size(); i++) {
                                chart += strings.get(i);
                            }
                            chart += "}";
                            System.out.println("chart remove : " + chart);
                            st = cnx.createStatement();

                            count--;
                            String query = "UPDATE panier SET user_panier= '" + "a:" + count + ":" + chart + "' WHERE id='" + res.getInt(1) + "'";
                            st.executeUpdate(query);
                            String[] parts = res1.getString(4).split(",");
                            for (String a : parts) {
                                if (a == Integer.toString(idproduit)) {
                                    a = "";
                                }
                            }
                            StringBuilder builder = new StringBuilder();
                            for (int i = 0; i < parts.length - 1; i++) {
                                builder.append(parts[i]);
                            }
                            String joined = builder.toString();
                            String query1 = "UPDATE panier SET produits= '" + joined + "' WHERE id='" + res.getInt(1) + "'";
                            st.executeUpdate(query1);
                        }
                    } else {
                        kt--;
                        strings.set(nbr, "i:" + idproduit + ";i:" + kt + ";");
                        String chart = "{";
                        for (int i = 0; i < strings.size(); i++) {
                            chart += strings.get(i);
                        }
                        chart += "}";
                        System.out.println("chart decrement : " + chart);
                        st = cnx.createStatement();
                        String query = "UPDATE panier SET user_panier= '" + "a:" + count + ":" + chart + "' WHERE id='" + res.getInt(1) + "'";
                        st.executeUpdate(query);

                    }
                }

// if panier exists with this product
// a:2:{i:1;i:1;i:2;i:2;}
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getCount(int iduser) {
          int count = 0;
        String req = "SELECT * FROM panier where user_id= " + iduser;

        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                 String panier = res.getString(3);
                 count = Integer.parseInt(panier.substring(2, 3));
               
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
    public int getPromo(String code) {
          int reduction = 0;
         
        String req = "SELECT * FROM promo where code= " +"'"+code+"'";

        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                 reduction = res.getInt(3);
               
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reduction;
    }
    public int getStock(int idproduit) {
          int reduction = 0;
         
        String req = "SELECT * FROM produit where id= "+idproduit;

        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                 reduction = res.getInt(5);
               
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reduction;
    }
    public int getQT(int iduser,int idproduit) {
        String req = "SELECT * FROM panier where user_id= " + iduser;
int kt=0;
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                
                String panier = res.getString(3);
                 String p = panier.substring(4, panier.length());
                  List<String> strings = new ArrayList<String>();
                String test = panier.substring(5, panier.length() - 1);
                int index = 0;
                while (index < test.length()) {
                    strings.add(test.substring(index, Math.min(index + 8, p.length())));
                    index += 8; }
                     List<String> MySortStrings = new ArrayList<String>();
                    for (int i = 0; i < strings.size(); i++) {
                        if (strings.get(i).startsWith("i:" + idproduit + ";")) {
                            MySortStrings.add(strings.get(i));
                        }
                    }
                    System.out.println("sort : " + MySortStrings);
                     kt = Integer.parseInt(MySortStrings.get(0).substring(6, 7));
                }
        
            }
        
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return kt;
    }
    
     public void supprimerPanierbyuser(int  p) {
        try {
            String req = "DELETE FROM panier where user_id=" + p;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("panier supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
