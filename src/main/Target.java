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
public class Target extends Juego{       
    Boolean jugadoresAprobados[] = new Boolean[4];        
    int punteoActivos[] = {0,0,0,0};
    Boolean ganador = false;
    int turnos = -1;
    
    public void Ejecutar(){
        escogerJugadores();
        Turnos();
    }
    
    void Turnos(){
        do{
            if(turnos == (cantidadActivos - 1)){
                turnos = -1;
            }
            turnos++;            
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
            marcarTurno();
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
                    break;
                default:
                    System.out.println("Valor no valido");
                    hecho = false;
            }            
            marcarTurno();
        }while(!hecho);
        
        if(punteoActivos[turno] < 200){
            return false;
        }
        else{
            return true;
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
        switch (resultado) {
            case 0:
                return 10;
            case 1:
                return 20;
            default:
                return 30;
        }
    }
    
    int tiroBajo(){
        int resultado = aleatorio(4, 0);
        switch (resultado) {
            case 0:
                return 0;
            case 1:
                return 10;
            case 2:
                return 20;
            case 3:
                return 30;
            default:
                return 40;
        }
        
    }
    
    void escogerJugadores(){
        int jugadores;
        System.out.print("Ingrese la cantidad de jugadores (1,4)\n> ");
        try{
            jugadores = leer.nextInt();            
            leer.nextLine();
            if(jugadores < 1 || jugadores > 4){
                jugadores = 1;
            }
        }
        catch (Exception ex){
            jugadores = 1;
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
