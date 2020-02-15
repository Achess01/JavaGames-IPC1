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
public class Sopa extends Juego{
    Boolean jugadorAprobado;   
    String palabras[] = {"SECRETO", "PROGRAMACION", "UNIVERSIDAD", "RECONOCER", "TECLADO"};
    
    public void Ejecutar(){
        jugadorAprobado = Menu(Puntuaciones.cantidadSopa, Puntuaciones.jugadoresSopa, 1);
        if(jugadorAprobado){            
            Juego();
        }        
    }    
    
    void Juego(){     
        String palabra;      
        String palabraIngresada;
        int posiciones[];
        int intentos = 3;        
        int index;
        palabra = escogerPalabra();
        posiciones = escogerOrden(palabra);
        do{
            marcarTurno();
            System.out.println("Juego: SOPA DE LETRAS");     
            System.out.println("Datos jugador:" + jugadoresActivos[0].nombre + " "  + jugadoresActivos[0].punteo +"pts.");
            System.out.println("Intentos restantes: " + intentos  + "\n\n");
            escribirPalabra(palabra, posiciones);
            System.out.print("\nAdivine la palabra >");
            palabraIngresada = leer.nextLine();
            palabraIngresada = palabraIngresada.toUpperCase().trim();
            if(palabraIngresada.equals(palabra)){                              
                index = jugadoresActivos[0].posicion;
                Puntuaciones.jugadoresSopa[index].punteo++;                
                System.out.println("!FELICIDADES¡");                
                intentos = 0; 
                leer.nextLine();
            }
            else{
                intentos--;
                if(intentos == 0){
                    System.out.println("Has perdido\nPuntos ganados: 0");
                    System.out.println("La palabra era: " + palabra);
                    leer.nextLine();
                }                
            }
            marcarTurno();
        }while(intentos > 0);
        
    }
    
    String escogerPalabra(){        
        int numero = aleatorio(palabras.length - 1 , 0);
        return palabras[numero];
    }
    
    int[] escogerOrden(String palabra){        
        Boolean repetido;
        int posiciones[] = new int[20];
        int indexLetra;
        for(int x = 0; x < palabra.length(); x++){
            repetido = false;
            indexLetra = aleatorio(palabra.length() - 1, 0);
            for(int y = 0; y < x; y++){
                if(indexLetra == posiciones[y]){
                    repetido = true;
                    break;
                }
            }
            if(repetido){
                x--;
                continue;
            }
            posiciones[x] = indexLetra;
        }               
      
        return posiciones;
    }
    
    void escribirPalabra(String palabra, int posiciones[]){
        for(int x = 0; x < palabra.length(); x++){
            System.out.print(palabra.charAt(posiciones[x]) + "\t");
        }
    }
            
}
