/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smusic;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Aditya Sinha
 */
public class UIController implements Initializable {
    
    
    @FXML
    private AnchorPane homepane;
    @FXML
    private Label lbl_close;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) {
        if(event.getSource()==lbl_close)
            System.exit(0);
    }
    
}
