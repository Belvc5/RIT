/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Infractor;
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
public class FunInfractor {
    //Instancia a la clase de Conexion
    private Conexion mysql = new Conexion();
    //Llama a la funcion conectar de la variable mysql de la instancia
    private Connection cn = mysql.conectar();
    //Almacenamos la cadena de conexion
    private String sSQL = "";
    //Ingreso otra cadena para insertar al infractor
    private String sSQL2 = "";
    public Integer totalregistros; //total de registros que contiene de mostrar

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        //Declaro Array para almacenar los titulos y registros de la tabla
        String[] titulos = {"ID", "Nombres", "Apellidos", "Doc", "Número Documento", " Dirección","Teléfono", "Email", "Placa Vehiculo"};

        String[] registro = new String[9];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        //Pongo un prefijo p para tabla persona e i para la tabla infractor
        //Uno mis tablas con el inner join
        //Coidan el idpersona de la tabla person e infractor
        //Busco por el numero de documento
        sSQL = "select p.idpersona,p.nombre,p.apellido,p.tipo_documento,p.num_documento,"+
                "p.direccion,p.telefono,p.email,i.placa_vehiculo from persona p inner join infractor i "+
                "on p.idpersona = i.idpersona where num_documento like '%"+ buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            //Busco todos los registros recorriendolos uno a uno
            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("tipo_documento");
                registro[4] = rs.getString("num_documento");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("telefono");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("placa_vehiculo");

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

    public boolean insertar(Infractor dts) {
        //Primero registro los datos de la perosna para registrar al infractor
        sSQL = "insert into persona (nombre,apellido,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?)";
        //Inserto con select el idpersona en la tabla cliente ya que es el mismo
        //Ordeno de manera descendiente y utilizo limit para que nos muestre el
        //ultimo idpersona registrado y ese se almacena, y el segundo dato que es la placa mando el ?
        sSQL2 = "insert into infractor (idpersona,placa_vehiculo)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?)";

        try {
            //contiene toda la cadena para insertar un registro
            //Envio dos conjuntos de parametros
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            //Envio al primer PreparedStatement que ejecuta la cadena sSQL
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTipo_documento());
            pst.setString(4, dts.getNum_documento());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getEmail());

            //Envio al segundo PreparedStatement que ejecuta la cadena sSQL2
            pst2.setString(1, dts.getPlaca_vehiculo());

            //Almacenamos el resultado de la ejecucion
            int n = pst.executeUpdate();

            //Verifico si se inserto un persona
            if (n != 0) {
                int n2 = pst2.executeUpdate();
                //Verifico si se inserto el infractor
                if (n2 != 0) {
                    return true;
                } else {
                    //No se pudo insertar ni la persona ni al infractor
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error insertar " + e);
            return false;
        }
    }

    public boolean editar(Infractor dts) {
        //Modifico los datos del idinfraccion que le mande
        sSQL = "update persona set nombre=?,apellido=?,tipo_documento=?,num_documento=?,"
                + "direccion=?,telefono=?,email=? where idpersona=?";
        
        sSQL2 = "update infractor set placa_vehiculo=?"
                + "where idpersona=?";

        try {
            //contiene toda la cadena para insertar un registro
            //Envio dos conjuntos de parametros
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            //Datos que puedo modificar en el primer pst
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApellido());
            pst.setString(3, dts.getTipo_documento());
            pst.setString(4, dts.getNum_documento());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getEmail());
            pst.setInt(8, dts.getIdpersona());

            //Datos que puedo modificar en el pst2
            pst2.setString(1, dts.getPlaca_vehiculo());
            pst2.setInt(2, dts.getIdpersona());

            //Almacenamos el resultado de la ejecucion
            int n = pst.executeUpdate();

            //Verifico si se inserto un persona
            if (n != 0) {
                int n2 = pst2.executeUpdate();
                //Verifico si se inserto el infractor
                if (n2 != 0) {
                    return true;
                } else {
                    //No se pudo insertar ni la persona ni al infractor
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error editar " + e);
            return false;
        }
    }

    public boolean eliminar(Infractor dts) {
        //Eliminamos los datos primero de la tabla infractor
        sSQL = "delete from infractor where idpersona=?";
        sSQL2 = "delete from persona where idpersona=?";
        try {
            //contiene toda la cadena para insertar un registro
            //Envio dos conjuntos de parametros
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            
            //Utilizo solo el idpersona
            pst.setInt(1, dts.getIdpersona());
            
            pst2.setInt(1, dts.getIdpersona());

            //Almacenamos el resultado de la ejecucion
            int n = pst.executeUpdate();

            //Verifico si se inserto un persona
            if (n != 0) {
                int n2 = pst2.executeUpdate();
                //Verifico si se inserto el infractor
                if (n2 != 0) {
                    return true;
                } else {
                    //No se pudo insertar ni la persona ni al infractor
                    return false;
                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error eliminar " + e);
            return false;
        }
    }
}
