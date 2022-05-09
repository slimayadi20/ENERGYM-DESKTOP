/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author MSI
 */
public class Produit {

    int id;
    String nom;
    String description;
    String image;
    int prix;
    int quantité;
int rating ; 
    int NomCategories;

    public Produit(String nom, String description, String image, int prix, int quantité, int NomCategories) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.quantité = quantité;
        this.NomCategories = NomCategories;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getPrix() {
        return prix;
    }

    public int getQuantité() {
        return quantité;
    }

    public int getNomCategories() {
        return NomCategories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public void setNomCategories(int NomCategories) {
        this.NomCategories = NomCategories;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    

    public Produit(int id, String nom, String description, String image, int prix, int quantité, int NomCategories) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.quantité = quantité;
        this.NomCategories = NomCategories;
    }

    public Produit() {
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", prix=" + prix + ", quantit\u00e9=" + quantité + ", NomCategories=" + NomCategories + '}';
    }

}
