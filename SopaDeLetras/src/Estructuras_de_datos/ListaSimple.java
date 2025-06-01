/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras_de_datos;

/**
 * @author Luis Peña
 * Descripción de la clase: Lista simplemente enlazada que almacena datos de tipo NodoSimple.
 */
public class ListaSimple {
    private NodoSimple first;
    private NodoSimple last;
    private int tamaño;

    public ListaSimple() {
        this.first = null;
        this.last = null;
        this.tamaño = 0;
    }
    
    public boolean EsVacia(){
        return first == null;
    }
    
    public void InsertarAlFinal(String newDato){
        NodoSimple aux = new NodoSimple(newDato);
        
        if(this.EsVacia()){
            first = last = aux;
        }else{
            last.setNext(aux);
            last = aux;
        }
        tamaño++;        
    }

    public NodoSimple getFirst() {
        return first;
    }

    public void setFirst(NodoSimple first) {
        this.first = first;
    }

    public NodoSimple getLast() {
        return last;
    }

    public void setLast(NodoSimple last) {
        this.last = last;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
}
