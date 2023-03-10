/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package tn.esprit.gui;

import java.security.GeneralSecurityException;
import tn.esprit.entity.User;
import tn.esprit.services.UserService;
import tn.esprit.tools.sendmail;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabsi
 */
public class FpassController implements Initializable {

    @FXML
    private TextField tfemail_tel;
    @FXML
    private Button btsearch;
    @FXML
    private TextField tfverificationcode;
    @FXML
    private PasswordField pfnew_password;
    @FXML
    private PasswordField pfconfirm;
    @FXML
    private Button btupdate;
    UserService us=new UserService();
    int n;
    User u =new User();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btupdate.setVisible(false);
        pfconfirm.setVisible(false);
        pfnew_password.setVisible(false);
        tfverificationcode.setVisible(false);
        Random rand =new Random();
        n=rand.nextInt(99999);
        
    }    

    @FXML
    private void search(ActionEvent event) throws GeneralSecurityException, SQLException {
             if(us.findByEmail1(tfemail_tel.getText()).isEmpty()==false){
            u=us.findByEmail(tfemail_tel.getText());
            sendmail.sendEmail("ugoapp4@gmail.com" ,"Forgot password","This is your code for updating your password: "+n);
            tfemail_tel.setVisible(true);
            btsearch.setVisible(false);
            btupdate.setVisible(true);
            pfconfirm.setVisible(true);
            pfnew_password.setVisible(true);
            tfverificationcode.setVisible(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid email");
            alert.setContentText("Email doesn't exist");
            alert.showAndWait();
        }
        
        
    }
        
    
    @FXML
    private void update(ActionEvent event) throws SQLException {
         System.out.println(u);
        if(tfverificationcode.getText().equals(String.valueOf(n)) && pfconfirm.getText().equals(pfnew_password.getText())){
            u.setPwd(pfnew_password.getText());
            us.modifier(u.getId(), u);
            try {
            Stage stageclose=(Stage) ((Node)event.getSource()).getScene().getWindow();
            
            stageclose.close();
            Parent root=FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage =new Stage();
            
            Scene scene = new Scene(root);
            
            stage.setTitle("reset password");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FpassController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
}
