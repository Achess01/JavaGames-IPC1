/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.util.Scanner;
/**
 *
 * @author achess
 */
public class Target extends Juego{       
    Boolean jugadoresAprobados[] = new Boolean[4];        
    int punteoActivos[] = {0,0,0,0};
    Boolean ganador = false;
    int turnos = 0;
    
    Target(){
        escogerJugadores();
    }    
    
    
    void Juego(String nombre, turno){
        char op;
        Boolean hecho;
        do{
            System.out.println("Turno jugador: "  + nombre);
            for(int x = 0; x < cantidadActivos; x++){
                System.out.println(jugadoresActivos[x].nombre +" : " + punteoActivos[x]);
            }
            System.out.println("\n");
            System.out.println("Tipos de tiro:");
            System.out.println("1. Rapido con el brazo arriba del brazo");
            System.out.println("2. Controlado con el dardo arriba del brazo");
            System.out.println("3. Con el dardo bajo el brazo\n> ");
            op = leer.nextLine().charAt(0);
            switch(op){
                case '1':        
                    punteoActivos[]
                    hecho = true;
                    break;
                case '2': 
                    hecho = true;
                    break;                    
                case '3': 
                    hecho = true;
                    break;
                default:
                    System.out.println("Valor no valido");
                    hecho = false;
            }
        }while(!hecho);
    }
    
    int tiroRapido(){
        int resultado = aleatorio(1, 0);
        if(resultado == 1){            
            return 40;
        }
        else{            
            return 0;
        }
    }
    
    void escogerJugadores(){
        int jugadores;
        System.out.println("Ingrese la cantidad de jugadores (2,4)");
        try{
            jugadores = leer.nextInt();            
            if(jugadores < 2 || jugadores > 4){
                jugadores = 2;
            }
        }
        catch (Exception ex){
            jugadores = 2;
        }
        
        for(int x = 0; x < jugadores; x++){
            System.out.println("Escogiendo jugador No. " + (x+1));
            jugadoresAprobados[x] = Menu(Puntuaciones.cantidadTarget, Puntuaciones.jugadoresTarget, 2);
            for(int y = 0; y < x; y++){
               if(jugadoresActivos[x] == jugadoresActivos[y]){
                   jugadoresAprobados[x] = false;
               }
            }
            if(!jugadoresAprobados[x]){               
                System.out.println("Error al introducir jugador No. " + (x+1));
                System.out.println("Vuelva a intentarlo\n");
                x--;
            }
        }        
    }
}
