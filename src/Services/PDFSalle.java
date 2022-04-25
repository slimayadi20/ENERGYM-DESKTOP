/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Salle;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author omar
 */
public class PDFSalle {
         public void liste_SallePDF() throws FileNotFoundException, DocumentException {

        SalleService ec = new SalleService();
        String message = "Voici la liste des salles \n\n";

        String file_name = "src/PDF/liste_Salle.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Salle> Salle = ec.afficher();
        PdfPTable table = new PdfPTable(5);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("Nom de la salle"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Description"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("adresse"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("mail"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("Affiche de l'événement"));
        table.addCell(cl4);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < Salle.size(); i++) {
            
            table.addCell("" + Salle.get(i).getNom());
            table.addCell("" + Salle.get(i).getDescription());
            table.addCell("" + Salle.get(i).getAdresse());
            table.addCell("" + Salle.get(i).getMail());
            table.addCell("" + Salle.get(i).getImage());

        }
        document.add(table);

        document.close();

    }
         
}
