/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.EvenementCalendrier;
import Entities.Event;
import Services.EventService;
import energym.desktop.MainFX;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.Agenda;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CalendarController implements Initializable {

    @FXML
    private AnchorPane anchorCalendar;
    @FXML
    private BorderPane cell;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VCalendar vCalendar = new VCalendar();
    
     Agenda a=new Agenda();
     a.applyCss();
        List<EvenementCalendrier> elements = participationToAppoitments();
        a.appointments().addAll(elements);
      /*ICalendarAgenda agenda = new ICalendarAgenda(vCalendar);
        BorderPane root = new BorderPane();*/
        
       cell.setCenter(a);
         
    }

    private  List<EvenementCalendrier> participationToAppoitments(){
        EventService participationDao=new EventService();
        List<Event> evenements=participationDao.fetchParticipationByUser(MainFX.UserconnectedC.getId());
        return EvenementCalendrier.appointmentsList(evenements);
        
    }
    
}
