/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Services.PanierService;
import static Services.PanierService.instance;

/**
 *
 * @author karim
 */
public class Panier {

    int id;
    int userid;
    String produit;
    int quantite;
String image;
int prix;
 String userpanier;
int idproduit;




    public Panier(int id, int userid, String produit, int quantite) {
        this.id = id;
        this.userid = userid;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Panier(int userid, String produit, int quantite) {
        this.userid = userid;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Panier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

     // String q=userpanier.substring(11,12);
        


    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", user_id=" + userid +  ", produit=" +produit +", quantite=" +quantite +  ", image=" +image +", prix=" +prix + '}'+"\n";
    }

    public String getUserpanier() {
        return userpanier;
    }

    public void setUserpanier(String userpanier) {
        this.userpanier = userpanier;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }



}
