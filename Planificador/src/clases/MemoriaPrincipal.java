/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author diego
 */
public class MemoriaPrincipal {
    private int tamanio = 50;
    // las posiciones 0 a 40 estan ocupadas por el os, pocisones 41 a 49 por el activador
    private int punteroDeMomoria = 50;
    public LinkedList<Procesos> proceso = new LinkedList<Procesos>();
    public ArrayList<Object> lista = new ArrayList<>();
    public ArrayList<Integer> t = new ArrayList<>();
    public Procesos ProcesoActual = null;
    //private int t = 0;

    public ArrayList<Integer> getT() {
        return t;
    }

    boolean isEmpty() {
        return proceso.isEmpty();

    }


    public Procesos getNext(Procesos pro) {

        int index = proceso.indexOf(pro);
        index++;

        return proceso.get(index);
    }

    boolean isSpace(int tamanio) {
        if (tamanio <= this.tamanio) {
            return true;
        } else {
            return false;
        }
    }

    boolean cabeAlFinal(int tamanio) {
        int index = proceso.getLast().getPosfinal();

        if ((index + tamanio) > 100) {
            return false;
        } else {
            return true;
        }

    }

    boolean isLast(Procesos pro) {
        if (pro == this.proceso.getLast()) {
            return true;
        } else {
            return false;
        }
    }

    public int ultimaPos() {
        int index = proceso.getLast().getPosfinal();
        return index++;

    }

    public void Print() {
        // metodo para ver que esta en la lista de procesos 
        System.out.println("Memoria");
        System.out.println("OS posI= 0x0 -- PosF= 0x28 ");
        System.out.println("Activador posI= 0x29 -- PosF= 0x31 ");
        for (int i = 0; i < proceso.size(); i++) {

            System.out.println(String.valueOf(proceso.get(i).getId()) + " posI= " + proceso.get(i).getPosinicialHex() + " posF= " + proceso.get(i).getPosfinalHex());
        }
    }

    public ArrayList<Object> mandarInfo() {

        if (lista.isEmpty() == false) {
            lista.clear();
            t.clear();
        }
        for (int i = 0; i < proceso.size(); i++) {

            //System.out.println("!! P"+proceso.get(i).getTamanio() );
            Object[] dato = new Object[]{String.valueOf(proceso.get(i).getId()), String.valueOf(proceso.get(i).getPosinicialHex()), String.valueOf(proceso.get(i).getPosfinalHex())};
            lista.add(dato);
            t.add(proceso.get(i).getTamanio());
        }

        return lista;
    }

    public Procesos getProcesoActual() {
        return ProcesoActual;
    }

    public void setProcesoActual(Procesos ProcesoActual) {
        this.ProcesoActual = ProcesoActual;
    }

    public int getPunteroDeMomoria() {
        return punteroDeMomoria;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

}
