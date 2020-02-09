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
public class Juego {
    Jugador jugadoresActivos[] = new Jugador[4];
    int cantidadActivos = 0;
    Scanner leer = new Scanner(System.in);
        
    Boolean mostrarJugadores(Jugador[] jugadores, int cantidadJugadores){        
        if(cantidadJugadores > 0){
            int op;            
            System.out.println("Los jugadores disponibles son:\nEscoja uno dependiendo el número indicado");
            for(int x = 0; x < cantidadJugadores; x++){
                System.out.println((x+1) + "- " + jugadores[x].nombre);
            }
            System.out.print("> ");
            try{                
                    op = (int)(leer.nextInt());
                    if(op < cantidadJugadores){
                        jugadoresActivos[cantidadActivos] = jugadores[op - 1];
                        cantidadActivos++;
                        return true;
                    }
                    else{
                        return false;
                    }
                    
                }
            catch(Exception x){
                    op = 0;
                    return false;
            }
        }
        else{
            System.out.println("No existen jugadores disponibles para este juego");
            return false;
        }
        
    }
    
    Boolean nuevoJugador(Jugador[] jugadores, int cantidadJugadores, int juego){
        String nombre;
        System.out.println("Ingrese el nombre del jugador");    
        System.out.print("> ");        
        try{
            nombre = leer.nextLine();
            nombre = nombre.toUpperCase().trim();
            if(existeJugador(nombre, cantidadJugadores, jugadores) || nombre.length() == 0){
                System.out.println("Es posible que este jugador ya exista o que usted haya introducido un valor invalido");
                leer.nextLine();
                return false;
            }
            else{            
                Jugador obj = new Jugador(nombre, cantidadJugadores);
                jugadoresActivos[cantidadActivos] = obj;
                cantidadActivos++;        
                switch(juego){
                    case 1:
                        Puntuaciones.jugadoresSopa[Puntuaciones.cantidadSopa] = obj;
                        Puntuaciones.cantidadSopa++;
                        break;
                    case 2:
                        Puntuaciones.jugadoresTarget[Puntuaciones.cantidadTarget] = obj;
                        Puntuaciones.cantidadTarget++;
                        break;
                    case 3: 
                        Puntuaciones.jugadores2048[Puntuaciones.cantidad2048] = obj;
                        Puntuaciones.cantidad2048++;

                }
                return true;
            }            
        }
        catch(Exception ex){
            System.out.println("No se pudo crear al jugador");
            return false;
        }
    }
    
    Boolean existeJugador(String nombre, int cantidadJugadores, Jugador[] jugadores){
        int bandera = 0;
        for(int x = 0; x < cantidadJugadores; x++){
            if(nombre.equals(jugadores[x].nombre)){
                bandera = 1;
                break;
            }
        }
        if(bandera == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    Boolean Menu(int jugadoresPermitidos, int cantidadJugadores, Jugador[] jugadores, int juego){
        char op;
        int iterador = 0;
        Boolean hecho = false;        
            Boolean mostrar = false;
            Boolean nuevo = false;
            System.out.println("1. Elegir jugador existente\n2. Crear nuevo jugador\n3. Salir");
            System.out.print("> ");
            try{
                op = leer.nextLine().charAt(0);
                switch(op){
                case '1': 
                    mostrar = mostrarJugadores(jugadores, cantidadJugadores); 
                    hecho = true;
                    break;
                case '2': 
                    nuevo = nuevoJugador(jugadores, cantidadJugadores, juego);                    
                    hecho = true;
                    break;
                }                
                iterador++;
            }
            catch(Exception ex){
                System.out.println("Valor invalido");                
            } 
            if(hecho){
                if(mostrar || nuevo){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }                            
    }
    
    int aleatorio(int max, int min){
        int z = (int)(Math.floor(Math.random() * (max - min +1) + min));
        return z;
    }
}
