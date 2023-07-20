package org.carrental.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class BaseDAO {

    final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
    final String USER = "root";
    final String PASS = "Root123+";
    Connection conn;

    BaseDAO(){
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
