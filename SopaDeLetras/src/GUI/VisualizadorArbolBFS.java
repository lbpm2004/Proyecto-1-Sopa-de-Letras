/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import Estructuras_de_datos.GrafoMatriz;
import Estructuras_de_datos.ListaSimple;
import Estructuras_de_datos.NodoSimple;
import javax.swing.JOptionPane;
/**
 *
 * @author Luis Peña
 */

public class VisualizadorArbolBFS {
    private final Graph graph;

    public VisualizadorArbolBFS() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("Árbol de Recorrido BFS");
        graph.setAttribute("ui.stylesheet", 
            "node { fill-color: #FF9F00; size: 30px; text-size: 20; }");
    }

    /**
     * Muestra el árbol de recorrido BFS para una búsqueda específica.
     * @param grafo Grafo del tablero.
     * @param camino ListaSimple de vértices en el camino de la palabra (en orden).
     */
    public void mostrarArbol(GrafoMatriz grafo, int[] padres, ListaSimple<Integer> camino) {
        graph.clear();

        // Caso especial: camino vacío
        if (camino.esVacia()) {
            JOptionPane.showMessageDialog(null, "Camino vacío - no hay recorrido para mostrar");
            return;
        }

        // Configuración inicial
        ListaSimple<String> nodeIds = new ListaSimple<>();
        int posX = 0;
        NodoSimple<Integer> nodoActual = camino.getFirst();
        String prevNodeId = null;

        // Construir nodos y conexiones
        while (nodoActual != null) {
            int vertice = nodoActual.getDato();
            char letra = grafo.getLetra(vertice);
            String nodeId = "n" + vertice;
            nodeIds.insertarAlFinal(nodeId);

            // Crear nodo en el grafo visual
            org.graphstream.graph.Node node = graph.addNode(nodeId);
            node.setAttribute("ui.label", String.valueOf(letra));
            node.setAttribute("layout.frozen");
            node.setAttribute("xy", posX++, 0);  // Posición horizontal

            // Conectar con nodo anterior (si existe)
            if (prevNodeId != null) {
                graph.addEdge("e" + prevNodeId + "-" + nodeId, prevNodeId, nodeId);
            }
            prevNodeId = nodeId;
            nodoActual = nodoActual.getNext();
        }

        // Mostrar el visualizador
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }
}
