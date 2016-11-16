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
public class Infraccion {
    private int idinfraccion;
    private String tipo_infraccion;
    private String clase_contravencion;
    private String descripcion;
    private double valor_multa;
    private String puntos_licencia;
    private String dias_prision;

    public Infraccion() {
    }

    public Infraccion(int idinfraccion, String tipo_infraccion, String clase_contravencion, String descripcion, double valor_multa, String puntos_licencia, String dias_prision) {
        this.idinfraccion = idinfraccion;
        this.tipo_infraccion = tipo_infraccion;
        this.clase_contravencion = clase_contravencion;
        this.descripcion = descripcion;
        this.valor_multa = valor_multa;
        this.puntos_licencia = puntos_licencia;
        this.dias_prision = dias_prision;
    }

    public int getIdinfraccion() {
        return idinfraccion;
    }

    public void setIdinfraccion(int idinfraccion) {
        this.idinfraccion = idinfraccion;
    }

    public String getTipo_infraccion() {
        return tipo_infraccion;
    }

    public void setTipo_infraccion(String tipo_infraccion) {
        this.tipo_infraccion = tipo_infraccion;
    }

    public String getClase_contravencion() {
        return clase_contravencion;
    }

    public void setClase_contravencion(String clase_contravencion) {
        this.clase_contravencion = clase_contravencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValor_multa() {
        return valor_multa;
    }

    public void setValor_multa(double valor_multa) {
        this.valor_multa = valor_multa;
    }

    public String getPuntos_licencia() {
        return puntos_licencia;
    }

    public void setPuntos_licencia(String puntos_licencia) {
        this.puntos_licencia = puntos_licencia;
    }

    public String getDias_prision() {
        return dias_prision;
    }

    public void setDias_prision(String dias_prision) {
        this.dias_prision = dias_prision;
    }

        
}
