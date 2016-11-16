/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Registro;
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
public class FunRegistro {
    //Instancia a la clase de Conexion
    private Conexion mysql = new Conexion();
    //Llama a la funcion conectar de la variable mysql de la instancia
    private Connection cn = mysql.conectar();
    //Almacenamos la cadena de conexion
    private String sSQL = "";
    public Integer totalregistros; //total de registros que contiene de mostrar
    
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        //Declaro Array para almacenar los titulos y registros de la tabla
        String[] titulos = {"ID","Fecha","Hora","Lugar","Idinfracción","Tipo Infracción","Descripción","Multa","Idinfractor","Infractor","Correo","Placa","Idempleado","Empleado","Estado"};

        String[] registro = new String[15];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        //Pongo un prefijo r para tabla registro e i para la tabla infraccion
        //Uno mis tablas con el inner join
        //Haga una consulta de los nombres y apellidos que se encuentran en la tabla persona
        //Coidan el idpersona de la tabla persona y reserva
        //Busco por la fecha
        sSQL =  "select r.idregistro,r.fecha,r.hora,r.lugar,r.idinfraccion,i.tipo_infraccion,i.clase_contravencion,"+
                "i.descripcion,i.valor_multa,i.puntos_licencia,i.dias_prision,r.idinfractor,"+
                "(select nombre from persona where idpersona = r.idinfractor)as infractorn,"+
                "(select apellido from persona where idpersona = r.idinfractor)as infractorap,"+
                "(select email from persona where idpersona = r.idinfractor)as correo,"+
                "(select placa_vehiculo from infractor where idpersona = r.idinfractor)as placa,"+
                "r.idempleado,(select nombre from persona where idpersona = r.idempleado)as empleadon,"+
                "(select apellido from persona where idpersona = r.idempleado)as empleadoap,"+
                "r.estado from registro r inner join infraccion i on  r.idinfraccion = i.idinfraccion  where r.fecha like '%"+buscar +"%' order by idregistro desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            //Busco todos los registros recorriendolos uno a uno
            //Los nombre de los campos deben ser iguales a como esta en la base de datos
            while (rs.next()) {
                registro[0] = rs.getString("idregistro");
                registro[1] = rs.getString("fecha");
                registro[2] = rs.getString("hora");
                registro[3] = rs.getString("lugar");
                registro[4] = rs.getString("idinfraccion");
                registro[5] = rs.getString("tipo_infraccion")+ " de " + rs.getString("clase_contravencion");
                registro[6] = rs.getString("descripcion");
                registro[7] = rs.getString("valor_multa")+ " dólares, con " + rs.getString("puntos_licencia")+ " en la licencia de conducir y  de " + rs.getString("dias_prision")+ " de prisión";
                registro[8] = rs.getString("idinfractor");
                registro[9] = rs.getString("infractorn")+ " " + rs.getString("infractorap");
                registro[10] = rs.getString("correo");
                registro[11] = rs.getString("placa");
                registro[12] = rs.getString("idempleado");
                registro[13] = rs.getString("empleadon")+ " " + rs.getString("empleadoap");
                registro[14] = rs.getString("estado");                              

                totalregistros = totalregistros + 1;
                //Agrego en mi tabla modelo en forma de fila mis registros            
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR MOSTRAR" + e);
            return null;
        }

    }
    
    public boolean insertar (Registro dts){
        //Inserto los datos en mi tabla registro
        sSQL = "insert into registro (fecha,hora,lugar,idinfraccion,idinfractor,idempleado,estado)"
                + "values (?,?,?,?,?,?,?)";
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL); 
            pst.setDate(1, dts.getFecha());
            pst.setString(2, dts.getHora());
            pst.setString(3, dts.getLugar());
            pst.setInt(4, dts.getIdinfraccion());
            pst.setInt(5, dts.getIdinfractor());
            pst.setInt(6, dts.getIdempleado());
            pst.setString(7, dts.getEstado());
          
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
    
    
    public boolean editar (Registro dts){
        //Modifico los datos del idinfraccion que le mande
        sSQL= "update registro set fecha=?,hora=?,lugar=?,idinfraccion=?,idinfractor=?,idempleado=?,estado=?"+
                "where idregistro=?";
        
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL); 
            pst.setDate(1, dts.getFecha());
            pst.setString(2, dts.getHora());
            pst.setString(3, dts.getLugar());
            pst.setInt(4, dts.getIdinfraccion());
            pst.setInt(5, dts.getIdinfractor());
            pst.setInt(6, dts.getIdempleado());
            pst.setString(7, dts.getEstado());
            pst.setInt(8, dts.getIdregistro());
                        
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
    
    
    public boolean eliminar (Registro dts){
        //Eliminamos los datos de la tabla dependido el idinfraccion que le mande
        sSQL="delete from registro where idregistro=?";
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL); 
            
            pst.setInt(1, dts.getIdregistro());
                        
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
    
    public boolean enviado (Registro dts){
        //Modifico los datos del idinfraccion que le mande
        sSQL= "update registro set estado='Enviada'"+
                "where idregistro=?";
        
        try {
            //contiene toda la cadena para insertar un registro
            PreparedStatement pst = cn.prepareStatement(sSQL);      
            pst.setInt(1, dts.getIdregistro());
                        
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
    
    
}
