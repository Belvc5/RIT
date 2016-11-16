/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author Belen
 */
public class Infractor extends Persona{
    private String placa_vehiculo;

    public Infractor() {
    }

    public Infractor(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }
    
    
    
}
