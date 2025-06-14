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
    private char[][] celdaTablero; //matriz 4 filas x 4 columnas
    private boolean[][] matrizAdyacencia; // matriz 16x16, demuestra si es adyacente o no
    public static final int N_VERTICES = 16; //Numero de vertices del grafo
    public static final int N_FILAS=4;
    public static final int N_COLUMNAS=4;
    

    public GrafoMatriz(char[][] tablero) {
        this.celdaTablero = tablero;
        this.matrizAdyacencia = new boolean [N_VERTICES][N_VERTICES];
    }
    
    public void agregarArista(int origen, int destino){
        matrizAdyacencia[origen][destino]=true;
        matrizAdyacencia[destino][origen]=true;
    }

    public void construirGrafo(){
        for (int fila = 0; fila < N_FILAS; fila++) {
            for (int columna = 0; columna < N_COLUMNAS; columna++) {
                int origen = fila * N_COLUMNAS + columna;
                
                //arriba               
                if (fila > 0) {
                    int destino = (fila-1)*N_COLUMNAS+columna;
                    agregarArista (origen, destino);
                }
                
                //abajo
                if (fila < N_FILAS-1){
                    int destino=(fila+1)*N_COLUMNAS+columna;
                    agregarArista (origen, destino);
                }
                
                //izquierda
                if (columna>0){
                    int destino = fila*N_COLUMNAS+(columna-1);
                    agregarArista (origen, destino);
                }
                
                //derecha
                if (columna<N_COLUMNAS-1){
                    int destino= fila*N_COLUMNAS+(columna+1);
                    agregarArista (origen, destino);
                }
                
                //diagonal arriba-izq
                if (fila>0 && columna>0){
                    int destino= (fila-1)*N_COLUMNAS+(columna-1);
                    agregarArista (origen, destino);
                }
                
                //diagonal arriba-der
                if (fila>0 && columna<N_COLUMNAS-1){
                    int destino= (fila-1)*N_COLUMNAS+(columna+1);
                    agregarArista (origen, destino);
                }
                
                //diagonal abajo -izq
                if (fila<N_FILAS-1 && columna>0){
                    int destino= (fila+1)*N_COLUMNAS+(columna-1);
                    agregarArista (origen, destino);
                }
                
                // diagonal abajo-der
                if (fila<N_FILAS-1 && columna<N_COLUMNAS-1){
                    int destino= (fila+1)*N_COLUMNAS+(columna+1);
                    agregarArista (origen, destino);
                }
            }
        }
    }
                
    public char[][] getCeldaTablero(){ //revisar
        return celdaTablero;
    }

    public boolean[][] getMatrizAdyacencia() { //revisar
        return matrizAdyacencia;
    }
    
}
