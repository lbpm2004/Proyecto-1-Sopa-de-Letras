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
    private int[] padresBFS;
  
    public Buscadores() {
        this.filaRecorrido = 0;
        this.columnaRecorrido = 0;
        this.padresBFS=null;

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
    
    public ListaSimple<String> buscarTodasBFS(GrafoMatriz grafo, ListaSimple diccionario) {
        ListaSimple<String> encontradas = new ListaSimple<>();
        NodoSimple<String> aux = diccionario.getFirst();
        iniciarBusqueda();
        
        while (aux != null) {
            String palabra = aux.getDato();
            int verticeInicial;
            
            while ((verticeInicial = establecerVerticeInicial(grafo, palabra)) != -1) {
                boolean[] visitados = new boolean[GrafoMatriz.N_VERTICES];

                if (BFS(verticeInicial, palabra, grafo, visitados)) {
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
            aux = aux.getNext();
        }
        return encontradas;
    }

    /**
     * Realiza BFS y retorna el camino de la palabra (ListaSimple de vértices).
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
    
    public int[] getPadresBFS() {
        return padresBFS;
    }
    
}
