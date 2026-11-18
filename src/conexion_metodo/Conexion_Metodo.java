/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion_metodo;

/**
 *
 * @author lenovo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion_Metodo {

    private final String URL = "jdbc:mysql://localhost:3306/Prueba_3";
    private final String USER = "root";
    private final String Contraseña = "123456789";
    
    public Connection getConnection(){
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, Contraseña);
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return conexion;
    } 
}
