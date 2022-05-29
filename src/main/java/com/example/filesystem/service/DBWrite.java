package com.example.filesystem.service;

import com.example.filesystem.module.DBfile;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;


public class DBWrite {
    ArrayList<DBfile> In = new ArrayList<>();
    private Connection conn;
    private Properties properties;
    private static final String Username = "root";
    private static final String Password = "dev12345";
    private static final String DatabaseDriver = "com.mysql.cj.jdbc.Driver";
    private static final String DatabaseURL = "jdbc:mysql://localhost:3306/devdb";


    public void getTxtData() {

        //Primary key automatically generated
        int id = 1;

        //Read data from txt
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader
                    (Paths.get("fileStructure.txt").toString()));
            String line = bufferedReader.readLine();
            do {
                String path = line.split("\t")[0];
                String type= line.split("\t")[1];
                String name = path.substring(path.lastIndexOf('\\') + 1);
                In.add(new DBfile(name,path, type));
                In.get(id-1).setID(id);
                id++;
                line = bufferedReader.readLine();
            } while (line != null);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OpenSQL() {
        //String SQL = "INSERT INTO fileSystem(id,path) " + "VALUES(?,?)";
        if (conn == null) {
            try {
                Class.forName(DatabaseDriver);
                conn = DriverManager.getConnection(DatabaseURL, getProperties());
                InitialDB();
                InsertData();
                //disconnection
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

    public void InsertData() {
        String SQL = "INSERT INTO fileSystem(id,name,path) " + "VALUES(?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(SQL);
            int num = 0;
            for (DBfile values : In) {
                statement.setInt(1, values.getID());
                statement.setString(2, values.getName());
                statement.setString(3, values.getPath());
                statement.addBatch();
                num++;
                //Batch Input at 80 data or achieve th size of In
                if (num == In.size() || num % 80 == 0) {
                    statement.executeBatch();
                }
            }
            statement.close();
        } catch (SQLException wrong) {
            System.out.println(wrong.getMessage());
        }

    }

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", Username);
            properties.setProperty("password", Password);
            //properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    private void InitialDB() {

        String drop = "DROP TABLE IF EXISTS fileSystem;";
        String create = "CREATE TABLE fileSystem (id int PRIMARY KEY," +
                "  name varchar(100) NOT NULL," +
                "  path varchar(6000) NOT NULL" +
                ");";

        try {
            PreparedStatement statement = conn.prepareStatement(drop);
            statement.execute();
            statement= conn.prepareStatement(create);
            statement.execute();

        } catch (SQLException wrong) {
            System.out.println(wrong.getMessage());
        }
    }
}


