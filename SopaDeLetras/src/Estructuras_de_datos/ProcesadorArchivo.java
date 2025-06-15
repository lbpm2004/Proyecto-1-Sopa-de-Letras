/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author luismarianolovera
 */
public class ProcesadorArchivo {
    private ListaSimple diccionario;
    private char[][] tableroLetras;
    

    public ProcesadorArchivo() {
        this.diccionario = new ListaSimple();
        this.tableroLetras = new char[4][4];
    }
   
    
    public String leerArchivo(File archivoSeleccionado) {
        String contenido = "";
        try {
            if (!archivoSeleccionado.exists()) {
                JOptionPane.showMessageDialog(null, "El archivo no existe.","Error",JOptionPane.ERROR_MESSAGE);
                return null;
            }
            FileReader fr = new FileReader(archivoSeleccionado);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {  //Ignorar líneas vacías
                    contenido += line + "\n";
                }
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return contenido;
    }
    
    public boolean validarEstructura(String contenido){
        String[] lineas = contenido.split("\n");
    
        //1. Validar existencia de "dic"
        
        if (!lineas[0].equals("dic")){
            JOptionPane.showMessageDialog(null, "El archivo no cumple con la estructura adecuada. Este debe iniciar con 'dic'. ", "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //2. Validar existencia de "/dic"
       
        int indiceDicFin=-1;
        
        for (int i = 1; i < lineas.length; i++) {
            if (lineas[i].equals("/dic")){
                indiceDicFin=i;
                break;   
            }
        }
        
        if (indiceDicFin==-1){
            JOptionPane.showMessageDialog(null, "El archivo no cumple con la estructura adecuada. No se encontró '/dic'. ", "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        
        //3. Validar existencia de "tab"
        
        if (indiceDicFin+1 >=lineas.length || !lineas[indiceDicFin+1].equals("tab")){
            JOptionPane.showMessageDialog(null, "El archivo no cumple con la estructura adecuada.", "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //4. Validar existencia de "/tab"
        if (indiceDicFin+3 >=lineas.length || !lineas[indiceDicFin+3].equals("/tab")){
            JOptionPane.showMessageDialog(null, "El archivo no cumple con la estructura adecuada.", "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //5. Validar que no haya más líneas de texto despues de "/tab"
        for (int i = indiceDicFin + 4; i < lineas.length; i++) {
            if (!lineas[i].trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El archivo contiene texto después de '/tab'. Esto no está permitido.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        
        return true;
    }
    
    
 
    
}
