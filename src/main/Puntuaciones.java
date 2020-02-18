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
/*Esta clase se encarga de  guardar los resultados y mostrarlos. 
  Tiene arreglos de tipo static para que los resultados puedan ser modificados cuando   
  los cambios ocurran en los juegos.

*/
public final class Puntuaciones {
    public static Jugador jugadoresSopa[] = new Jugador[100];    
    public static Jugador jugadoresTarget[] = new Jugador[100];
    public static Jugador jugadores2048[] = new Jugador[100];
    public static int cantidadSopa = 0;
    public static int cantidadTarget = 0;
    public static int cantidad2048 = 0;
    
    public static void mostrar(){
        System.out.println("Sopa de letras\tPts.");
        Escribir(jugadoresSopa, cantidadSopa);
        System.out.println("Target\tPts.");
        Escribir(jugadoresTarget, cantidadTarget);
        System.out.println("2048\tPts.");
        Escribir(jugadores2048, cantidad2048);
        
    }
    
    public static Jugador[] Ordenar(Jugador[] jugadores, int cantidadJugadores){
        Jugador aux;
        for(int x = 0; x < (cantidadJugadores - 1); x++){
            for(int y = x+1; y < cantidadJugadores; y++){
                if(jugadores[x].punteo < jugadores[y].punteo){
                    aux = jugadores[x];
                    jugadores[x] = jugadores[y];
                    jugadores[y] = aux;
                }
            }
        }        
        return jugadores;
    }
    
    public static void Escribir(Jugador[] jugadores, int cantidadJugadores){
        jugadores = Ordenar(jugadores, cantidadJugadores);
        for(int x = 0; x < cantidadJugadores; x++){
            System.out.println((x+1) + "- " + jugadores[x].nombre + "\t " + jugadores[x].punteo);
        }
        System.out.println("\n\n");
    }
}
