/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author luismarianolovera
 */
public class GrafoMatriz {
    private final char[][] celdaTablero; //matriz 4 filas x 4 columnas
    private final boolean[][] matrizAdyacente; // matriz 16x16, demuestra si es adyacente o no
    private static final int N_VERTICES = 16; //Numero de vertices del grafo
    private static final int N_FILAS=4;
    private static final int N_COLUMNAS=4;
    

    public GrafoMatriz(char[][] tablero) {
        this.celdaTablero = tablero;
        this.matrizAdyacente = new boolean [N_VERTICES][N_VERTICES];
    }

    public void GenerarAdyacencia(){
        for (int fila = 0; fila < N_FILAS; fila++) {
            for (int columna = 0; columna < N_COLUMNAS; columna++) {
                int origen = fila * N_COLUMNAS + columna;
                
                //arriba               
                if (fila > 0) {
                    int destino = (fila-1)*N_COLUMNAS+columna;
                    matrizAdyacente[origen][destino] = true;   
                }
                
                //abajo
                if (fila < N_FILAS-1){
                    int destino=(fila+1)*N_COLUMNAS+columna;
                    matrizAdyacente[origen][destino] = true; 
                }
                
                //izquierda
                if (columna>0){
                    int destino = fila*N_COLUMNAS+(columna-1);
                    matrizAdyacente[origen][destino] = true; 
                }
                
                //derecha
                if (columna<N_COLUMNAS-1){
                    int destino= fila*N_COLUMNAS+(columna+1);
                    matrizAdyacente[origen][destino] = true;
                }
                
                //diagonal arriba-izq
                if (fila>0 && columna>0){
                    int destino= (fila-1)*N_COLUMNAS+(columna-1);
                    matrizAdyacente[origen][destino] = true;
                }
                
                //diagonal arriba-der
                if (fila>0 && columna<N_COLUMNAS-1){
                    int destino= (fila-1)*N_COLUMNAS+(columna+1);
                    matrizAdyacente[origen][destino] = true;
                }
                
                //diagonal abajo -izq
                if (fila<N_FILAS-1 && columna>0){
                    int destino= (fila+1)*N_COLUMNAS+(columna-1);
                    matrizAdyacente[origen][destino] = true;
                }
                
                // diagonal abajo-der
                if (fila<N_FILAS-1 && columna<N_COLUMNAS-1){
                    int destino= (fila+1)*N_COLUMNAS+(columna+1);
                    matrizAdyacente[origen][destino] = true;
                }
            }
        }
    }
    
     
}
