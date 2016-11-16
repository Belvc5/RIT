/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Empleado;
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
public class FunEmpleado {
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
        String[] titulos = {"ID", "Nombres", "Apellidos", "Doc", "Número Documento", " Dirección","Teléfono", "Email", "Sueldo","Acceso","Login","Clave","Estado"};

        String[] registro = new String[13];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        //Pongo un prefijo p para tabla persona e i para la tabla infractor
        //Uno mis tablas con el inner join
        //Coidan el idpersona de la tabla person e infractor
        //Busco por el numero de documento
        sSQL = "select p.idpersona,p.nombre,p.apellido,p.tipo_documento,p.num_documento,"+
                "p.direccion,p.telefono,p.email,e.sueldo,e.acceso,e.login,e.password,e.estado from persona p inner join empleado e "+
                "on p.idpersona = e.idpersona where num_documento like '%"+ buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            //Busco todos los registros recorriendolos uno a uno
            //Los nombre de los campos deben ser iguales a como esta en la base de datos
            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("tipo_documento");
                registro[4] = rs.getString("num_documento");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("telefono");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("sueldo");
                registro[9] = rs.getString("acceso");
                registro[10] = rs.getString("login");
                registro[11] = rs.getString("password");
                registro[12] = rs.getString("estado");

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

    public boolean insertar(Empleado dts) {
        //Primero registro los datos de la perosna para registrar luego al empleago
        sSQL = "insert into persona (nombre,apellido,tipo_documento,num_documento,direccion,telefono,email)"
                + "values (?,?,?,?,?,?,?)";
        //Inserto con select el idpersona en la tabla cliente ya que es el mismo
        //Ordeno de manera descendiente y utilizo limit para que nos muestre el
        //ultimo idpersona registrado y ese se almacena, y el segundo los datos de la tabla empleado
        sSQL2 = "insert into empleado (idpersona,sueldo,acceso,login,password,estado)"
                + "values ((select idpersona from persona order by idpersona desc limit 1),?,?,?,?,?)";

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
            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());

            //Almacenamos el resultado de la ejecucion
            int n = pst.executeUpdate();

            //Verifico si se inserto un persona
            if (n != 0) {
                int n2 = pst2.executeUpdate();
                //Verifico si se inserto el empleado
                if (n2 != 0) {
                    return true;
                } else {
                    //No se pudo insertar ni la persona ni al empleado
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

    public boolean editar(Empleado dts) {
        //Modifico los datos del idinfraccion que le mande
        sSQL = "update persona set nombre=?,apellido=?,tipo_documento=?,num_documento=?,"
                + "direccion=?,telefono=?,email=? where idpersona=?";
        
        sSQL2 = "update empleado set sueldo=?,acceso=?,login=?,password=?,estado=?"
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
            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());

            //Almacenamos el resultado de la ejecucion
            int n = pst.executeUpdate();

            //Verifico si se inserto un persona
            if (n != 0) {
                int n2 = pst2.executeUpdate();
                //Verifico si se inserto el empleado
                if (n2 != 0) {
                    return true;
                } else {
                    //No se pudo insertar ni la persona ni al empleado
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

    public boolean eliminar(Empleado dts) {
        //Eliminamos los datos primero de la tabla empleado
        sSQL = "delete from empleado where idpersona=?";
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
                //Verifico si se inserto el empleado
                if (n2 != 0) {
                    return true;
                } else {
                    //No se pudo insertar ni la persona ni al empleado
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
    //Creo la funcion login, guardo en la tabla los datos del usuario que inici sesion
    public DefaultTableModel login(String login, String password) {
        DefaultTableModel modelo;
        //Declaro Array para almacenar los titulos y registros de la tabla
        String[] titulos = {"ID", "Nombres", "Apellidos", "Acceso","Login","Clave","Estado"};

        String[] registro = new String[7];

        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        //Pongo un prefijo p para tabla persona e i para la tabla empleado
        //Uno mis tablas con el inner join
        //Debe coincidir el idpersona de la tabla persona y empleado
        //Filtor los datos con el campo login y password. 
        //Evaluo la cuenta el login corresponda a ese password y verifico que este activada
        sSQL = "select p.idpersona,p.nombre,p.apellido,e.acceso,e.login,e.password,"
                +"e.estado from persona p inner join empleado e "
                +"on p.idpersona = e.idpersona where e.login = '"
                + login + "' and e.password = '" + password + "' and e.estado = 'Habilitado'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);
            //Busco todos los registros recorriendolos uno a uno
            //Los nombre de los campos deben ser iguales a como esta en la base de datos
            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("acceso");
                registro[4] = rs.getString("login");
                registro[5] = rs.getString("password");
                registro[6] = rs.getString("estado");

                totalregistros = totalregistros + 1;
                //Agrego en mi tabla modelo en forma de fila mis registros            
                modelo.addRow(registro);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR Login" + e);
            return null;
        }

    }
    
}
