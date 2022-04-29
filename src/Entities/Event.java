/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class Event {

    int id;
    int categories;
     String nomEvent;
     Date dateEvent;
     String descriptionEvent;
     String lieuEvent;
     String nbrPlacesEvent;
     String etatEvent;
     String imageFile;

    public Event() {
    }

    public String getnomEvent() {
        return nomEvent;
    }

    public void setnomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

 

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public String getNbrPlacesEvent() {
        return nbrPlacesEvent;
    }

    public void setNbrPlacesEvent(String nbrPlacesEvent) {
        this.nbrPlacesEvent = nbrPlacesEvent;
    }

    public String getEtatEvent() {
        return etatEvent;
    }

    public void setEtatEvent(String etatEvent) {
        this.etatEvent = etatEvent;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }


    public Event(int id, int categories, Date dateEvent, String nomEvent, String descriptionEvent, String lieuEvent, String nbrPlacesEvent, String imageFile) {
        this.id = id;
        this.categories = categories;
        this.nomEvent = nomEvent;
        this.dateEvent = dateEvent;
        this.descriptionEvent = descriptionEvent;
        this.lieuEvent = lieuEvent;
        this.nbrPlacesEvent = nbrPlacesEvent;
        this.imageFile = imageFile;
    }


    public Event(String nomEvent, int categories, Date dateEvent, String descriptionEvent, String lieuEvent, String nbrPlacesEvent, String imageFile) {
        this.nomEvent = nomEvent;
        this.categories = categories;
        this.dateEvent = dateEvent;
        this.descriptionEvent = descriptionEvent;
        this.lieuEvent = lieuEvent;
        this.nbrPlacesEvent = nbrPlacesEvent;
        this.imageFile = imageFile;
    }
    

    public Event(String nomEvent, int categories, Date dateEvent, String descriptionEvent, String lieuEvent, String nbrPlacesEvent, String etatEvent, String imageFile) {
        this.nomEvent = nomEvent;
        this.categories = categories;
        this.dateEvent = dateEvent;
        this.descriptionEvent = descriptionEvent;
        this.lieuEvent = lieuEvent;
        this.nbrPlacesEvent = nbrPlacesEvent;
        this.etatEvent = etatEvent;
        this.imageFile = imageFile;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", categories=" + categories + ", nomEvent=" + nomEvent + ", dateEvent=" + dateEvent + ", descriptionEvent=" + descriptionEvent + ", lieuEvent=" + lieuEvent + ", nbrPlacesEvent=" + nbrPlacesEvent + ", etatEvent=" + etatEvent + ", imageFile=" + imageFile + '}';
    }

 
}
