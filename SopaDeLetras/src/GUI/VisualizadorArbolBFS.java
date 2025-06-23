/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;

import Estructuras_de_datos.GrafoMatriz;
import Estructuras_de_datos.ListaSimple;
import Estructuras_de_datos.NodoSimple;
import Estructuras_de_datos.Cola;

import javax.swing.JOptionPane;

/**
 * Visualizador gráfico del árbol BFS generado durante la búsqueda en la sopa de letras.
 * Utiliza la biblioteca GraphStream para representar visualmente el grafo y resaltar el camino encontrado.
 * 
 * @author Luis Peña
 * @author Luis Mariano Lovera
 */
public class VisualizadorArbolBFS {
    private final Graph graph;
    private final String stylesheet =
        "node {\n" +
        "  fill-color: #5DADE2;\n" +
        "  size: 30px;\n" +
        "  text-mode: normal;\n" +
        "  text-alignment: center;\n" +
        "  text-size: 20;\n" +
        "  text-style: bold;\n"+       
        "  text-color: #000;\n" +
        "}\n" +
        "edge {\n" +
        "  fill-color: #BBBBBB;\n" +
        "  arrow-shape: arrow;\n" +
        "  arrow-size: 10px, 6px;\n" +
        "}\n" +
        "node.highlight {\n" +
        "  fill-color: #F39C12;\n" +
        "}\n" +
        "edge.path {\n" +
        "  fill-color: #27AE60;\n" +
        "  size: 3px;\n" +
        "}\n";
    
    /**
     * Constructor que inicializa el visualizador con configuración básica:
     * - Establece el renderizador como Swing
     * - Crea un grafo vacío con estilos predefinidos
     * - Configura calidad de visualización
     */
    public VisualizadorArbolBFS() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("Árbol BFS");
        graph.setAttribute("ui.stylesheet", stylesheet);
        graph.setAttribute("ui.quality", true);
        graph.setAttribute("ui.antialias", true);
    }
    
    /**
     * Construye el árbol BFS a partir de un nodo raíz en el grafo.
     * 
     * @param raiz Índice del nodo raíz para la búsqueda BFS
     * @param grafo GrafoMatriz que contiene la estructura de adyacencia
     * @return Arreglo de padres donde padresTree[i] = padre del nodo i
     */
    private int[] construccionArbol(int raiz, GrafoMatriz grafo) {
        int N = GrafoMatriz.N_VERTICES;
        boolean[] visited = new boolean[N];
        int[] padresTree = new int[N];
        // inicializar padresTree a -1
        for (int i = 0; i < N; i++) {
            padresTree[i] = -1;
        }

        // cola propia
        Cola<Integer> q = new Cola<>();
        visited[raiz] = true;
        q.encolar(raiz);

        while (!q.esVacia()) {
            int u = q.desencolarDato();
            for (int v = 0; v < N; v++) {
                if (!visited[v] && grafo.getMatrizAdyacencia()[u][v]) {
                    visited[v]    = true;
                    padresTree[v] = u;
                    q.encolar(v);
                }
            }
        }
        return padresTree;
    }
    
    /**
     * Muestra visualmente el árbol BFS completo con el camino encontrado resaltado.
     * 
     * @param grafo GrafoMatriz que contiene las letras y conexiones
     * @param camino Lista simple con los índices de los nodos del camino encontrado
     * @throws IllegalArgumentException Si el camino está vacío
     */
    public void mostrarArbolCompleto(GrafoMatriz grafo, ListaSimple<Integer> camino) {
        if (camino.esVacia()) {
            JOptionPane.showMessageDialog(
                null,
                "No hay recorrido para mostrar.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }

        //Raíz de la búsqueda
        int raiz = (Integer) camino.getFirst().getDato();
   
        //Construcción del árbol completo
        int[] padresTree = construccionArbol(raiz, grafo);

        //Limpiar arbol y reaplicar estilo
        graph.clear();
        graph.setAttribute("ui.stylesheet", stylesheet);

        int N = GrafoMatriz.N_VERTICES;
        //Nodos alcanzados
        for (int i = 0; i < N; i++) {
            if (i == raiz || padresTree[i] != -1) {
                Node n = graph.addNode("n" + i);
                n.setAttribute("ui.label", "" + grafo.getLetra(i));
            }
        }
        //Adición aristas padre-hijo
        for (int i = 0; i < N; i++) {
            int p = padresTree[i];
            if (p != -1) {
                graph.addEdge("e" + p + "-" + i, "n" + p, "n" + i, true);
            }
        }

        //Calculo vel[] para cada nodo con BFS sobre padresTree
        int[] nivel = new int[N];
        for (int i = 0; i < N; i++) {
            nivel[i] = -1;
        }
        nivel[raiz] = 0;
        Cola<Integer> cola2 = new Cola<>();
        cola2.encolar(raiz);
        while (!cola2.esVacia()) {
            int u = cola2.desencolarDato();
            for (int v = 0; v < N; v++) {
                if (padresTree[v] == u) {
                    nivel[v] = nivel[u] + 1;
                    cola2.encolar(v);
                }
            }
        }

        //Determinacion de nivel máximo
        int maxNivel = 0;
        for (int l : nivel) {
            if (l > maxNivel) {
                maxNivel = l;
            }
        }

        //Agrupación de nodos
        ListaSimple<Integer>[] nodosPorNivel = new ListaSimple[maxNivel + 1];
        for (int i = 0; i <= maxNivel; i++) {
            nodosPorNivel[i] = new ListaSimple<>();
        }
        for (int v = 0; v < N; v++) {
            if (nivel[v] >= 0) {
                nodosPorNivel[nivel[v]].insertarAlFinal(v);
            }
        }

        // Layout manual: fijar posición según nivel y orden en la fila
        int gapX = 40, gapY = 70;
        for (int lvl = 0; lvl <= maxNivel; lvl++) {
            //Contador de niveles
            int count = 0;
            NodoSimple<Integer> aux = nodosPorNivel[lvl].getFirst();
            while (aux != null) {
                count++;
                aux = aux.getNext();
            }
            //Asigna posiciones
            aux = nodosPorNivel[lvl].getFirst();
            int idx = 0;
            while (aux != null) {
                int v = aux.getDato();
                double x = (idx - (count - 1) / 2.0) * gapX;
                double y = -lvl * gapY;
                Node n = graph.getNode("n" + v);
                n.setAttribute("xy", x, y);
                n.setAttribute("layout.frozen", true);
                aux = aux.getNext();
                idx++;
            }
        }

        //Resalta rama palabra
        Integer prev = null;
        NodoSimple<Integer> cur = camino.getFirst();
        while (cur != null) {
            int v = cur.getDato();
            graph.getNode("n" + v).setAttribute("ui.class", "highlight");
            if (prev != null) {
                Edge e = graph.getEdge("e" + prev + "-" + v);
                if (e != null) {
                    e.setAttribute("ui.class", "path");
                }
            }
            prev = v;
            cur = cur.getNext();
        }

        //Mostrar grafo
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
}