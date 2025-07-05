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
<<<<<<< HEAD
        String password = "!Emn172329";
=======
        String password = "floripa123";
>>>>>>> 81bdc61df21ada09ad9e1bdb5e6090f806210324
>>>>>>> 957c8a7f2499e0ff8ac4831932380c10cbd22a76
        return DriverManager.getConnection(url, user, password);
    }
}