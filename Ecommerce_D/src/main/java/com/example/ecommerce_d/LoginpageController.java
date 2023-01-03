package com.example.ecommerce_d;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
//import org.w3c.dom.events.MouseEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginpageController {
    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML


    public void login(MouseEvent e) throws SQLException, IOException {
        String query =String.format("Select * from user where emailID ='%s' AND pass ='%s'",email.getText(),password.getText());
        // query ="select * from user where emailId = 'robin@gmail.com' AND pass='1234'";
        ResultSet res=HelloApplication.connection.executeQuery(query);


            if (res.next()) {
                HelloApplication.emailId=res.getString("emailID");
                String userType=res.getString("userType");
                if(userType.equals("Seller"))
                {

                    AnchorPane sellerPage= FXMLLoader.load((getClass().getResource("Sellerpage.fxml")));
                    HelloApplication.root.getChildren().add(sellerPage);
                }
                else
                {
                    System.out.println("We are logged in as Buyer");
                    ProductPage productPage=new ProductPage();

                    Header header=new Header();

                    AnchorPane productPane= new AnchorPane();
                    productPane.getChildren().add(productPage.products());
                    productPane.setLayoutX(150);
                    productPane.setLayoutY(100);
                    HelloApplication.root.getChildren().clear();
                    HelloApplication.root.getChildren().addAll(header.root,productPane);


                }
                System.out.println("The user is present in the User Table");
            }
            else{
                Dialog<String> dialog = new Dialog<>();
                dialog.setTitle("Login");
                ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.setContentText("Login Failed, please check username/password and try again");
                dialog.showAndWait();


            }


    }
}
