/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Time;

/**
 *
 * @author nouri
 */
public class Salle {

    int id;
    private String nom;
    private String adresse;
    private String tel;
    private String mail;
    private String description;
    private String image;
    private String prix1;
    private String prix2;
    private String prix3;
    private String heureo;
    private String heuref;
    private String url;
    private int nblike;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getPrix1() {
        return prix1;
    }

    public String getPrix2() {
        return prix2;
    }

    public String getPrix3() {
        return prix3;
    }

    public String getHeureo() {
        return heureo;
    }

    public String getHeuref() {
        return heuref;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix1(String prix1) {
        this.prix1 = prix1;
    }

    public void setPrix2(String prix2) {
        this.prix2 = prix2;
    }

    public void setPrix3(String prix3) {
        this.prix3 = prix3;
    }

    public void setHeureo(String heureo) {
        this.heureo = heureo;
    }

    public void setHeuref(String heuref) {
        this.heuref = heuref;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNblike() {
        return nblike;
    }

    public void setNblike(int nblike) {
        this.nblike = nblike;
    }

    public Salle() {
    }

    public Salle(int id, String nom, String adresse, String tel, String mail, String description, String image, String prix1, String prix2, String prix3, String heureo, String heuref, String url) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.description = description;
        this.image = image;
        this.prix1 = prix1;
        this.prix2 = prix2;
        this.prix3 = prix3;
        this.heureo = heureo;
        this.heuref = heuref;
        this.heuref = heuref;
        this.url = url;
    }

    public Salle(String nom, String adresse, String tel, String mail, String description, String image, String prix1, String prix2, String prix3, String heureo, String heuref, String url) {
        this.nom = nom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.description = description;
        this.image = image;
        this.prix1 = prix1;
        this.prix2 = prix2;
        this.prix3 = prix3;
        this.heureo = heureo;
        this.heuref = heuref;
        this.url = url;

    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", tel=" + tel + ", mail=" + mail + ", description=" + description + ", image=" + image + ", prix1=" + prix1 + ", prix2=" + prix2 + ", prix3=" + prix3 + ", heureo=" + heureo + ", heuref=" + heuref + '}';
    }

}
