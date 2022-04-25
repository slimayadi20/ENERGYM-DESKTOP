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
public class Cours {
    int id;
    private String nom;
    private int salleassocie_id;
    private String nom_coach;
    private int nombre;
    private String description;
    private String image;
    private String heure_d;
    private String heure_f;
     private String jour;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getSalleassocie_id() {
        return salleassocie_id;
    }

    public String getNom_coach() {
        return nom_coach;
    }

    public int getNombre() {
        return nombre;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getHeure_d() {
        return heure_d;
    }

    public String getHeure_f() {
        return heure_f;
    }

    public String getJour() {
        return jour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSalleassocie_id(int salleassocie_id) {
        this.salleassocie_id = salleassocie_id;
    }

    public void setNom_coach(String nom_coach) {
        this.nom_coach = nom_coach;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setHeure_d(String heure_d) {
        this.heure_d = heure_d;
    }

    public void setHeure_f(String heure_f) {
        this.heure_f = heure_f;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Cours() {
    }

    public Cours(int id, String nom, int salleassocie_id, String nom_coach, int nombre, String description, String image, String jour, String heure_d, String heure_f) {
        this.id = id;
        this.nom = nom;
        this.salleassocie_id = salleassocie_id;
        this.nom_coach = nom_coach;
        this.nombre = nombre;
        this.description = description;
        this.image = image;
        this.jour = jour;
        this.heure_d = heure_d;
        this.heure_f = heure_f;
    }

    public Cours(String nom, int salleassocie_id, String nom_coach, int nombre, String description, String image, String jour, String heure_d, String heure_f) {
        this.nom = nom;
        this.salleassocie_id = salleassocie_id;
        this.nom_coach = nom_coach;
        this.nombre = nombre;
        this.description = description;
        this.image = image;
        this.jour = jour;
         this.heure_d = heure_d;
        this.heure_f = heure_f;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", nom=" + nom + ", salleassocie_id=" + salleassocie_id + ", nom_coach=" + nom_coach + ", nombre=" + nombre + ", description=" + description + ", image=" + image + ", heure_d=" + heure_d + ", heure_f=" + heure_f + ", jour=" + jour + '}'+"\n";
    }
     
}
