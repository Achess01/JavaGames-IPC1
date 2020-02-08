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
public final class Puntuaciones {
    public static Jugador jugadoresSopa[] = new Jugador[100];
    public static Jugador jugadoresTarget[] = new Jugador[100];
    public static Jugador jugadores2048[] = new Jugador[100];
    public static int cantidadJugadores = 0;
    
    Puntuaciones(){
        System.out.println("Sopa de letras");
        Ordenar(jugadoresSopa);
        System.out.println("Target");
        Ordenar(jugadoresTarget);
        System.out.println("2048");
        Ordenar(jugadores2048);
        
    }
    
    public void Ordenar(Jugador[] jugadores){
        Jugador aux;
        for(int x = 0; x < (Puntuaciones.cantidadJugadores - 1); x++){
            for(int y = x+1; y < Puntuaciones.cantidadJugadores; y++){
                if(jugadores[x].punteo < jugadores[y].punteo){
                    aux = jugadores[x];
                    jugadores[x] = jugadores[y];
                    jugadores[y] = aux;
                }
            }
        }
        
        for(int x = 0; x < Puntuaciones.cantidadJugadores; x++){
            System.out.print((x+1) + "- " + jugadores[x].nombre + "\t| " + jugadores[x].punteo);
        }
        System.out.println("\n\n");
    }
}
