/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import GUI.Interfaz1;
/**
 * Clase principal que inicia la aplicación de Sopa de Letras.
 * Contiene el método main que muestra la interfaz gráfica principal.
 * Proyecto desarrollado para el curso de Estructuras de Datos (EDD).
 * 
 * @author Luis Peña
 * @author Luis Lovera
 * @author Diego Linares
 */
public class SopaDeLetras {
    
    /**
     * Punto de entrada principal para la aplicación.
     * Crea y muestra la interfaz gráfica, configurando su título, tamaño y posición centrada en pantalla.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Interfaz1 interfaz1 = new Interfaz1();
        interfaz1.setTitle("Proyecto 1 de EDD");
        interfaz1.setSize(1040, 900);
        interfaz1.setLocationRelativeTo(null);
        interfaz1.setVisible(true);
        
    }
}
