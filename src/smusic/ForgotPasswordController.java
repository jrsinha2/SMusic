/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smusic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */

public class ForgotPasswordController {

    @FXML
    private AnchorPane homepane;

    @FXML
    private JFXTextField email;


    @FXML
    private JFXButton btnSignUp;

    @FXML
    private Label lbl_close;

    @FXML
    private Label lbl_errors;

    @FXML
    private Label lbl_skip;
    @FXML
    private JFXButton btnResetPass;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    void handleButtonAction(MouseEvent event) {
        if(event.getSource()==lbl_close) {
            System.exit(0);
        }
        if(event.getSource()==btnResetPass) {
            //resetPass
            lbl_errors.setText("");
            if(resetPassword().equals("Success")) {
                System.out.println("Next Window");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }    
            }
        }
        if(event.getSource()==btnSignUp) {
            System.out.println("Next Window");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SignUp.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }
        if(event.getSource()==lbl_skip) {
            System.out.println("Next Window");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
        }
    }
    private String resetPassword() {
        String enteredEmail = email.getText();
        //query
        String findSql = "SELECT * FROM users WHERE email = ?";
        
        try{
            preparedStatement = conn.prepareStatement(findSql);
            preparedStatement.setString(1,enteredEmail);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                lbl_errors.setTextFill(Color.TOMATO);
                lbl_errors.setText("Account does not exist");
                return "ERROR";
            }
            else
            {   int pass = (int) (Math.random()*(999999-100000) + 100000);
                String passString = String.valueOf(pass);
                String enterPassword = String.valueOf(passString.hashCode());
                //mail to user random number
                lbl_errors.setTextFill(Color.GREEN);
                lbl_errors.setText("Check your email");
                System.out.println("Mailing System need to be added");
                return "SUCCESS";
            }
        
        }   
        catch (SQLException ex) {
            
            return "Exception";
        }
    }
    public ForgotPasswordController() {
        conn = ConnectionUtil.conDB();
    }
}



