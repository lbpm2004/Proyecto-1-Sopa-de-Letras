/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author luismarianolovera
 * @colaborador Luis Peña
 */
public class Buscadores {
    private GrafoMatriz grafo;
    private ListaSimple<String> diccionario;

    public Buscadores(GrafoMatriz grafo, ListaSimple diccionario) {
        this.grafo = grafo;
        this.diccionario = diccionario;
    }
    
    public String buscarPalabras(boolean metodoBusqueda) {
        String resultados = "";
        NodoSimple<String> nodoActual = diccionario.getFirst();

        while (nodoActual != null) {
            String palabra = nodoActual.getDato();
            boolean palabraEncontrada;

            if (metodoBusqueda) {
                palabraEncontrada = BFS(palabra);
            } else {
                palabraEncontrada = DFS(palabra);
            }

            if (palabraEncontrada) {
                resultados += palabra + "\n";
            }
            nodoActual = nodoActual.getNext();
        }
        return resultados;
    }
        
    private boolean BFS(String palabra) {
        char primeraLetra = palabra.charAt(0);
        char[][] tablero = grafo.getCeldaTablero();

        for (int i = 0; i < GrafoMatriz.N_FILAS; i++) { 
            for (int j = 0; j < GrafoMatriz.N_COLUMNAS; j++) { 
                if (tablero[i][j] == primeraLetra) {
                    int verticeInicial = i * GrafoMatriz.N_COLUMNAS + j;
                    ListaSimple<Integer> celdasUsadas = new ListaSimple();
                    if (bfsDesdeVertice(verticeInicial, palabra, celdasUsadas)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

        
    private boolean bfsDesdeVertice(int verticeInicial, String palabra, ListaSimple celdasUsadas) {
        Cola<Integer> cola = new Cola<>();
        cola.encolar(verticeInicial);
        celdasUsadas.insertarAlFinal(verticeInicial);
        int letraActual = 1;

        while (!cola.esVacia() && letraActual < palabra.length()) {
            int vertice = cola.desencolar();
            char letraBuscada = palabra.charAt(letraActual);

            for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                if (grafo.getMatrizAdyacencia()[vertice][vecino] && !contiene(celdasUsadas, vecino)) {
                    int fila = vecino / GrafoMatriz.N_COLUMNAS;
                    int col = vecino % GrafoMatriz.N_COLUMNAS;
                    if (grafo.getCeldaTablero()[fila][col] == letraBuscada) {
                        cola.encolar(vecino);
                        celdasUsadas.insertarAlFinal(vecino);
                        letraActual++;
                        break;
                    }
                }
            }
        }
        return letraActual == palabra.length();
    }
    
    private boolean DFS(String palabra) {
        char primeraLetra = palabra.charAt(0);
        char[][] tablero = grafo.getCeldaTablero();

        for (int i = 0; i < GrafoMatriz.N_FILAS; i++) {
            for (int j = 0; j < GrafoMatriz.N_COLUMNAS; j++) {
                if (tablero[i][j] == primeraLetra) {
                    int verticeInicial = i * GrafoMatriz.N_COLUMNAS + j;
                    if (dfsDesdeVertice(verticeInicial, palabra)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * DFS desde un vértice inicial.
     */
    private boolean dfsDesdeVertice(int verticeInicial, String palabra) {
        Pila<Integer> pila = new Pila();
        ListaSimple<Integer> celdasUsadas = new ListaSimple();
        pila.apilar(verticeInicial);
        celdasUsadas.insertarAlFinal(verticeInicial);
        int letraActual = 1;

        while (!pila.esVacia() && letraActual < palabra.length()) {
            int vertice = pila.desapilar();
            char letraBuscada = palabra.charAt(letraActual);

            for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                if (grafo.getMatrizAdyacencia()[vertice][vecino] && !contiene(celdasUsadas, vecino)) {
                    int fila = vecino / GrafoMatriz.N_COLUMNAS;
                    int col = vecino % GrafoMatriz.N_COLUMNAS;
                    if (grafo.getCeldaTablero()[fila][col] == letraBuscada) {
                        pila.apilar(vecino);
                        celdasUsadas.insertarAlFinal(vecino);
                        letraActual++;
                        break;
                    }
                }
            }
        }
        return letraActual == palabra.length();
    }
    
    // Método auxiliar para verificar si la lista contiene un elemento
    private boolean contiene(ListaSimple<Integer> lista, int valor) {
        NodoSimple<Integer> actual = lista.getFirst();
        while (actual != null) {
            if (actual.getDato() == valor) {
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }
}
