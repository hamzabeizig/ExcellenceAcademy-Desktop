/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


/**
 *
 * @author ASUS
 */
public class Pi extends Application {
           private static Stage stg ;
           
   @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        
        Parent root = FXMLLoader.load(getClass().getResource("authentification.fxml"));
        Scene scene = new Scene(root);
        
        
    
        
        stage.setResizable(false);
        stage.getIcons().add(new Image("/gui/pics/iconelogo.png"));
        stage.setTitle("Exellence Academy");
       
        
        stage.setScene(scene);
        stage.show();
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

        public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    
    }
    
}
