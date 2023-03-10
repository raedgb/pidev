/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import tn.esprit.entity.User;
import tn.esprit.services.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javax.mail.MessagingException;
import tn.esprit.services.UserSession;


/**
 * FXML Controller class
 *
 * @author gabsi
 */
public class LoginController implements Initializable {
private static String currentMailReset;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpwd;

    private Label errorLabel;
 
    private Connection connection;
    UserService us =new UserService() ;
     User u2,u3,u4;
     String codeV;
     Parent root; 
      Stage stage;
      int btn=0;
    private Scene scene;
    private TextField resetcodeField;
    @FXML
    private Button btnlogin;
    @FXML
    private Button btnsignup;
    @FXML
    private Label ForgotPassword;   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {

        if (us.checklogin(tfemail.getText(), tfpwd.getText())) {
            us.userSession =new UserSession();
            us.userSession.setUserEmail(tfemail.getText());
            System.out.println(us.userSession.getUser());
        
            User u = us.findByEmail(tfemail.getText());
            switch (u.getRole()) {
                case admin:
                    try {
               
                    Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stageclose.close();
                  
                    Parent root = FXMLLoader.load(getClass().getResource("AfficherUser.fxml"));

                    Stage stage = new Stage();

                    Scene scene = new Scene(root);

                    stage.setTitle("Dashbord Admin");
                    stage.setScene(scene);
                    stage.show();
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case conducteur:
                     System.out.println("bienveunue Conducteur");
                    try {
               
                    Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    stageclose.close();
                  
                    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

                    Stage stage = new Stage();

                    Scene scene = new Scene(root);

                    stage.setTitle("Dashbord Admin");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                case livreur:
                    System.out.println("bienveunue livreur");
                    try {
                    
                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("AjouterUser.fxml"));
             

                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("INVICTUS APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case passager:
                    System.out.println("bienveunue passenger");
                    try {
                    
                        Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        stageclose.close();
                        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                        Stage stage = new Stage();

                        Scene scene = new Scene(root);

                        stage.setTitle("INVICTUS APP");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                default:
                    break;
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login fail");
            alert.setContentText("Username or password invalid");
            alert.showAndWait();
        }
    }
   

    

    @FXML
    private void signup(ActionEvent event) {
    
    System.out.print("hi");
    try {
        // Charger le fichier FXML de la page d'inscription
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterUser.fxml"));
        Parent root = loader.load();
        // CrÃ©er un nouveau stage pour la fenÃªtre d'inscription
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Inscription");
        stage.show();
       
    } catch (IOException e) {
    }
}

    @FXML
     public void LinkToResetPassword() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Fpass.fxml"));
        uggo.primaryStage.setScene(new Scene(root));
        uggo.primaryStage.show();

       
   

     }
}
        

  
    

