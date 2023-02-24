package tn.esprit.tests;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import tn.esprit.entity.Reclamation;
import tn.esprit.services.ReclamationService;


public class Main {


      public static void main(String[] args) throws SQLException {
          Date d =Date.valueOf(LocalDate.of(2022, Month.MARCH, 17));
    Reclamation r = new Reclamation( "raed", "raed", "raed", 11111111,"aa", "en cours",d);
    ReclamationService ps = new ReclamationService();

 //   ps.ajouterReclamation(r);
    
    //ps.afficherReclamation();
    
    
   //ps.supprimerReclamation(2); 
    ps.modifierReclamation(1,r);
    //System.out.println(ps.afficherReclamation());
    
    }}

