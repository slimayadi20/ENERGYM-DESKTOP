/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jfxtras.scene.control.agenda.Agenda;

/**
 *
 * @author Khaled
 */
public class EvenementCalendrier implements Agenda.Appointment {
    Event evenement;
    Agenda.AppointmentGroupImpl group = new Agenda.AppointmentGroupImpl();
    
    
    EvenementCalendrier(){
        
    }
    EvenementCalendrier(Event e){
      this.evenement=e;
        
    }
    @Override
    public Boolean isWholeDay() {
        return false;
    }

    @Override
    public void setWholeDay(Boolean bln) {
        
    }

    @Override
    public String getSummary() {
        return this.evenement.getDescriptionEvent();
    }

    @Override
    public void setSummary(String string) {
       this.evenement.setDescriptionEvent(string);
    }

    @Override
    public String getDescription() {
        return this.evenement.getDescriptionEvent();
    }

    @Override
    public void setDescription(String string) {
        this.evenement.setDescriptionEvent(string);
       
    }

    @Override
    public String getLocation() {
        return evenement.getLieuEvent();
    }

    @Override
    public void setLocation(String string) {
       evenement.setLieuEvent(string);
    }

    @Override
    public Agenda.AppointmentGroup getAppointmentGroup() {
        
        group.setStyleClass("calendar");
        return group;
    }

    @Override
    public void setAppointmentGroup(Agenda.AppointmentGroup ag) {
        Agenda.AppointmentGroupImpl group2 = new Agenda.AppointmentGroupImpl();
        this.group=group2 ;
    }

    @Override
    public LocalDateTime getStartLocalDateTime() {
        return evenement.getTime().toLocalDateTime();
    }
    
     @Override
    public LocalDateTime getEndLocalDateTime() {
        return evenement.getTime().toLocalDateTime().plusHours(2);
    }
    
    
 public static  List<EvenementCalendrier>  appointmentsList(List<Event> list){
    List<EvenementCalendrier> appointmets =new ArrayList<EvenementCalendrier>();
        for(int i=0;i<list.size();i++){
            appointmets.add(new EvenementCalendrier(list.get(i)) );    
        }
        return appointmets;
    
    
    
}
    
    
}
