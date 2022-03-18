package clases;

import java.beans.PropertyChangeSupport;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    Random random = new Random();
    // Memoria Principal
    MemoriaPrincipal memoria = new MemoriaPrincipal();
    // metodo axiliar para poder convertir de decimal a exadecimal
    Convertidor convert = new Convertidor();
    // variable para llevar id correlativas 0= os, 1 = activador
    int id = 2;
    // objeto del procesador
    procesador cpu = new procesador();
    // Reloj 
    Reloj horaActual = new Reloj();
    //Solicitar Tiempo
    boolean FTime=false;
    public Main() {
        initComponents();
        cpu.start();
        horaActual.start();
    }

    public int getRandom() {

        int value = (int) Math.floor(Math.random() * 10 + 5);
        return value;
    }

    public class procesador extends Thread {

        // variables axiliares
        int quantum = 5;
        // proceso que se esta ejecutando
        Procesos aux = new Procesos();
        Procesos aux2 = new Procesos();
        // contador de programa (que espacio de memoria esta en ejecucion)
        int pc;
        // variable para cambiar de proceso
        int index;

        @Override
        public void run() {
            // proceso infinito para que no se muera el hilo 
            while (true) {

                if (memoria.isEmpty()) {
                    // si no existe nada en la lista de procesos solo se prende y apaga el activador
                    lbBase.setText("");
                    lbIDProceso.setText("");
                    lbLimite.setText("");
                    lbContadorPrograma.setText("");
                    lbActivador.setText("ON");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lbActivador.setText("OFF");
                    // ver que esta cambiando de on a off/ para eso este sleep
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    // si existe algun proceso se carga al procesador
                    while (memoria.ProcesoActual != null) {
                        aux = memoria.ProcesoActual;
                        lbIDProceso.setText(String.valueOf(aux.getId()));
                        lbBase.setText(String.valueOf(aux.getPosinicialHex()));
                        lbLimite.setText(String.valueOf(aux.getPosfinalHex()));
                        pc = aux.getPosinicial();
                        System.out.println("Proceso " + aux.getId());
                        while ((quantum > 0) && (aux.getTamanioRestante() > 0)) {
                            // poner la instruccion ejecutandose
                            lbContadorPrograma.setText(convert.convertir(pc));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            quantum--;
                            System.out.println("Quantum: " + quantum);
                            System.out.println("Tiempo restante: " + aux.getTamanioRestante());
                            pc++;
                            aux.setTamanioRestante(aux.getTamanioRestante() - 1);

                        }
                        // pasan los 5 segundos de quantum o se acabo el tiempo del procesos
                        /* replanificar 
                        ver si el proceso se acabo para eliminarlo 
                        pasar al siguiente proceso
                        si el el ultimo ir al inicio 
                         */
                        // pasar al siguiente proceso, ver si el el ultimo
                        if (memoria.isLast(memoria.ProcesoActual)) {
                            // esta en el ultimo proceso pasa al primero/ si solo hay uno da vueltas
                            memoria.setProcesoActual(memoria.proceso.getFirst());
                        } else {
                            // pasamos al siguiente 

                            aux2 =  memoria.getNext(aux);
                            memoria.setProcesoActual(aux2);

                            
                        }

                        // ver si es necesario eliminar el proceso
                        if (aux.getTamanioRestante() == 0) {
                            memoria.proceso.remove(aux);
                            memoria.Print();
                            // regresar el espacio libre a la memoria
                            memoria.setTamanio(memoria.getTamanio() + aux.getTamanio());
                            // ver si la lista no se quedo basia
                            if (memoria.proceso.size() == 0) {
                                memoria.setProcesoActual(null);
                            }
                        }
                        // mostrar que el activador esta planificando
                        lbActivador.setText("ON");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        lbActivador.setText("OFF");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        quantum = 5;
                    }

                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProceso = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbIDProceso = new javax.swing.JLabel();
        lbContadorPrograma = new javax.swing.JLabel();
        lbBase = new javax.swing.JLabel();
        lbLimite = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbActivador = new javax.swing.JLabel();
        Hora = new javax.swing.JLabel();
        WTime = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnProceso.setText("Agregar Proceso");
        btnProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setText("PROCESADOR");

        jLabel2.setText("ID:");

        jLabel3.setText("Contador de programa:");

        jLabel4.setText("Info del Proceso:");

        jLabel5.setText("Base:");

        jLabel6.setText("Limite:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbIDProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbBase, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbContadorPrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lbIDProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbContadorPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLimite, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );

        jLabel7.setText("ACTIVADOR:");

        Hora.setText("Hora de Sistema");

        WTime.setText("Mostrar Hora");
        WTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnProceso)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbActivador, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Hora, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(WTime)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnProceso)
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lbActivador, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Hora)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(WTime)
                        .addContainerGap(15, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Hilo del Reloj
    public class Reloj extends Thread {

        Calendar calendario;

        @Override
        public void run() {

            while (true) {
                String horaSistema = "";
                calendario = Calendar.getInstance();

                if (calendario.get(Calendar.HOUR_OF_DAY) < 10) {
                    horaSistema += String.valueOf("0" + calendario.get(Calendar.HOUR_OF_DAY)) + ":";
                } else {
                    horaSistema += String.valueOf(calendario.get(Calendar.HOUR_OF_DAY)) + ":";
                }
                if (calendario.get(Calendar.MINUTE) < 10) {
                    horaSistema += String.valueOf("0" + calendario.get(Calendar.MINUTE)) + ":";
                } else {
                    horaSistema += String.valueOf(calendario.get(Calendar.MINUTE)) + ":";
                }
                if (calendario.get(Calendar.SECOND) < 10) {
                    horaSistema += String.valueOf("0" + calendario.get(Calendar.SECOND)) + ":";
                } else {
                    horaSistema += String.valueOf(calendario.get(Calendar.SECOND)) + ":";
                }
                horaSistema += String.valueOf(calendario.get(Calendar.MILLISECOND)) + " hrs";
                Hora.setText(horaSistema);
                if(FTime==true)
                {
                    JOptionPane.showMessageDialog(null, "La Hora es: " + horaSistema);
                    FTime=false;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
   
    }

    private void btnProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesoActionPerformed
        // crear un nuevo proceso
        // variables auxiliares
        int tamanio;
        int posIni;
        int posFin;
        // ver si el proceso es el primero en crearse
        if (memoria.isEmpty()) {
            tamanio = getRandom();
            posIni = memoria.getPunteroDeMomoria();
            posFin = tamanio + posIni;
            Procesos aux = new Procesos(id, tamanio, posIni, posFin, convert.convertir(posIni), convert.convertir(posFin));
            this.id++;
            memoria.proceso.addLast(aux);
            memoria.setTamanio(memoria.getTamanio() - tamanio);
            memoria.ProcesoActual = memoria.proceso.getFirst();
            JOptionPane.showMessageDialog(this, "Proceso creado");
            // visualizar que hay en la lista
            memoria.Print();
        } else {
            // ver si queda espacio para agregarlo 
            tamanio = getRandom();
            if (memoria.isSpace(tamanio)) {
                // existe espacio disponible pero hay que ver que se puede ingresar al final
                if (memoria.cabeAlFinal(tamanio)) {

                    posIni = memoria.ultimaPos();
                    posFin = tamanio + posIni;
                    Procesos aux = new Procesos(id, tamanio, posIni, posFin, convert.convertir(posIni), convert.convertir(posFin));
                    this.id++;
                    memoria.proceso.addLast(aux);
                    memoria.setTamanio(memoria.getTamanio() - tamanio);
                    JOptionPane.showMessageDialog(this, "Proceso creado");
                    // visualizar que hay en la lista
                    memoria.Print();

                } else {
                    JOptionPane.showMessageDialog(this, "No se puede crear el proceso por falta de espacio en memoria");
                }

            } else {
                JOptionPane.showMessageDialog(this, "No se puede crear el proceso por falta de espacio en memoria");
            }
        }
    }//GEN-LAST:event_btnProcesoActionPerformed

    private void WTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WTimeActionPerformed
        // TODO add your handling code here:
        FTime=true;
    }//GEN-LAST:event_WTimeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Hora;
    private javax.swing.JButton WTime;
    private javax.swing.JButton btnProceso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbActivador;
    private javax.swing.JLabel lbBase;
    private javax.swing.JLabel lbContadorPrograma;
    private javax.swing.JLabel lbIDProceso;
    private javax.swing.JLabel lbLimite;
    // End of variables declaration//GEN-END:variables
}
