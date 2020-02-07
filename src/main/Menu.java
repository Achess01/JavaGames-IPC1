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
public class Menu {
    Menu(String[] juego){
        char op = 'x';
        int longitud = juego.length;     
        do{                          
            if(longitud > 0){
                switch(juego[0].toUpperCase()){
                    case "SOPA":
                        op = '1';
                        break;
                    case "TARGET":
                        op = '2';
                        break;
                    case "2048":
                        op = '3';
                        break; 
                    default:            
                        op = 'x';
                }
                longitud = 0;
            }
            else{                
                System.out.print("Bienvenido\n1.Sopa de letras\n2.Target\n3.2048\n4.Salir");     
                Scanner leer = new Scanner(System.in);
                System.out.print("\n> ");
                op = leer.nextLine().charAt(0);
            }
            switch(op){
                case '1':
                    System.out.println("Sopa");
                    break;
                case '2':
                    System.out.println("target");
                    break;
                case '3':
                    System.out.println("2048");
                    break;
                    
            }                                                
        }
        while(op != '4');        
        
    }
    
}
