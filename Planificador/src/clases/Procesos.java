/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author diego
 */
public class Procesos {
    private int id;
    private int tamanio; // sin ñ para evitar problemas
    private int tamanioRestante;
    private int Posinicial;
    private int Posfinal;
    private String PosinicialHex;
    private String PosfinalHex;

    // contructor
    
    public Procesos(int id,int tamanio, int Posinicial, int Posfinal, String PosinicialHex, String PosfinalHex) {
        this.id=id;
        this.tamanio = tamanio;
        this.tamanioRestante = tamanio;
        this.Posinicial = Posinicial;
        this.Posfinal = Posfinal;
        this.PosinicialHex = PosinicialHex;
        this.PosfinalHex = PosfinalHex;
    }
     public Procesos() {
       
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getTamanioRestante() {
        return tamanioRestante;
    }

    public void setTamanioRestante(int tamanioRestante) {
        this.tamanioRestante = tamanioRestante;
    }

    public int getPosinicial() {
        return Posinicial;
    }

    public void setPosinicial(int Posinicial) {
        this.Posinicial = Posinicial;
    }

    public int getPosfinal() {
        return Posfinal;
    }

    public void setPosfinal(int Posfinal) {
        this.Posfinal = Posfinal;
    }

    public String getPosinicialHex() {
        return PosinicialHex;
    }

    public void setPosinicialHex(String PosinicialHex) {
        this.PosinicialHex = PosinicialHex;
    }

    public String getPosfinalHex() {
        return PosfinalHex;
    }

    public void setPosfinalHex(String PosfinalHex) {
        this.PosfinalHex = PosfinalHex;
    }
    
}
