/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author Luis Peña
 */
public class Pila<T> {
    private NodoSimple<T> tope;

    public boolean esVacia() {
        return tope == null;
    }
    
    public void apilar(T dato) {
        NodoSimple<T> nuevo = new NodoSimple<>(dato);
        nuevo.setNext(tope);
        tope = nuevo;
    }

    public T desapilar() {
        if (tope == null) {
            return null;
        }
        T dato = tope.getDato();
        tope = tope.getNext();
        return dato;
    }

}