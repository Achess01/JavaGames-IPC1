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
public class Colores {
    public static final String ANSI_RESET = "\u001B[0m";
        //Colores de letra        
        public static final String ANSI_WHITE = "\u001B[37m";
        //Colores de fondo        
        public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
        public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";        
        public static String coloresDeFondo[]={                
            ANSI_CYAN_BACKGROUND,           
            ANSI_YELLOW_BACKGROUND,
            ANSI_GREEN_BACKGROUND,
            ANSI_RED_BACKGROUND,
            ANSI_BLUE_BACKGROUND,                                             
            ANSI_PURPLE_BACKGROUND,
                                            };
}
