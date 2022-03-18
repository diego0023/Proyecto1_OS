/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.util.Calendar;
import java.util.Date;
/**
 *
 * @author User
 */
public class Historial {

    
    Procesos proces = new Procesos();
    Calendar cal = Calendar.getInstance();
    Date fechahora = new Date();
    private int id;
    private String tiempo_inicial;
    private String tiempo_final;
    
    public Historial(int id1) {
        this.id=  id1;
        this.tiempo_inicial=" ";
        this.tiempo_final=" ";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = proces.getId();
    }

    public String getTiempo_inicial() {
        return tiempo_inicial;
    }

    public void setTiempo_inicial() {
        this.tiempo_inicial = " Fecha y hora: " + fechahora;
    }

    public String getTiempo_final() {
        return tiempo_final;
    }

    public void setTiempo_final(){
        //this.tiempo_final  = "Fecha: " + cal.get(cal.DATE)+"/"+cal.get(cal.MONTH)+ " Hora: "+cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND);
        this.tiempo_final="Fecha y hora: " + fechahora;
    }
}
