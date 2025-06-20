/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author Luis Peña
 */
public class Cola<T> {
    private NodoSimple<T> front;
    private NodoSimple<T> back;
    private int tamaño;

    public Cola() {
        this.front = null;
        this.back = null;
        this.tamaño = 0;
    }

    public void encolar(T dato) {
        NodoSimple nuevoNodo = new NodoSimple(dato);
        if (this.esVacia()) {
            front = back = nuevoNodo;
        } else {
            back.setNext(nuevoNodo);
            back = nuevoNodo;
        }
        tamaño++;
    }

    public T desencolar() {
        
        if (this.esVacia()) {
            return null;
        }
       
        T dato = front.getDato();
        front = front.getNext();
        tamaño--; 
        if(this.esVacia()){
            back = null; 
        }
        return dato;
    }

    public boolean esVacia() {
        return front == null;
    }
}
