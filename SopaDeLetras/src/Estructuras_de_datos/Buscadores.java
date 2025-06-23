/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * Clase encargada de realizar búsquedas de palabras en el grafo del tablero
 * utilizando algoritmos DFS (Depth-First Search) y BFS (Breadth-First Search).
 * También incluye funcionalidad para reconstruir caminos de palabras encontradas.
 * 
 * @author Diego Linares
 * @author Luis Mariano Lovera
 * @contributor Luis Peña
 */
public class Buscadores {
    private int filaRecorrido;
    private int columnaRecorrido;
    private int[] padresBFS;
    
    /**
     * Constructor que inicializa el estado de búsqueda.
     */
    public Buscadores() {
        this.filaRecorrido = 0;
        this.columnaRecorrido = 0;
        this.padresBFS=null;

    }
    
    /**
     * Reinicia las coordenadas de búsqueda al origen (0,0).
     */
    public void iniciarBusqueda(){
        filaRecorrido=0;
        columnaRecorrido=0;
    }
    
    /**
     * Encuentra el vértice inicial que contiene la primera letra de la palabra objetivo.
     * 
     * @param grafo Grafo que representa el tablero
     * @param palabraObjetivo Palabra a buscar
     * @return Índice del vértice inicial o -1 si no se encuentra
     */
    public int establecerVerticeInicial(GrafoMatriz grafo, String palabraObjetivo) {
        char[][] tablero = grafo.getTablero();
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
 
    /**
     * Realiza búsqueda en profundidad (DFS) para encontrar una palabra.
     * 
     * @param indice Índice actual de la letra buscada en la palabra
     * @param vertice Vértice actual en el grafo
     * @param grafo Grafo que representa el tablero
     * @param palabraObjetivo Palabra a buscar
     * @param visitado Array de vértices visitados
     * @return true si la palabra se encuentra a partir de este vértice
     */
    public boolean DFS(int indice, int vertice, GrafoMatriz grafo, String palabraObjetivo, boolean[] visitado) {
        if (indice == palabraObjetivo.length()) {
            return true;
        }

        visitado[vertice] = true;

        char siguienteLetra = palabraObjetivo.charAt(indice); 
        boolean encontrado = false; 
        
        for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
            if (grafo.getMatrizAdyacencia()[vertice][vecino] && !visitado[vecino]) {
                if (grafo.getLetra(vecino) == siguienteLetra) {
                    encontrado = DFS(indice + 1, vecino, grafo, palabraObjetivo, visitado);
                    if (encontrado) return true; 
                }
            }
        }

        if (!encontrado) {
            visitado[vertice] = false; 
        }

        return false;
    }
    
    /**
     * Realiza búsqueda en amplitud (BFS) para encontrar una palabra.
     * 
     * @param inicio Vértice inicial para la búsqueda
     * @param palabraObjetivo Palabra a buscar
     * @param grafo Grafo que representa el tablero
     * @param visitados Array de vértices visitados
     * @return true si la palabra se encuentra a partir de este vértice
     */
    private boolean BFS(int inicio, String palabraObjetivo, GrafoMatriz grafo) {
        Cola<Integer> q = new Cola<>(); //guarda los vertices
        Cola<String> colaQ = new Cola<>(); //guarda el camino
        Cola<boolean[]> colaVisitados = new Cola<>();
      
        boolean[] inicial = new boolean[GrafoMatriz.N_VERTICES];
        inicial[inicio] = true;
        q.encolar(inicio);
        colaQ.encolar(Character.toString(grafo.getLetra(inicio)));
        colaVisitados.encolar(inicial);
        
        while (!q.esVacia()) {
            int verticeActual = (Integer) q.getFront().getDato();
            String camino = (String) colaQ.getFront().getDato();
            boolean[] vis = (boolean[]) colaVisitados.getFront().getDato();
            
            q.desencolar();
            colaQ.desencolar();
            colaVisitados.desencolar();
            
            if (camino.equals(palabraObjetivo)) {
                return true;
            }

            if (camino.length() >= palabraObjetivo.length()) {
                continue; 
            }

            for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                if (grafo.getMatrizAdyacencia()[verticeActual][vecino] && !vis[vecino]) { 
                    char letraVecino = grafo.getLetra(vecino); 
                    
                    if (letraVecino == palabraObjetivo.charAt(camino.length())) {
                        String newPath = camino + letraVecino;
                        boolean[] copia = new boolean[GrafoMatriz.N_VERTICES];
                        for (int i = 0; i < copia.length; i++) {
                            copia[i] = vis[i];
                        }
                        copia[vecino] = true;

                        q.encolar(vecino);
                        colaQ.encolar(newPath);
                        colaVisitados.encolar(copia);
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Busca todas las palabras del diccionario usando DFS.
     * 
     * @param grafo Grafo que representa el tablero
     * @param diccionario Lista de palabras a buscar
     * @return Lista de palabras encontradas
     */
    public ListaSimple<String> buscarTodasDFS(GrafoMatriz grafo, ListaSimple diccionario) {
        ListaSimple<String> encontradas = new ListaSimple<>();
        NodoSimple<String> aux = diccionario.getFirst();
        
        while (aux != null) {
            String palabra = aux.getDato();
            iniciarBusqueda(); 
            int verticeInicial;
            while ((verticeInicial = establecerVerticeInicial(grafo, palabra)) != -1) {
                boolean[] visitados = new boolean[GrafoMatriz.N_VERTICES];
                    
                if (DFS(1, verticeInicial, grafo, palabra, visitados)) { //pendiente aqu
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
            aux = aux.getNext();
        }
        return encontradas;
    }
    
    /**
     * Busca todas las palabras del diccionario usando BFS.
     * 
     * @param grafo Grafo que representa el tablero
     * @param diccionario Lista de palabras a buscar
     * @return Lista de palabras encontradas
     */
    public ListaSimple<String> buscarTodasBFS(GrafoMatriz grafo, ListaSimple diccionario) {
        ListaSimple<String> encontradas = new ListaSimple<>();

        NodoSimple<String> aux = diccionario.getFirst();
        
        while (aux != null) {
            String palabra = aux.getDato();

            for (int i = 0; i < GrafoMatriz.N_VERTICES; i++) {
                if (grafo.getLetra(i) == palabra.charAt(0)) {
                    if (BFS(i, palabra, grafo)) {
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

    /**
     * Realiza BFS y retorna el camino de vértices que forman la palabra.
     * 
     * @param palabra Palabra a buscar
     * @param grafo Grafo que representa el tablero
     * @return Lista de vértices que forman el camino de la palabra
     */
   public ListaSimple<Integer> BFSConCamino(String palabra, GrafoMatriz grafo) {
    iniciarBusqueda();
        int verticeInicial=establecerVerticeInicial(grafo, palabra);

        while (verticeInicial != -1) {
            boolean[] visitados = new boolean[GrafoMatriz.N_VERTICES];
            int[] padres = new int[GrafoMatriz.N_VERTICES];
            int[] nivel = new int[GrafoMatriz.N_VERTICES];

            for (int i = 0; i < padres.length; i++) {
                padres[i]=-1;
                nivel[i]=-1;
            }

            Cola<Integer> cola = new Cola<>();
            cola.encolar(verticeInicial);
            visitados[verticeInicial] = true;
            nivel[verticeInicial]=0;

            int fin=-1;
        
   
            while (!cola.esVacia()) {
                int actual = cola.desencolarDato();
                if (grafo.getLetra(actual)==palabra.charAt(nivel[actual])){
                    if (nivel[actual] == palabra.length() - 1) {
                        fin = actual;
                    }               
                }

                if (nivel[actual] < palabra.length() - 1) {
                    for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                        if (grafo.getMatrizAdyacencia()[actual][vecino] && !visitados[vecino]) {
                            if (grafo.getLetra(vecino) == palabra.charAt(nivel[actual] + 1)) {
                                visitados[vecino]=true;
                                cola.encolar(vecino);
                                padres[vecino]=actual;
                                nivel[vecino] = nivel[actual] + 1;
                            }
                        }
                    }
                }
            }
            if (fin != -1) {
                padresBFS=padres;
                return reconstruirCamino(padres, verticeInicial, fin);
            }else{
                verticeInicial = establecerVerticeInicial(grafo, palabra);  
            }
        }   
        return new ListaSimple<>();
    }

    /**
     * Reconstruye el camino desde el vértice final hasta el inicial.
     * 
     * @param padres Arreglo de padres de cada vértice
     * @param inicio Vértice inicial
     * @param fin Vértice final
     * @return Lista de vértices en orden desde inicio hasta fin
     */
    private ListaSimple<Integer> reconstruirCamino(int[] padres, int inicio, int fin) {
        Pila<Integer> pila = new Pila<>(); 

        // Reconstruir de fin a inicio
        int actual = fin;
        while (actual != -1) {
            pila.apilar(actual);
            actual = padres[actual];
        }

        // desapilar para obtener inicio->fin
        ListaSimple<Integer> camino = new ListaSimple<>();
        while (!pila.esVacia()) {
            camino.insertarAlFinal(pila.desapilar());
        }
        return camino;
    }  
    
    /**
     * Obtiene el arreglo de padres de la última búsqueda BFS realizada.
     * 
     * @return Arreglo de padres de los vértices
     */
    public int[] getPadresBFS() {
        return padresBFS;
    }
    
}
