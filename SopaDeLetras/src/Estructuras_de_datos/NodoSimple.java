/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * @author Luis Peña
 * Descripción de la clase: Define un tipo de dato abstracto, constituido por un dato de tipo String (palabra) y next (un apuntador a tipo de dato NodoSimple)
 */
public class NodoSimple<T> {
    private T dato;
    private NodoSimple<T> next;

    public NodoSimple(T newDato) {
        this.dato = newDato;
        this.next = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoSimple getNext() {
        return next;
    }

    public void setNext(NodoSimple next) {
        this.next = next;
    }
    
}
