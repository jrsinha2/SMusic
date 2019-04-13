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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


public class SignUpController {

    @FXML
    private AnchorPane homepane;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private JFXPasswordField password;

    @FXML
    private Label lbl_close;


    @FXML
    private JFXTextField email;
    @FXML
    private Label lbl_skip;
    Connection conn = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    @FXML
    private Label lbl_errors;
    
    public SignUpController() {
        conn = ConnectionUtil.conDB();
    }

    @FXML
    void handleButtonAction(MouseEvent event) {
        if(event.getSource()==lbl_close)
            System.exit(0);
        if(event.getSource()==btnSignUp) {
            //signup here
            if(signUp().equals("SUCCESS")) {
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
        if(event.getSource()==btnSignIn) {
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
    private String signUp() {
        String enteredUsername = username.getText();
        String enteredEmail = email.getText();
        String enteredPassword = password.getText();
        
        //query
        String findSql = "SELECT * FROM users WHERE username = ? or email = ?";
        
        try{
            preparedStatement = conn.prepareStatement(findSql);
            preparedStatement.setString(1,enteredUsername);
            preparedStatement.setString(2,enteredEmail);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next())
            {
                String insertSql = "INSERT INTO users VALUES(?,?,?)";
                try
                {   PreparedStatement preparedStatement1;
                    preparedStatement1 = conn.prepareStatement(insertSql);
                    preparedStatement1.setString(1,enteredUsername);
                    preparedStatement1.setString(2,enteredEmail);
                    int hash = enteredPassword.hashCode();
                    enteredPassword = String.valueOf(hash);
                    preparedStatement1.setString(3,enteredPassword);
                    preparedStatement1.executeUpdate();  
                    return "SUCCESS";
                }
                catch(Exception ex)
                {   System.err.println("query wrong" + ex.getMessage());
                    return "Exception";
                }
            }
            else
            {
                lbl_errors.setTextFill(Color.TOMATO);
                lbl_errors.setText("Account already exists");
                return "Error";
            }
        }
        catch(Exception ex) {
            return "Exception";
        }
    }

}
