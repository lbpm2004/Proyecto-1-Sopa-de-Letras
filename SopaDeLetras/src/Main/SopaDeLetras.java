/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import GUI.Interfaz1;
/**
 * Se encarga de iniciar el programa y mostrar la interfaz de usuario.
 * @authors Luis Peña, Luis Lovera y Diego Linares.
 */
public class SopaDeLetras {
    
    /**
     * Método principal que inicia la aplicación.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Interfaz1 interfaz1 = new Interfaz1();
        interfaz1.setTitle("Proyecto 1 de EDD");
        interfaz1.setSize(1040, 900);
        interfaz1.setLocationRelativeTo(null); //método para que la interfaz apareczcs en el centro de la pantalla
        interfaz1.setVisible(true);
        
    }
}
