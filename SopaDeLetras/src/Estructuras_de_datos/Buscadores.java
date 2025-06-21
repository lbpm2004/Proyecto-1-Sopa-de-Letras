/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author luismarianolovera
 * @Colaboradores Diego Linares y Luis Peña
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
    
    private boolean BFS(int inicio, String palabraObjetivo, GrafoMatriz grafo, boolean[] visitados) {
        Cola<Integer> q = new Cola<>(); //guarda los vertices
        Cola<String> colaQ = new Cola<>(); //guarda el camino
      
        q.encolar(inicio);
        colaQ.encolar(Character.toString(grafo.getLetra(inicio)));

        while (!q.esVacia()) {
            int verticeActual = (Integer) q.getFront().getDato();
            String camino = (String) colaQ.getFront().getDato();
            
            q.desencolar();
            colaQ.desencolar();
            visitados[verticeActual] = true;
            
            if (camino.equals(palabraObjetivo)) {
                return true;
            }

            if (camino.length() >= palabraObjetivo.length()) {
                continue; 
            }

            for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                if (grafo.getMatrizAdyacencia()[verticeActual][vecino] && !visitados[vecino]) { 
                    char letraVecino = grafo.getLetra(vecino); 
                    
                    if (camino.length() < palabraObjetivo.length() && letraVecino == palabraObjetivo.charAt(camino.length())) {
                        String newPath = camino + letraVecino;
                        
                        q.encolar(vecino);
                        colaQ.encolar(newPath);
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

    /**
     * Realiza BFS y retorna el camino de la palabra (ListaSimple de vértices).
     */
    public ListaSimple<Integer> BFSConCamino(int inicio, String palabra, GrafoMatriz grafo) {
        boolean[] visitados = new boolean[GrafoMatriz.N_VERTICES];
        int[] padres = new int[GrafoMatriz.N_VERTICES];
        for (int i = 0; i < padres.length; i++) padres[i] = -1;

        Cola<Integer> cola = new Cola<>();
        visitados[inicio] = true;
        cola.encolar(inicio);
        padres[inicio] = -1;

        int nivel = 0; // Posición actual en la palabra
        boolean encontrada = false;
        int fin = -1;

        while (!cola.esVacia() && nivel < palabra.length()) {
            int size = cola.getTamaño();

            for (int i = 0; i < size; i++) {
                int vertice = cola.desencolarDato();
                char letraActual = palabra.charAt(nivel);

                // Verificar coincidencia de letra
                if (grafo.getLetra(vertice) != letraActual) {
                    continue;
                }

                // Palabra completa?
                if (nivel == palabra.length() - 1) {
                    encontrada = true;
                    fin = vertice;
                    break;
                }

                // Explorar vecinos
                for (int vecino = 0; vecino < GrafoMatriz.N_VERTICES; vecino++) {
                    if (grafo.getMatrizAdyacencia()[vertice][vecino] && !visitados[vecino]) {
                        visitados[vecino] = true;
                        cola.encolar(vecino);
                        padres[vecino] = vertice;
                    }
                }
            }

            if (encontrada) {
                break;
            }
            nivel++;
        }

        // 3. Reconstruir camino si se encontró
        if (encontrada) {
            return reconstruirCamino(padres, inicio, fin);
        }
        return new ListaSimple<>(); // Camino vacío
    }

    /**
     * 
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
}