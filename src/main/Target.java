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
    int turnos = -1;
    
    Target(){
        escogerJugadores();
        Turnos();
    }    
    
    void Turnos(){
        do{
            if(turnos == (cantidadActivos - 1)){
                turnos = -1;
            }
            turnos++;
            System.out.println(turnos + " " + cantidadActivos);
            ganador = Juego(jugadoresActivos[turnos].nombre, turnos);                        
        }while(!ganador);
        System.out.println("Felicidades " + jugadoresActivos[turnos].nombre);
        int index = jugadoresActivos[turnos].posicion;
        Puntuaciones.jugadoresTarget[index].punteo++;
    }
    
    Boolean Juego(String nombre, int turno){
        char op;
        Boolean hecho;
        int puntosTurno;
        do{
            System.out.println("Turno jugador: "  + nombre);
            for(int x = 0; x < cantidadActivos; x++){
                System.out.println(jugadoresActivos[x].nombre +" : " + punteoActivos[x]);
            }            
            System.out.println("Tipos de tiro:");
            System.out.println("1. Rapido con el brazo arriba del brazo");
            System.out.println("2. Controlado con el dardo arriba del brazo");
            System.out.print("3. Con el dardo bajo el brazo\n> ");
            op = leer.nextLine().charAt(0);
            switch(op){
                case '1':        
                    puntosTurno = tiroRapido();
                    punteoActivos[turno] += puntosTurno;
                    System.out.println("+" + puntosTurno);                    
                    hecho = true;
                    break;
                case '2': 
                    puntosTurno = tiroControlado();
                    punteoActivos[turno] += puntosTurno;
                    System.out.println(jugadoresActivos[turno].nombre + ": +" + puntosTurno);                    
                    hecho = true;
                    break;                    
                case '3': 
                    puntosTurno = tiroBajo();
                    punteoActivos[turno] += puntosTurno;
                    System.out.println("+" + puntosTurno);                    
                    hecho = true;
                    hecho = true;
                    break;
                default:
                    System.out.println("Valor no valido");
                    hecho = false;
            }            
        }while(!hecho);
        
        if(punteoActivos[turno] >= 200){
            return true;
        }
        else{
            return false;
        }
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
    
    int tiroControlado(){
        int resultado = aleatorio(2, 0);
        if(resultado == 0){
            return 10;
        }
        else if(resultado == 1){
            return 20;
        }
        else{
            return 30;
        }
    }
    
    int tiroBajo(){
        int resultado = aleatorio(4, 0);
        if(resultado == 0){
            return 0;
        }
        else if(resultado == 1){
            return 10;
        }
        else if(resultado == 2){
            return 20;
        }
        else if(resultado == 3){
            return 30;
        }
        else{
            return 40;
        }
        
    }
    
    void escogerJugadores(){
        int jugadores;
        System.out.print("Ingrese la cantidad de jugadores (2,4)\n> ");
        try{
            jugadores = leer.nextInt();            
            leer.nextLine();
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
