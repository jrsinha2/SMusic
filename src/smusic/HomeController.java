/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smusic;

import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Aditya Sinha
 */
public class HomeController implements Initializable {
    @FXML
    private AnchorPane pn_menu;
    @FXML
    private AnchorPane pn_genmenu;
    @FXML
    private AnchorPane pn_personal;
    @FXML
    private AnchorPane pn_body;
    @FXML
    private AnchorPane pn_player;
    @FXML
    private ImageView btn_play;
    @FXML
    private ImageView btn_pause;
    @FXML
    private ImageView seek_back;
    @FXML
    private ImageView seek_fwd;
    @FXML
    private JFXProgressBar seek_bar;
    @FXML
    private ImageView repeat;
    @FXML
    private ImageView repeat1;
    @FXML
    private ImageView seek_fwd1;
    @FXML
    private ImageView seek_fwd11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
