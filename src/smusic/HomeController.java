package smusic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import utils.ConnectionUtil;

public class HomeController {

    private AnchorPane homepane;

    @FXML
    private AnchorPane pn_menu;

    @FXML
    private AnchorPane pn_genmenu;

    @FXML
    private Label lbl_menu;

    @FXML
    private JFXButton btn_discover;

    @FXML
    private ImageView icon_discover;

    @FXML
    private JFXButton btn_artists;

    @FXML
    private ImageView icon_artists;

    @FXML
    private JFXButton btn_albums;

    @FXML
    private ImageView icon_albums;

    @FXML
    private JFXButton btn_songs;

    @FXML
    private ImageView icon_songs;

    @FXML
    private AnchorPane pn_personal;

    @FXML
    private Label lbl_personal;

    @FXML
    private JFXButton btn_playlists;

    @FXML
    private ImageView icon_playlists;

    @FXML
    private JFXButton btn_favourites;

    @FXML
    private ImageView icon_favourites;


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
    Connection conn = null;
    @FXML
    private VBox title_bar;
    @FXML
    private BorderPane borderpane;
    
    public HomeController() {
        conn = ConnectionUtil.conDB();
    }
    @FXML
    void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btn_discover) {
            loadUI("Discover");
            System.out.println(UserSession.getUserName());
        }
        else if (event.getSource() == btn_artists) {
            loadUI("Artists");
        }
        else if (event.getSource() == btn_albums) {
            loadUI("Albums");
        }
        else if (event.getSource() == btn_songs) {
            loadUI("Songs");
        }
    }
    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
            borderpane.setCenter(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
           
    }
}
