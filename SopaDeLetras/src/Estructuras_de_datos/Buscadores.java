/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author luismarianolovera
 */
public class Buscadores {

    public Buscadores() {
    }
    
    public int[] establecerVerticeInicial(GrafoMatriz grafo, String palabraObjetivo) {
        char[][] tablero = grafo.getCeldaTablero();
        int N_FILAS = GrafoMatriz.N_FILAS;
        int N_COLUMNAS = GrafoMatriz.N_COLUMNAS;
        char letraInicial = palabraObjetivo.charAt(0);
        int filaRecorrido=0;
        int columnaRecorrido=0;
       
        while (filaRecorrido<N_FILAS){
            boolean encontrado=false;
            int vertice=-1;
            
            for (int i = filaRecorrido; i < N_FILAS && encontrado==false; i++) {
     
                int jInicio;
                if (i==filaRecorrido){
                    jInicio=columnaRecorrido;
                }else{
                    jInicio=0;
                }
                
                for (int j = jInicio; j < N_COLUMNAS; j++) {
                    if (tablero[i][j] == letraInicial) {
                        vertice=(i*N_COLUMNAS)+j;
                        filaRecorrido=i;
                        columnaRecorrido=j+1;
                        if (columnaRecorrido>=N_COLUMNAS){
                            filaRecorrido++;
                            columnaRecorrido=0;
                        }
                        encontrado=true;
                        break;
                    }
                }
  
            }
        }
        return null;
    }
}

      
