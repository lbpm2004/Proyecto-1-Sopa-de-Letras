/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * Implementación de una estructura de datos Cola (FIFO).
 * Permite las operaciones básicas de encolar, desencolar y verificación de estado.
 * 
 * @param <T> Tipo genérico de los elementos almacenados en la cola
 * @author Diego Linares
 * @contributor Luis Peña
 */
public class Cola<T> {
    private NodoSimple<T> front;
    private NodoSimple<T> back;
    private int tamaño;
    
    /**
     * Constructor que inicializa una cola vacía.
     */
    public Cola() {
        this.front = null;
        this.back = null;
        this.tamaño = 0;
    }
    
    /**
     * Verifica si la cola está vacía.
     * 
     * @return true si la cola no contiene elementos, false en caso contrario
     */
    public boolean esVacia(){
        return front == null;
    }
    
    /**
     * Vacía la cola, eliminando todos sus elementos.
     */
    public void vaciar(){
        front=null;
        back=null;
        tamaño=0;
    }
    
    /**
     * Agrega un elemento al final de la cola.
     * 
     * @param dato Elemento a encolar
     */
    public void encolar(T dato) {
        NodoSimple<T> nuevoNodo = new NodoSimple<>(dato);
        if (esVacia()) {
            front = back = nuevoNodo;
        } else {
            back.setNext(nuevoNodo);
            back = nuevoNodo;
        }
        tamaño++;
    }
    
    /**
     * Elimina el elemento del frente de la cola.
     * 
     * @throws IllegalStateException si se intenta desencolar de una cola vacía
     */
    public void desencolar() {
        if(esVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        front = front.getNext();
        if (front == null) {
            back = null;
        }
        tamaño --;
    }
    
    /**
     * Elimina y devuelve el elemento del frente de la cola.
     * 
     * @return Elemento removido del frente, o null si la cola está vacía
     */
    public T desencolarDato() {
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
    
    /**
     * Genera una representación en cadena de todos los elementos de la cola.
     * Los elementos se muestran en orden desde el frente hasta el final,
     * cada uno en una línea separada.
     * 
     * @return Cadena con los elementos de la cola separados por saltos de línea
     */
    public String mostrarCola(){
        String cadena="";
        NodoSimple aux = front;
        
        while (aux!=null){
            cadena=cadena+aux.getDato()+"\n";
            aux=aux.getNext();
        }
        return cadena;
    } 

    /**
     * Getters y Setters
     */
    public NodoSimple getFront() {
        return front;
    }

    public void setFront(NodoSimple front) {
        this.front = front;
    }

    public NodoSimple getBack() {
        return back;
    }

    public void setBack(NodoSimple back) {
        this.back = back;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

}
