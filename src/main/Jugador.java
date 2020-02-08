/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author achess
 */
public class Jugador {
    String nombre;
    int punteo;
    int posicion;
    
    Jugador(String nombre, int posicion){
        this.nombre = nombre;
        this.punteo = 0;
        this.posicion = posicion;
    }
}
