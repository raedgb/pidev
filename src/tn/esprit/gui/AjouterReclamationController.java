/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import tn.esprit.entity.Reclamation;
import tn.esprit.tools.MaConnection;
import tn.esprit.services.ReclamationService;
import java.awt.HeadlessException;
import static java.awt.SystemColor.control;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterReclamationController implements Initializable {

    ReclamationService rs = new ReclamationService();
 private String selectedReclamationId;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tftel;
    @FXML
    private TextField tfetat;
    @FXML
    private TextField tfdescription;
    @FXML
    private DatePicker tfdatereclamation;
    @FXML
    private Button btnajouter;

   Notifications no;
    String erreur;
    int id_d;

    /*
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouterreclamation(ActionEvent event) throws IOException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        StringBuilder errors = new StringBuilder();
        //  if (tfnom.getText().isEmpty()|| tfprenom.getText().isEmpty()|| tfemail.getText().isEmpty()||tftel.getText().isEmpty()||tfdescription.getText().isEmpty()|| tfdatereclamation.getText().isEmpty()){
        //control.setText("field is empty");
        //}
        if (tfnom.getText().trim().isEmpty()) {
            errors.append("- Please enter un Nom\n");
        }
        if (tfemail.getText().trim().isEmpty()) {
            errors.append("- Please enter un Email\n");
        }
        if (pat.matcher(tfemail.getText()).matches() == false) {

            errors.append("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");

        }
        if (tftel.getText().trim().length() != 8) {
            errors.append("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        if (tfdatereclamation.getValue() == null) {
            errors.append("- Please enter a Date\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            Reclamation u = new Reclamation();
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setTel(Integer.parseInt(tftel.getText()));
            u.setEtat(tfetat.getText());
            u.setDescription(tfdescription.getText());
            u.setDate_reclamation(java.sql.Date.valueOf(tfdatereclamation.getValue()));

            try {
                rs.ajouterReclamation(u);

                no = Notifications.create()
                        .title("Reclamation Ajoutée")
                        .text(erreur)
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(6));
                no.showInformation();
                //     reset();*/
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    

    private void modifierReclamation(ActionEvent event) {

        StringBuilder errors = new StringBuilder();
        if (tfnom.getText().trim().isEmpty()) {
            errors.append("Ajouter un nom \n");
        }
        if (tfprenom.getText().trim().isEmpty()) {
            errors.append("Ajouter un prenom\n");
        }
        if (tfemail.getText().trim().isEmpty()) {
            errors.append("Ajouter un e-mail valide\n");
        }

        if (tfetat.getText().trim().isEmpty()) {
            errors.append("Ajouter l'etat \n");
        }
        if (tfdescription.getText().trim().isEmpty()) {
            errors.append("Ajouter une description\n");
        }
        if (tfdatereclamation.getValue() == null) {
            errors.append("Ajouter une date de réclamation\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {
            String nom = tfnom.getText();
            String prenom = tfprenom.getText();
            String email = tfemail.getText();
            String telString = tftel.getText();
            if (!telString.matches("\\d+")) {
                errors.append("Ajouter un numero de tel valide \n");
            } else {
                int tel = Integer.parseInt(telString);
               
            }
            String etat = tfetat.getText();
            String description = tfdescription.getText();
            LocalDate datereclamation = tfdatereclamation.getValue();

            //Reclamation d = new Reclamation(nom, prenom, email, String.valueOf(tel), etat, description, datereclamation.toString());
            ReclamationService ds = new ReclamationService();
            //rs.modifierReclamation(id_d, d);
        }
   
    }
  
        
    

public void setTextField(int id, String nom, String prenom, String email, String tel, String etat, String description, Date date_reclamation) {
        id_d = id;
        tfnom.setText(nom);
        tfprenom.setText(prenom);
        tfemail.setText(email);
        tftel.setText(tel);
        tfetat.setText(etat);
        tfdescription.setText(description);
        LocalDate localDate = date_reclamation.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        tfdatereclamation.setValue(localDate);

    }

    

}
