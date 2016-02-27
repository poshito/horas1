/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.horas.entities;

/**
 *
 * @author monitor
 */
public class Usuario {
    private String nombre;
    private int horas;

    public Usuario(String nombre, int horas){
        this.nombre=nombre;
        this.horas=horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

}
