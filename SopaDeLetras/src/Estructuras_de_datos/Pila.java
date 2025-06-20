/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * Implementación de una pila que almacena datos de tipo T.
 * @author Luis Peña
 */
public class Pila<T> {
    private NodoSimple<T> tope;

    public Pila() {
        this.tope = null;
    }

    /**
     * Agrega un elemento a la pila.
     */
    public void apilar(T dato) {
        NodoSimple<T> nuevo = new NodoSimple(dato);
        nuevo.setNext(tope);
        tope = nuevo;
    }

    /**
     * Elimina y retorna el elemento en el tope de la pila.
     */
    public T desapilar() {
        if (tope == null) {
            return null;
        }
        T dato = tope.getDato();
        tope = tope.getNext();
        return dato;
    }

    /**
     * Verifica si la pila está vacía.
     */
    public boolean esVacia() {
        return tope == null;
    }
}
