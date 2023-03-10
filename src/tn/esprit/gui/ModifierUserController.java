/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.esprit.entity.Role;
import tn.esprit.entity.User;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author gabsi
 */
public class ModifierUserController implements Initializable {

    private String selectedUserId;

    public void initData(String selectedUserId) {
        this.selectedUserId = selectedUserId;
        // Get the selected user's data
        User selectedUser = userService.findByEmail(selectedUserId);
        if (selectedUser != null) {
            // Populate the form with the selected user's data
            tfnom.setText(selectedUser.getNom());
            tfprenom.setText(selectedUser.getPrenom());
            tfemail.setText(selectedUser.getEmail());
            tfpwd.setText(selectedUser.getPwd());
            tfnum.setText(Integer.toString(selectedUser.getNumTel()));
            tfadresse.setText(selectedUser.getAdresse());
            tfsexe.setText(selectedUser.getSexe());
            tfdate.setValue(selectedUser.getDateNaissc().toLocalDate());
            comborole.setValue(selectedUser.getRole());
        }
    }

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpwd;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfsexe;
    @FXML
    private Button btnajouter;
    @FXML
    private DatePicker tfdate;
    @FXML
    private ComboBox<Role> comborole;
    UserService userService = new UserService();
    String erreur;
    List<Role> roleList = Arrays.asList(Role.passager, Role.conducteur, Role.livreur, Role.admin);
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // ChoiceBox<Role> comborole = new ChoiceBox<>();
        comborole.getItems().addAll(roleList);
        initData(selectedUserId);
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        StringBuilder errors = new StringBuilder();
        if (tfnom.getText().trim().isEmpty()) {
            errors.append("- Please enter un Nom\n");
        }
        if (tfprenom.getText().trim().isEmpty()) {
            errors.append("- Please enter un prenon\n");
        }
        if (tfemail.getText().trim().isEmpty()) {
            errors.append("- Please enter un Email\n");
        }
        if (pat.matcher(tfemail.getText()).matches() == false) {

            errors.append("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");

        }
        if (tfnum.getText().trim().length() != 8) {
            errors.append("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        if (tfdate.getValue() == null) {
            errors.append("- Please enter a Date\n");
        }
        if (tfadresse.getText() == null) {
            errors.append("- Please enter a Date\n");
        }
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        } else {

            User u = new User();
            u.setNom(tfnom.getText());
            u.setPrenom(tfprenom.getText());
            u.setEmail(tfemail.getText());
            u.setPwd(tfpwd.getText());
            u.setNumTel(Integer.parseInt(tfnum.getText()));
            u.setDateNaissc(java.sql.Date.valueOf(tfdate.getValue()));
            u.setAdresse(tfadresse.getText());
            u.setSexe(tfsexe.getText());
            u.setRole(comborole.getValue());
            
            User s = userService.findByEmail(selectedUserId);
            userService.modifier(s.getId(),u);
            try {
                //navigation
                Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
                btnback.getScene().setRoot(loader);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            //navigation
            Parent loader = FXMLLoader.load(getClass().getResource("Login.fxml"));
            btnback.getScene().setRoot(loader);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
