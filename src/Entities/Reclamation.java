/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class Reclamation {

    float id;
    String titre;
    String contenu;
    String statut;
    Date date;
    int NomUser;
    int produit;

    public Reclamation(String titre, String contenu, String statut, Date date, int NomUser, int produit) {
        this.titre = titre;
        this.contenu = contenu;
        this.statut = statut;
        this.date = date;
        this.NomUser = NomUser;
        this.produit = produit;
    }

    public Reclamation(String titre, String contenu, int NomUser, Date date) {
        this.titre = titre;
        this.contenu = contenu;
        this.NomUser = NomUser;
        this.date = date;
    }

    public int getNomUser() {
        return NomUser;
    }

    public void setNomUser(int NomUser) {
        this.NomUser = NomUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Reclamation(float id, String titre, String contenu, int nomUser) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.NomUser = nomUser;
    }

    public Reclamation(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

    public Reclamation() {
    }

    public float getId() {
        return id;
    }

    public void setId(float id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", statut=" + statut + ", date=" + date + ", NomUser=" + NomUser+ '}'+"\n";
    }
 

}
