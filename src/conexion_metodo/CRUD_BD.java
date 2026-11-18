/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion_metodo;

/**
 *
 * @author lenovo
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CRUD_BD {
    Conexion_Metodo conexion = new Conexion_Metodo();
    
    
    public void consultar (JTable tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Folio");
        modelo.addColumn("Nombre");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        
        String sql = "SELECT * FROM Pedido";
        
        try {
            Connection bd = conexion.getConnection();
            PreparedStatement ps = bd.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                Object[] fila = new Object[4];
                
                fila[0] = rs.getInt("Folio");
                fila[1] = rs.getString("Nombre");
                fila[2] = rs.getString("Telefono");
                fila[3] = rs.getString("Direccion");
                
                modelo.addRow(fila);
            }
            
            tabla.setModel(modelo);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al consulat: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    public void insertar (String folio, String Nombre, String Telefono, String Direccion){
        String sql = "INSERT INTO Pedido (Folio, Nombre, Telefono, Direccion) VALUES (?,?,?,?)";
        
        try {
            Connection bd = conexion.getConnection();
            PreparedStatement ps = bd.prepareStatement(sql);
            
            ps.setInt(1, Integer.parseInt(folio));
            ps.setString(2, Nombre);
            ps.setString(3, Telefono);
            ps.setString(4, Direccion);
            
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);           
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al incertar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "El folio debe ser numerico", "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizar (String folio, String Nombre, String Telefono, String Direccion){
        String sql = "UPDATE Pedido SET Nombre = ?, Telefono = ?, Direccion = ? WHERE Folio = ?";
        
        try {
            Connection bd = conexion.getConnection();
            PreparedStatement ps = bd.prepareStatement(sql);
            
            ps.setString(1, Nombre);
            ps.setString(2, Telefono);
            ps.setString(3, Direccion);
            ps.setInt(4, Integer.parseInt(folio));
            
            int resultado = ps.executeUpdate();
            
            if (resultado > 0){
                JOptionPane.showMessageDialog(null,"Datos actualizados", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el folio indicado", "Error", JOptionPane.ERROR_MESSAGE);
            }            
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"El folio debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo conectar con la base: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }      
    }
    
    public void eliminar (String folio){
        String sql = "DELETE FROM Pedido WHERE Folio = ?";
        
        try {
            Connection bd = conexion.getConnection();
            PreparedStatement ps = bd.prepareStatement(sql);
            
            ps.setInt(1, Integer.parseInt(folio));
            int resultado = ps.executeUpdate();
            
            if (resultado > 0){
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro registros con ese folio", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error al eliminar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El folio debe ser numerico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiar (JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
    }
    
}
