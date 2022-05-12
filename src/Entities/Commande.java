/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author karim
 */
public class Commande {
 int id;
 int userid;
 String prenom;
 String nom;
 String email;

    public Commande(int id, int userid, String prenom, String nom, String email) {
        this.id = id;
        this.userid = userid;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public Commande(int userid, String prenom, String nom, String email) {
        this.userid = userid;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public Commande() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", userid=" + userid + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + '}';
    }
 
}
