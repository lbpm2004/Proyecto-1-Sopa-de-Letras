/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author luismarianolovera y Diego Linares
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
 
    
    public boolean DFS(int indice, int vertice, GrafoMatriz grafo, String palabra, boolean[] visitado) {
        if (indice == palabra.length()) {
            return true; // Palabra completa encontrada - ¡NO resetear visitados!
        }

        visitado[vertice] = true;

        char siguienteLetra = palabra.charAt(indice); // ¡Error! Debería ser charAt(indice)
        boolean encontrado = false; // Bandera para controlar backtracking

        for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
            if (grafo.getMatrizAdyacencia()[vertice][vecino] && !visitado[vecino]) {
                if (grafo.getLetra(vecino) == siguienteLetra) {
                    encontrado = DFS(indice + 1, vecino, grafo, palabra, visitado);
                    if (encontrado) return true; // Si se encontró, terminar sin reset
                }
            }
        }

        // Solo hacer backtrack si NO se encontró solución
        if (!encontrado) {
            visitado[vertice] = false; // Liberar celda para otros caminos
        }

        return false;
    }
    
    private boolean BFS(int inicio, String palabra, GrafoMatriz grafo, boolean[] visitados) {
        Cola<Integer> q = new Cola<>(); 
        Cola<String> colaQ = new Cola<>(); 
      
        if (grafo.getLetra(inicio) != palabra.charAt(0)) {
            return false;
        }

        visitados[inicio] = true;
        q.encolar(inicio);
        colaQ.encolar(String.valueOf(grafo.getLetra(inicio)));

        while (!q.esVacia()) {
            int verticeActual = (Integer) q.getFront().getDato();
            String currentPath = (String) colaQ.getFront().getDato();
            
            q.desencolar();
            colaQ.desencolar();

            if (currentPath.equals(palabra)) {
                return true;
            }

            if (currentPath.length() >= palabra.length()) {
                continue; 
            }

            for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                if (grafo.getMatrizAdyacencia()[verticeActual][vecino] && !visitados[vecino]) { 
                    char letraVecino = grafo.getLetra(vecino); 
                    
                    if (currentPath.length() < palabra.length() && letraVecino == palabra.charAt(currentPath.length())) {
                        String newPath = currentPath + letraVecino;
                        
                        q.encolar(vecino);
                        colaQ.encolar(newPath);
                        visitados[vecino] = true; 
                    }
                }
            }
        }
        return false;
    }
    
    public ListaSimple<String> buscarTodasDFS(GrafoMatriz grafo, ListaSimple diccionario) {
        ListaSimple<String> encontradas = new ListaSimple<>();

        if (grafo == null || diccionario == null || diccionario.esVacia()) {
            return encontradas;
        }
        
        NodoSimple<String> aux = diccionario.getFirst();
        while (aux != null) {
            String palabra = aux.getDato();

            if (palabra.length() >= 3) {
                this.iniciarBusqueda(); 

                int verticeInicial;
                while ((verticeInicial = establecerVerticeInicial(grafo, palabra)) != -1) {
                    boolean[] visitados = new boolean[GrafoMatriz.N_VERTICES];
                    
                    if (DFS(1, verticeInicial, grafo, palabra, visitados)) {
                        boolean palabraEncontrada = false;
                        NodoSimple<String> nodoResultado = encontradas.getFirst();
                        while(nodoResultado != null) {
                            if (nodoResultado.getDato().equals(palabra)) {
                                palabraEncontrada = true;
                                break;
                            }
                            nodoResultado = nodoResultado.getNext();
                        }
                        
                        if (!palabraEncontrada) {
                            encontradas.insertarAlFinal(palabra);
                        }
                    }
                }
            }
            aux = aux.getNext();
        }
        return encontradas;
    }
    
    public ListaSimple<String> buscarTodasBFS(GrafoMatriz grafo, ListaSimple diccionario) {
        ListaSimple<String> encontradas = new ListaSimple<>();

        if (grafo == null || diccionario == null || diccionario.esVacia()) {
            return encontradas;
        }

        NodoSimple<String> aux = diccionario.getFirst();
        while (aux != null) {
            String palabra = aux.getDato();

            if (palabra.length() >= 3) {
                for (int inicio = 0; inicio < GrafoMatriz.N_VERTICES; inicio++) {
                    boolean[] visitados = new boolean[GrafoMatriz.N_VERTICES];
                    
                    if (BFS(inicio, palabra, grafo, visitados)) {
                        boolean palabraEncontrada = false;
                        NodoSimple<String> nodoResultado = encontradas.getFirst();
                        while(nodoResultado != null) {
                            if (nodoResultado.getDato().equals(palabra)) {
                                palabraEncontrada = true;
                                break;
                            }
                            nodoResultado = nodoResultado.getNext();
                        }
                        
                        if (!palabraEncontrada) {
                            encontradas.insertarAlFinal(palabra);
                        }
                        break; 
                    }
                }
            }
            aux = aux.getNext();
        }
        return encontradas;
    }
}
        
    
        
        
        
        
  