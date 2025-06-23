/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * Implementación de una estructura de datos Pila (LIFO).
 * Permite las operaciones básicas de apilar, desapilar y verificación de estado vacío.
 * 
 * @param <T> Tipo genérico de los elementos almacenados en la pila
 * @author Luis Peña
 */
public class Pila<T> {
    private NodoSimple<T> tope;
    
    /**
     * Constructor que inicializa una pila vacía.
     */
    public Pila(){
        this.tope = null;
    }
    
    /**
     * Verifica si la pila está vacía.
     * 
     * @return true si la pila no contiene elementos, false en caso contrario
     */
    public boolean esVacia() {
        return tope == null;
    }
    
    /**
     * Agrega un elemento en la parte superior de la pila.
     * 
     * @param dato Elemento a apilar
     */
    public void apilar(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        nuevo.setNext(tope);
        tope = nuevo;
    }
    
    /**
     * Elimina y devuelve el elemento de la parte superior de la pila.
     * 
     * @return Elemento removido del tope, o null si la pila está vacía
     */
    public T desapilar() {
        if (tope == null) {
            return null;
        }
        T dato = tope.getDato();
        tope = tope.getNext();
        return dato;
    }

}