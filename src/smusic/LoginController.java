/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smusic;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utils.ConnectionUtil;

/**
 *
 * @author Aditya Sinha
 */
public class LoginController implements Initializable {
    
    
    @FXML
    private AnchorPane homepane;
    @FXML
    private Label lbl_close;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label lbl_errors;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private JFXButton btnSignUp;
    @FXML
    private Label lbl_skip;
    @FXML
    private Label lbl_forgotpass;
    public LoginController() {
        conn = ConnectionUtil.conDB();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(javafx.scene.input.MouseEvent event) {
        if(event.getSource()==lbl_close)
            System.exit(0);
        if(event.getSource()==btnSignIn) {
            //login here
            lbl_errors.setText("");
            if(logIn().equals("Success")) {
                System.out.println("Next Window");
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();
                
                try {
                    Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Home.fxml")));
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println("Cannot load Home.fxml");
                    ex.printStackTrace();
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
                    System.out.println("Cannot load Home.fxml");
                    ex.printStackTrace();
                }
        }
        if(event.getSource()==lbl_forgotpass){
            System.out.println("Next Window");
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ForgotPassword.fxml")));
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println("Cannot load Home.fxml");
                ex.printStackTrace();
            }
        }
    }
    
    
    private String logIn() {
        String enteredEmail = username.getText();
        String enteredUsername = enteredEmail;
        String enteredPassword = password.getText();
        

        //query
        String sql = "SELECT * FROM users WHERE (email = ? or username = ?) and password = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,enteredEmail);
            preparedStatement.setString(2,enteredUsername);
            if(!(enteredEmail.equals("aditya.sinha99@gmail.com") || enteredUsername.equals("smusic99")))
                enteredPassword = String.valueOf(enteredPassword.hashCode());
            preparedStatement.setString(3,enteredPassword);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                lbl_errors.setTextFill(Color.TOMATO);
                lbl_errors.setText("Invalid username/email address");
                System.err.println("Enter correct username/email");
                return "Error";
            }
            else
            {   
                //showDialog("Login Successful",null,"Successful");
                System.out.println("Login Successful");
                UserSession.createUser(enteredUsername, null);
                return "Success";
            }
        } catch (SQLException ex) {
            return "Exception";
        }
    }
    
    
}
