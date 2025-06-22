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
    private ListaSimple diccionario; //Almacena las palabras del archivo TXT.
    private char[][] tableroLetras; //Matriz de caracteres con las letras del archivo TXT. 
    private int indiceDicFin; //Indica el índice donde termina el diccionario para una variable de tipo []String que almacena toda la información del archivo TXT.
    private char[] letrasPermitidas;
    private String[] letrasArchivo;
    
    public ProcesadorArchivo() {
        this.diccionario = new ListaSimple(); //Lista simplemente enlazada vacía.
        this.tableroLetras = new char[4][4]; //Matriz 4x4 vacía.
        this.indiceDicFin = -1; //Valor predeterminado.
        this.letrasPermitidas= new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','ñ','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','Ñ','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        this.letrasArchivo=null;
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
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {  //Ignorar líneas vacías
                    contenido += linea.trim() + "\n";
                }
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo. Intenta de nuevo","Error",JOptionPane.ERROR_MESSAGE);
        }
        return contenido;
    }
    
    public String[] adaptadorArchivo (String contenido){
        return contenido.split("\n");
    }
    
    public boolean validarEstructura(String[] lineas){
  
        //1. Validar existencia de "dic"
        
        if (!lineas[0].equals("dic")){
            JOptionPane.showMessageDialog(null, "El archivo no cumple con la estructura adecuada. Este debe iniciar con 'dic'. ", "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
 
        //2. Validar existencia de "/dic"
               
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
    
    public boolean validarPalabras(String palabra, boolean esArchivo){
        if (palabra.length()>=3){
            for (int j = 0; j < palabra.length(); j++) {
                boolean letraValida=false;
                char letraPalabra=palabra.charAt(j);
                for (int k = 0; k < letrasPermitidas.length; k++) {
                    if (letraPalabra==letrasPermitidas[k]){
                        letraValida=true;
                        break;   
                    }
                }
                if (letraValida==false){
                    if (esArchivo==true){
                        JOptionPane.showMessageDialog(null, "Las palabras del archivo contienen caracteres inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(null, "Las palabra introducida contiene caracteres no permitidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return false;
                }
            }
        }else{
            if (esArchivo==true){
                JOptionPane.showMessageDialog(null, "Las palabras del archivo deben tener al menos 3 letras.", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Las palabras introducida debe tener al menos 3 letras.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
        return true;
    }
    
    public boolean validarDiccionario(String[] lineas){
        for (int i = 1; i < indiceDicFin; i++) {
            if (validarPalabras(lineas[i], true)==false) {
                return false; 
           }
        }
        return true;
    }
    
    public boolean validarLetrasTablero(String[] lineas){
        letrasArchivo=lineas[indiceDicFin+2].split(",");
        
        if (letrasArchivo.length!=16){
            JOptionPane.showMessageDialog(null, "El archivo debe contener exactamente 16 letras para la formación del tablero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
   
        for (int i = 0; i < letrasArchivo.length; i++) {
            String letra=letrasArchivo[i].trim();
            if (letra.length()!=1){
            JOptionPane.showMessageDialog(null, "El archivo contiene más de 16 letras para el tablero.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
            }
          
            boolean letraValida=false;
            for (int j = 0; j < letrasPermitidas.length; j++) {
                if (letra.charAt(0)==letrasPermitidas[j]){
                    letraValida=true;
                    break;
                }   
            }
            
            if(letraValida==false){
                JOptionPane.showMessageDialog(null, "El archivo contiene letras inválidas.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
            }
        }
        return true;
    }
    
    public void generarDiccionario (String [] lineas){
        for (int i = 1; i < indiceDicFin; i++) {
            diccionario.insertarAlFinal(lineas[i].toUpperCase());
        }
    }
    
    public void generarTablero (String[] lineas){
        int identificador=0;
        for (int i = 0; i < tableroLetras.length; i++) {
            for (int j = 0; j < tableroLetras.length; j++) {
                tableroLetras[i][j]=letrasArchivo[identificador].toUpperCase().charAt(0);
                identificador++;
            }
            
        }
    }
    
    public boolean procesarArchivo(File archivoSeleccionado) {
        String contenido = leerArchivo(archivoSeleccionado);
        if (contenido == null){
            return false;
        }
        
        String[] lineas = adaptadorArchivo(contenido); //Array que almacena toda la info del TXT.
        
        if (validarEstructura(lineas)==true){
            if (validarDiccionario(lineas)==true){
                if(validarLetrasTablero(lineas)==true){
                    generarDiccionario(lineas);
                    generarTablero(lineas);
                }else{
                    return false;
                }
            }else{
                return false;
                    }
        }else{
            return false;
        }
        return true;
    }
    
    public void guardarDiccionario(File archivoSeleccionado){
        try{
            if (diccionario ==null || diccionario.esVacia()) {
                JOptionPane.showMessageDialog(null, "No hay palabras en el diccionario para guardar.","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            PrintWriter pw = new PrintWriter(archivoSeleccionado);
            pw.println("dic");
            NodoSimple actual = diccionario.getFirst();
            while (actual != null) {
                pw.println(actual.getDato());
                actual = actual.getNext();
            }
            pw.println("/dic");
            pw.println("tab");
            pw.println(String.join(",", letrasArchivo)); // Usar el string original
            pw.println("/tab");
            pw.close();
            JOptionPane.showMessageDialog(null, "Diccionario guardado exitosamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void reiniciar(){
        diccionario.vaciar();
        indiceDicFin = -1;
    }
    
    public ListaSimple getDiccionario() {
        return diccionario;
    }

    public char[][] getTableroLetras() {
        return tableroLetras;
    }
    
}
