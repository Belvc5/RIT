/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.Date;

/**
 *
 * @author Belen
 */
public class Registro {
    private int idregistro;
    private int idinfraccion;
    private int idinfractor;
    private int idempleado;
    private Date fecha;
    private String hora;
    private String lugar;
    private String estado;   

    public Registro() {
    }

    public Registro(int idregistro, int idinfraccion, int idinfractor, int idempleado, Date fecha, String hora, String lugar, String estado) {
        this.idregistro = idregistro;
        this.idinfraccion = idinfraccion;
        this.idinfractor = idinfractor;
        this.idempleado = idempleado;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.estado = estado;
    }

    public int getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(int idregistro) {
        this.idregistro = idregistro;
    }

    public int getIdinfraccion() {
        return idinfraccion;
    }

    public void setIdinfraccion(int idinfraccion) {
        this.idinfraccion = idinfraccion;
    }

    public int getIdinfractor() {
        return idinfractor;
    }

    public void setIdinfractor(int idinfractor) {
        this.idinfractor = idinfractor;
    }

    public int getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(int idempleado) {
        this.idempleado = idempleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
        
}
