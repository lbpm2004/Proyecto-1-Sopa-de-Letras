/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * Representa un grafo mediante matriz de adyacencia para el tablero de sopa de letras.
 * Cada vértice corresponde a una celda del tablero 4x4, con aristas que conectan celdas adyacentes
 * en todas las direcciones (horizontal, vertical y diagonal).
 * 
 * @author Luis Mariano Lovera
 * @author Diego Linares
 */
public class GrafoMatriz {
    private char[][] tablero; //matriz 4 filas x 4 columnas
    private boolean[][] matrizAdyacencia; // matriz 16x16, demuestra si es adyacente o no
    public static final int N_VERTICES = 16; //Numero de vertices del grafo
    public static final int N_FILAS=4;
    public static final int N_COLUMNAS=4;
    
    /**
     * Constructor que inicializa el grafo con un tablero dado.
     * 
     * @param tablero Matriz 4x4 de caracteres que representa el tablero de juego
     */
    public GrafoMatriz(char[][] tablero) {
        this.tablero = tablero;
        this.matrizAdyacencia = new boolean[N_VERTICES][N_VERTICES];
    }
    
    /**
     * Establece una conexión bidireccional entre dos vértices.
     * 
     * @param origen Vértice de origen (0-15)
     * @param destino Vértice de destino (0-15)
     */
    public void agregarArista(int origen, int destino){
        matrizAdyacencia[origen][destino]=true;
        matrizAdyacencia[destino][origen]=true;
    }
    
    /**
     * Construye el grafo conectando cada celda con sus 8 vecinos posibles:
     * - Arriba, abajo, izquierda, derecha
     * - 4 direcciones diagonales
     * Las conexiones son bidireccionales.
     */
    public void construirGrafo(){
        for (int fila = 0; fila < N_FILAS; fila++) {
            for (int columna = 0; columna < N_COLUMNAS; columna++) {
                int origen = fila * N_COLUMNAS + columna;
                
                //Arriba               
                if (fila > 0) {
                    int destino = (fila-1)*N_COLUMNAS+columna;
                    agregarArista (origen, destino);
                }
                
                //Abajo
                if (fila < N_FILAS-1){
                    int destino=(fila+1)*N_COLUMNAS+columna;
                    agregarArista (origen, destino);
                }
                
                //Izquierda
                if (columna>0){
                    int destino = fila*N_COLUMNAS+(columna-1);
                    agregarArista (origen, destino);
                }
                
                //Derecha
                if (columna<N_COLUMNAS-1){
                    int destino= fila*N_COLUMNAS+(columna+1);
                    agregarArista (origen, destino);
                }
                
                //Diagonal arriba-izq
                if (fila>0 && columna>0){
                    int destino= (fila-1)*N_COLUMNAS+(columna-1);
                    agregarArista (origen, destino);
                }
                
                //Diagonal arriba-der
                if (fila>0 && columna<N_COLUMNAS-1){
                    int destino= (fila-1)*N_COLUMNAS+(columna+1);
                    agregarArista (origen, destino);
                }
                
                //Diagonal abajo -izq
                if (fila<N_FILAS-1 && columna>0){
                    int destino= (fila+1)*N_COLUMNAS+(columna-1);
                    agregarArista (origen, destino);
                }
                
                //Diagonal abajo-der
                if (fila<N_FILAS-1 && columna<N_COLUMNAS-1){
                    int destino= (fila+1)*N_COLUMNAS+(columna+1);
                    agregarArista (origen, destino);
                }
            }
        }
    }
    
    /**
     * Obtiene la matriz de adyacencia del grafo.
     * 
     * @return Matriz 16x16 de booleanos donde true indica conexión
     */
    public boolean[][] getMatrizAdyacencia() { 
        return matrizAdyacencia;
    }
    
    /**
     * Obtiene la matriz del tablero de letras.
     * 
     * @return Matriz 4x4 de caracteres
     */
    public char[][] getTablero(){ 
        return tablero;
    }
    
    /**
     * Obtiene la letra correspondiente a un vértice específico.
     * 
     * @param vertice Índice del vértice (0-15)
     * @return Carácter en la posición correspondiente del tablero
     */
    public char getLetra(int vertice) {
        int fila = vertice / N_COLUMNAS;
        int columna = vertice % N_COLUMNAS;
        return tablero[fila][columna];
    }
    
}