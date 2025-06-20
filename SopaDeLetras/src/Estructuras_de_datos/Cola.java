/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 *
 * @author diego
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
    
    public boolean esVacia(){
        return front == null;
    }
    
    public void vaciar(){
        front=null;
        back=null;
        tamaño=0;
    }
    
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
    
    public String mostrarCola(){
        String cadena="";
        NodoSimple aux = front;
        
        while (aux!=null){
            cadena=cadena+aux.getDato()+"\n";
            aux=aux.getNext();
        }
        return cadena;
    } 

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
