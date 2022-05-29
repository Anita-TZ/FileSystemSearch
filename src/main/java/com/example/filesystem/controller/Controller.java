package com.example.filesystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.sql.*;
import java.util.Properties;


public class Controller {
    private Connection conn;
    private Properties properties;
    private static final String Username = "root";
    private static final String Password = "dev12345";
    private static final String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
    private static final String DatabaseURL = "jdbc:mysql://localhost:3306/devdb";

    @FXML
    private TextField searchBar;
    @FXML
    private TextArea output;


    @FXML
    protected void onHelloButtonClick() {

        if (conn == null) {
            try {
                Class.forName(DatabaseDriver);
                conn = DriverManager.getConnection(DatabaseURL, getProperties());
                System.out.println(conn);//TODO
                getSearchData(conn);
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    private void getSearchData(Connection conn) {
        String SQL = "SELECT path from fileSystem" + " WHERE name LIKE ?";
        try {
            PreparedStatement input = conn.prepareStatement(SQL);
            String show = "";
            //need to get all the file name contains the word
                input.setString(1, "%" + searchBar.getText() + "%");
                ResultSet result = input.executeQuery();
                //Get the result of SQL
                while (result.next()) {
                    show += result.getString("path");
                    show += "\n";
                }
            //show in the screen
            output.setText(show);

            //prevent connection disabled
            result.close();
            input.close();
        } catch (SQLException wrong) {
            System.out.println(wrong.getMessage());
        }
    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", Username);
            properties.setProperty("password", Password);
        }
        return properties;
    }


}