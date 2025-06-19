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
    private int filaRecorrido;
    private int columnaRecorrido;

    public Buscadores() {
        this.filaRecorrido = 0;
        this.columnaRecorrido = 0;
    }
    
    public void iniciarBusqueda(){
        filaRecorrido=0;
        columnaRecorrido=0;
    }
    
    public int establecerVerticeInicial(GrafoMatriz grafo, String palabraObjetivo) {
        char[][] tablero = grafo.getTablero();
        int N_FILAS = GrafoMatriz.N_FILAS;
        int N_COLUMNAS = GrafoMatriz.N_COLUMNAS;
        char letraInicial = palabraObjetivo.charAt(0);
        int vertice=-1;
        
        for (int i = filaRecorrido; i < tablero.length; i++) {
            for (int j = columnaRecorrido; j < tablero[i].length; j++) {
                if (tablero[i][j] == letraInicial) {
                    vertice=(i*N_COLUMNAS)+j;
                    filaRecorrido=i;
                    columnaRecorrido=j+1;
                    if (columnaRecorrido>=N_COLUMNAS){
                        filaRecorrido++;
                        columnaRecorrido=0;
                    }
                    return vertice;
                }
            }
        }
        return vertice; // regresa vertice -1, en caso de error, o no se encontro letra
    }
 
    
    public boolean DFS(int indicePalabra, int vertice, GrafoMatriz grafo, String palabraObjetivo, boolean[] visitado){
        
    }
        
    
        
        
        
        
  