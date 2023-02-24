/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package tn.esprit.gui;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gabsi
 */
public class uggo extends Application {
    
 @Override
    public void start(Stage primaryStage) throws SQLException, IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/AjouterUser.fxml"));
        Scene scene = new Scene(root); 
        primaryStage.setTitle("ADD user");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }    
}
