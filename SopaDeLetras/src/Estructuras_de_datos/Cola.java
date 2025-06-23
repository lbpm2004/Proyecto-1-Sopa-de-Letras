/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * Implementación de una cola genérica basada en nodos enlazados.
 *
 * @author Diego Linares
 * @colaborator Luis Peña
 */
public class Cola<T> {
    private NodoSimple<T> front;
    private NodoSimple<T> back;
    private int tamaño;

    /**
     * Crea una cola vacía.
     */
    public Cola() {
        this.front = null;
        this.back = null;
        this.tamaño = 0;
    }
    
    /**
     * Comprueba si la cola se encuentra vacia
     * 
     * @return true si la cola está vacía, false en caso contrario.
     */
    public boolean esVacia(){
        return front == null;
    }
    
    /**
     * Elimina todos los elementos de una cola
     */
    public void vaciar(){
        front=null;
        back=null;
        tamaño=0;
    }
    
    /**
     * Añade un elemento al final de la cola.
     * 
     * @param dato Elemento que se quiere encolar.
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
     * Elimina el elemento al frente de la cola.
     * 
     * @throws IllegalStateException si la cola está vacía.
     */
    public void desencolar() {
        if(esVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        front = front.getNext();
        if (front == null) {
            back = null;
        }
        tamaño--;
    }
    
    /**
     * Desencola el elemento al frente de la cola.
     * 
     * @return El dato desencolado, o nulo si la cola esta vacía.
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
     * Recorre la cola a fin de mostrar sus elementos.
     * 
     * @return Cadena con los elementos de la cola.
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

    //Getters y setters
    
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
