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
public class Juego2048 extends Juego{
    Boolean jugadorAprobado; 
    Boolean finDeJuego = false;
    int campoDeJuego[][] = {{0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0},
                            {0,0,0,0}};
    
    int campoTranspuesto[][] = new int[4][4];
    int f = campoDeJuego.length;
    
    public void Ejecutar(){
        jugadorAprobado = Menu(Puntuaciones.cantidad2048, Puntuaciones.jugadores2048, 3);
        if(jugadorAprobado){            
            Juego();
        }                
        else{
            System.out.println("Error al ingresar el jugador");
        }
    }
    
    void Juego(){
        String valor;
        System.out.println("Para mover: arriba (w), abajo (s), izquierda (a), derecha (d)");
        System.out.println("Para salir ingrese el comando salir");
        inicio();        
        campoTranspuesto = transponerMatrices(campoTranspuesto, campoDeJuego);
        do{
            dibujar();
            System.out.print("> ");
            valor = leer.nextLine().toUpperCase().trim();            
            switch(valor){
                case "A":
                    System.out.println("arriba");
                    campoDeJuego = mover1(campoDeJuego);
                    campoTranspuesto = transponerMatrices(campoTranspuesto, campoDeJuego);
                    break;
                case "D":
                    System.out.println("abajo");
                    campoDeJuego = mover2(campoDeJuego);
                    campoTranspuesto = transponerMatrices(campoTranspuesto, campoDeJuego);
                    break;
                case "W":
                    System.out.println("izquierda");
                    campoTranspuesto = mover1(campoTranspuesto);
                    campoDeJuego = transponerMatrices(campoDeJuego, campoTranspuesto);
                    break;
                case "S":
                    System.out.println("derecha");
                    campoTranspuesto = mover2(campoTranspuesto);
                    campoDeJuego = transponerMatrices(campoDeJuego, campoTranspuesto);
                    break;
                default: 
                    System.out.println("valor no válido");
            }
        }while(!finDeJuego);
    }
    
    int[][] transponerMatrices(int matriz1[][], int matriz2[][]){
        for(int x = 0; x < f; x++){
            for(int y = 0; y < f; y++){
                matriz1[x][y] = matriz2[y][x];
            }
        }
        return matriz1;
    }
            
    int[][] mover1(int matriz[][]){
        int fila[];
        for(int x = 0; x < f; x++){
            fila = matriz[x];
            for(int y = 0; y < f-1; y++){                
                for(int z = y+1; z < f; z++){
                    if(fila[y] == 0 && fila[z] != 0){
                        fila[y] = fila[z];
                        fila[z] = 0;                        
                    }
                }
            }
            matriz[x] = fila;
        }
        return matriz;
    }
    
    int[][] mover2(int matriz[][]){
        int fila[];
        for(int x = 0; x < f; x++){
            fila = matriz[x];
            for(int y = 0; y < f-1; y++){                
                for(int z = y+1; z < f; z++){
                    if(fila[y] != 0 && fila[z] == 0){
                        fila[z] = fila[y];
                        fila[y] = 0;                        
                    }
                }
            }
            matriz[x] = fila;
        }
        return matriz;
    }   
           
    void inicio(){
        int x, y;
        int iterador = 0;
        do{
                x = aleatorio(3, 0);
                y = aleatorio(3, 0);                
                if(campoDeJuego[x][y] == 0){
                    campoDeJuego[x][y] = 2;                
                    iterador++;
                }
        }while(iterador < 6);
    }
    
    void dibujar(){
        int espacios;
        int casilla;        
        String colores;
        for(int x = 0; x < f; x++){            
            for(int y = 0; y < f; y++){
                casilla = campoDeJuego[x][y];
                espacios = definirEspacios(casilla);
                colores = definirColores(casilla);
                System.out.print("|"+colores);
                if(casilla == 0){                    
                    dibujarEspacios(espacios);
                }
                else{
                    System.out.print(casilla);
                    dibujarEspacios(espacios);
                }
            }
            System.out.println();
            for(int z = 0; z < 28; z++){
            System.out.print("-");
            }
            System.out.println();
        }
                
    }
    
    int definirEspacios(int casilla){
        int espacios;
        if(casilla == 0){
            espacios = 5;
        }
        else if(casilla < 10){
            espacios = 4;
        }
        else if(casilla < 100){
            espacios = 3;
        }
        else if(casilla < 1000){
            espacios = 2;
        }
        else{
            espacios = 1;
        }
        return espacios;
    }
    
    void dibujarEspacios(int espacios){
        for(int x = 0; x < espacios; x++){
            System.out.print(" ");
        }
        System.out.print(Colores.ANSI_RESET + "|");
    }
    
    String definirColores(int casilla){
        int index;
        int contador = 0;
        do{
            casilla = casilla/2;
            if(casilla != 0){
                contador++;                
            }
        }while(casilla > 1);
        index = contador % 6;
        return Colores.coloresDeFondo[index]+Colores.ANSI_WHITE;        
    }
}
