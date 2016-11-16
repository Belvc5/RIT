/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Infraccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Belen
 */
public class FunInfraccion {
    //Instancia a la clase de Conexion
    private Conexion mysql = new Conexion();
    //Llama a la funcion conectar de la variable mysql de la instancia
    private Connection cn = mysql.conectar();
    //Almacenamos la cadena de conexion
    private String sSQL = "";
    public Integer totalregistros; //total de registros que contiene de mostrar
    
    public DefaultTableModel mostrar(String buscar){
        DefaultTableModel modelo;
        //Declaro Array para almacenar los titulos y registros de la tabla
        String [] titulos = {"ID","Tipo Infracción","Clase Contravención",
                  "Descripción","Valor Multa","Disminución Puntos","Días Prisión"};
        
        String [] registro =  new String [7];
        
        totalregistros = 0;
        modelo = new DefaultTableModel(null,titulos);
        
        sSQL = "select * from infraccion where tipo_infraccion like '%"+buscar +"%' order by idinfraccion";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            //Busco todos los registros recorriendolos uno a uno
            while(rs.next()){
                registro[0] = rs.getString("idinfraccion");
                registro[1] = rs.getString("tipo_infraccion");
                registro[2] = rs.getString("clase_contravencion"); 
                registro[3] = rs.getString("descripcion");
                registro[4] = rs.getString("valor_multa");
                registro[5] = rs.getString("puntos_licencia");
                registro[6] = rs.getString("dias_prision");
                
                totalregistros = totalregistros+1;
                 //Agrego en mi tabla modelo en forma de fila mis registros            
                modelo.addRow(registro);
            }
            return modelo;
                    
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR MOSTRAR"+e);
            return null;
        }
        
    }
    
    public boolean insertar (Infraccion dts){
        sSQL = "insert into infraccion (tipo_infraccion,clase_contravencion,descripcion,valor_multa,puntos_licencia,dias_prision)"+
                    "values (?,?,?,?,?,?)";
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL); 
            pst.setString(1, dts.getTipo_infraccion());
            pst.setString(2, dts.getClase_contravencion());
            pst.setString(3, dts.getDescripcion());
            pst.setDouble(4, dts.getValor_multa());
            pst.setString(5, dts.getPuntos_licencia());
            pst.setString(6, dts.getDias_prision());
            
            //Almacenamos el resultado de la ejecucion del statament
            int n = pst.executeUpdate();
            
            //Verificamos la inserion de registros en la tabla
            if(n!=0){
                return true;
                
            }
            else{
                return false;
            }
                  
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error insertar "+e);
            return false;
        }
    }
    
    
    public boolean editar (Infraccion dts){
        //Modifico los datos del idinfraccion que le mande
        sSQL= "update infraccion set tipo_infraccion=?,clase_contravencion=?,descripcion=?,valor_multa=?,puntos_licencia=?,dias_prision=?"+
                "where idinfraccion=?";
        
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL); 
            pst.setString(1, dts.getTipo_infraccion());
            pst.setString(2, dts.getClase_contravencion());
            pst.setString(3, dts.getDescripcion());
            pst.setDouble(4, dts.getValor_multa());
            pst.setString(5, dts.getPuntos_licencia());
            pst.setString(6, dts.getDias_prision());
            pst.setInt(7, dts.getIdinfraccion());
                        
            //Almacenamos el resultado de la ejecucion del statament
            int n = pst.executeUpdate();
            
            //Verificamos la inserion de registros en la tabla
            if(n!=0){
                return true;
                
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
    
    
    public boolean eliminar (Infraccion dts){
        //Eliminamos los datos de la tabla dependido el idinfraccion que le mande
        sSQL="delete from infraccion where idinfraccion=?";
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL); 
            
            pst.setInt(1, dts.getIdinfraccion());
                        
            //Almacenamos el resultado de la ejecucion del statament
            int n = pst.executeUpdate();
            
            //Verificamos la inserion de registros en la tabla
            if(n!=0){
                return true;
                
            }
            else{
                return false;
            }
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            //Error por si no puede eliminar algun registro de mi tabla
            return false; 
        }
    }
    
}
