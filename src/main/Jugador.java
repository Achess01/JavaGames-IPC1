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
    int punteoSopa;
    int punteoTarget;
    int punteo2048;
    
    Jugador(String nombre, int punteoSopa, int punteoTarget, int punteo2048){
        this.nombre = nombre;
        this.punteoSopa = punteoSopa;
        this.punteoTarget = punteoTarget;
        this.punteo2048 = punteo2048;
    }
}
