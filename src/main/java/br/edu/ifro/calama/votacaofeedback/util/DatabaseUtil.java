/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.util;

/**
 *
 * @author Aluno
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
<<<<<<< HEAD
        String password = "!Emn172329";
=======
        String password = "eychila123";
>>>>>>> 4a103610f9dd2bfcbb371268b4a9b63afb5d82b5
        return DriverManager.getConnection(url, user, password);
    }
}