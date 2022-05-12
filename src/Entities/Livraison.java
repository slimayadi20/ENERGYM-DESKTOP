/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author karim
 */
public class Livraison {
    int id;
int commandeid;
String nom;
Date date;
String etat;

    public Livraison(int id, int commandeid, String nom, Date date, String etat) {
        this.id = id;
        this.commandeid = commandeid;
        this.nom = nom;
        this.date = date;
        this.etat = etat;
    }

    public Livraison(int commandeid, String nom, Date date, String etat) {
        this.commandeid = commandeid;
        this.nom = nom;
        this.date = date;
        this.etat = etat;
    }

    public Livraison() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(int commandeid) {
        this.commandeid = commandeid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

@Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", commandeid=" + commandeid + ", nom=" + nom +", date=" + date +", etat=" + etat  +'}';
    }
    
}
