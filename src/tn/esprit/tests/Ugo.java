/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.sql.SQLException;
import tn.esprit.entity.User;
import tn.esprit.services.UserService;
import java.sql.Date; 
import java.time.LocalDate;
import java.time.Month;
import javax.management.relation.Role;
import tn.esprit.entity.Reclamation;
import static tn.esprit.entity.Role.passager;
import static tn.esprit.entity.Role.conducteur ;
import static tn.esprit.entity.Role.livreur;
import tn.esprit.services.ReclamationService;

/**
 *
 * @author Fayechi
 */
public class Ugo {


      public static void main(String[] args) throws SQLException {
          Date d =Date.valueOf(LocalDate.of(2022, Month.MARCH, 17));
        UserService userService= new UserService();

     User user1=new User("raede","raed1","raed11","raed1111",12,d,"azerty","raed",conducteur);
       
 //userService.ajouter(user1);
  userService.modifier(19,user1); 
  //  userService.getAll();



    Reclamation r = new Reclamation( "raed", "raed", "raed", 42548769,"aa", "en cours",d);
    ReclamationService ps = new ReclamationService();

   // ps.ajouterReclamation(r);
    
    //ps.afficherReclamation();
    
    
    //ps.supprimerReclamation(137); 
    //ps.modifierReclamation(131,r);
    //System.out.println(ps.afficherReclamation());
    
    }
    
    }




