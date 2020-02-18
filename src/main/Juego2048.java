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
/*
    Clase juego 2048:
    El campo de juego consiste en dos matrices. Una matriz que sirve para mostrar el campo y además 
    para los movimientos de izquierda a derecha. La otra matriz es una matrzi transpuesta de la matriz
    principal que se utiliza para los movimientos de arriba y abajo.
    Se usan dos matrices para poder reciclar los algoritmos del movimiento de izquierda y deracha.
*/
public class Juego2048 extends Juego{
    Boolean jugadorAprobado; 
    int acumulado = 0;
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
        Jugador highScore[] = Puntuaciones.Ordenar(Puntuaciones.jugadores2048, Puntuaciones.cantidad2048);
        Boolean hecho = false;
        System.out.println("Para mover: arriba (w), abajo (s), izquierda (a), derecha (d)");
        System.out.println("Para salir ingrese el comando salir");
        inicio();        
        campoTranspuesto = transponerMatrices(campoTranspuesto, campoDeJuego);
        do{            
            marcarTurno();
            System.out.println("Jugador: " + jugadoresActivos[0].nombre);
            System.out.println("SCORE: " + acumulado);            
            System.out.println("HIGH SCORE: " + highScore[0].punteo);
            dibujar();
            System.out.print("> ");
            valor = leer.nextLine().toUpperCase().trim();            
            switch(valor){
                case "A":
                    System.out.println("izquierda");
                    campoDeJuego = mover1(campoDeJuego);
                    campoTranspuesto = transponerMatrices(campoTranspuesto, campoDeJuego);                    
                    break;
                case "D":
                    System.out.println("derecha");
                    campoDeJuego = mover2(campoDeJuego);
                    campoTranspuesto = transponerMatrices(campoTranspuesto, campoDeJuego);                    
                    break;
                case "W":
                    System.out.println("arriba");
                    campoTranspuesto = mover1(campoTranspuesto);
                    campoDeJuego = transponerMatrices(campoDeJuego, campoTranspuesto);                    
                    break;
                case "S":                    
                    System.out.println("abajo");
                    campoTranspuesto = mover2(campoTranspuesto);
                    campoDeJuego = transponerMatrices(campoDeJuego, campoTranspuesto);                    
                    break;
                case "SALIR":
                    finDeJuego = true;                    
                    break;
                default: 
                    System.out.println("valor no válido");                    
            }
            ganadorPerdedor();
            marcarTurno();
        }while(!finDeJuego);
    }
     
    void ganadorPerdedor(){
        int puntosActuales = jugadoresActivos[0].punteo;
        int posicion = jugadoresActivos[0].posicion;
            if(acumulado > puntosActuales){
                Puntuaciones.jugadores2048[posicion].punteo = acumulado;
            }
    }
    int[][] crearNuevoNumero(int matriz[][]){
        int numero = aleatorio(2,1);
        int x, y;
        Boolean validar = false;
        numero *= 2;
        int contar = 0;
        Boolean ganador = false;
        for(int j = 0; j < f; j++){
            for(int i = 0; i < f; i++){
                if(matriz[j][i] == 0){
                    contar++;
                }
                if(matriz[j][i] == 2048){
                    ganador = true;
                }                
            }
        }
        
        if(ganador){
            System.out.println("!FELICIDADES¡");
            ganadorPerdedor();
            finDeJuego = true;
            leer.nextLine();
        }
        else if(contar == 0){            
            System.out.println("Has perdido\nInténtalo de nuevo");            
            ganadorPerdedor();
            finDeJuego = true;
            leer.nextLine();
        }
        else{
            do{
                x = aleatorio(3,0);
                y = aleatorio(3,0);                
                if(matriz[x][y] == 0){
                    matriz[x][y] = numero;
                    validar = true;
                }
            }while(!validar);
        }
        return matriz;
    }
    
    int[][] transponerMatrices(int matriz1[][], int matriz2[][]){
        for(int x = 0; x < f; x++){
            for(int y = 0; y < f; y++){
                matriz1[x][y] = matriz2[y][x];
            }
        }        
        return matriz1;
    }
    /*Para los movimientos:
    Lo primero que hace es juntar todas las casillas al lado indicado por el usuario. Esto para
    facilitar la sumatoria de las casillas. Luego de hacer la sumatoria se juntan de nuevo las casillas.
    
    
    */
    int[][] mover1(int matriz[][]){
        int fila[];
        int sumas;
        for(int x = 0; x < f; x++){
            fila = matriz[x];                        
            //Juntando
            fila = juntar1(fila);
            //Sumando
            for(int j = 0; j < f - 1; j++){
                if(fila[j] == fila[j+1]){
                    sumas = fila[j] + fila[j+1];
                    fila[j] = sumas;                    
                    acumulado += sumas;
                    fila[j+1] = 0;
                    j++;
                }
            }
            //Juntando            
            fila = juntar1(fila);
            matriz[x] = fila;
        }
        matriz = crearNuevoNumero(matriz);
        return matriz;
    }
    
    int[][] mover2(int matriz[][]){        
        int fila[];
        int sumas;
        for(int x = 0; x < f; x++){
            fila = matriz[x];            
            //Juntando
            fila = juntar2(fila);
            //Sumando
            for(int j = f - 1; j > 0; j--){
                if(fila[j] == fila[j-1]){
                    sumas = fila[j] + fila[j-1];
                    fila[j] = sumas;
                    acumulado += sumas;
                    fila[j-1] = 0;
                    j--;
                }
            }
            //Juntando
            fila = juntar2(fila);
            matriz[x] = fila;
        }
        matriz = crearNuevoNumero(matriz);
        return matriz;
    }   
    
    int[] juntar1(int fila[]){
        /*
        Se tomó el metodo de ordenamiento burbuja y se modificó para hacer que corra los espacios
        y no para ordenar. 
        */
        for(int y = 0; y < f-1; y++){                
                for(int z = y+1; z < f; z++){
                    if(fila[y] == 0 && fila[z] != 0){
                        fila[y] = fila[z];
                        fila[z] = 0;      
                        break;
                    }
                }
            }
        return fila;
    }
    
    int[] juntar2(int fila[]){
        /*
        Se tomó el metodo de ordenamiento burbuja y se modificó para hacer que corra los espacios
        y no para ordenar. 
        */
         for(int y = f-1; y > 0; y--){                
                for(int z = y-1; z >= 0; z--){
                    if(fila[z] != 0 && fila[y] == 0){
                        fila[y] = fila[z];
                        fila[z] = 0;    
                        break;
                    }
                }
            }
        return fila;
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
        }while(iterador < 2);
    }
    
    void dibujar(){
        /*
        Este método se encarga de dibujar la matriz "campoDeJuego".
        Lo primero que hace es establecer los espacios dependiendo del tamaño del número que se encuentra 
        en la casilla que se está evaluando. Se hace así para darle simetría al campo de juego.
        Luego escoge los colores previemente declarados en la clase "Colores". 
        */
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
        /*
            Utilizando el operador % (mod) y descomponiendo el número de la casilla que se está evaluando
            se logra determinar el color de cada número. Los colores están guardados en un arreglo en la 
            clase "Colores".
        */
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
