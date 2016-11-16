/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import javax.swing.ImageIcon;

/**
 *
 * @author Belen
 */
public class frminicio extends javax.swing.JFrame {

    /**
     * Creates new form frminicio
     */
    public frminicio() {
        initComponents();
        this.setExtendedState(frminicio.MAXIMIZED_BOTH);
        this.setTitle("Sistema de Registro de Infracciones de Tránsito RIT");
        setIconImage(new ImageIcon(getClass().getResource("../Files/logo.png")).getImage());
        lblidpersona.setVisible(false);
        lblnombre.setVisible(false);
        lblapellido.setVisible(false);
        lblacceso.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        lblidpersona = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lblapellido = new javax.swing.JLabel();
        lblacceso = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuarchivo = new javax.swing.JMenu();
        submenuinfracciones = new javax.swing.JMenuItem();
        mnuregistros = new javax.swing.JMenu();
        submenuregistrosinfracciones = new javax.swing.JMenuItem();
        submenuinfractores = new javax.swing.JMenuItem();
        mnuconfiguraciones = new javax.swing.JMenu();
        submenusuarios = new javax.swing.JMenuItem();
        mnusalir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(0, 102, 153));

        lblidpersona.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblidpersona.setText("jLabel1");
        escritorio.add(lblidpersona);
        lblidpersona.setBounds(20, 30, 130, 20);

        lblnombre.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblnombre.setText("jLabel2");
        escritorio.add(lblnombre);
        lblnombre.setBounds(20, 80, 130, 20);

        lblapellido.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblapellido.setText("jLabel3");
        escritorio.add(lblapellido);
        lblapellido.setBounds(20, 130, 130, 20);

        lblacceso.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblacceso.setText("jLabel4");
        escritorio.add(lblacceso);
        lblacceso.setBounds(20, 190, 130, 20);

        menuBar.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N

        mnuarchivo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuarchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/archivo.png"))); // NOI18N
        mnuarchivo.setMnemonic('e');
        mnuarchivo.setText("Archivo");
        mnuarchivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        submenuinfracciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        submenuinfracciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submenuinfracciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/infracciones.jpg"))); // NOI18N
        submenuinfracciones.setMnemonic('t');
        submenuinfracciones.setText("Infracciones");
        submenuinfracciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuinfraccionesActionPerformed(evt);
            }
        });
        mnuarchivo.add(submenuinfracciones);

        menuBar.add(mnuarchivo);

        mnuregistros.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuregistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/registros1.png"))); // NOI18N
        mnuregistros.setMnemonic('h');
        mnuregistros.setText("Registros");
        mnuregistros.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        submenuregistrosinfracciones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        submenuregistrosinfracciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submenuregistrosinfracciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/reginfracciones.png"))); // NOI18N
        submenuregistrosinfracciones.setMnemonic('c');
        submenuregistrosinfracciones.setText("Registros de Infracciones");
        submenuregistrosinfracciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuregistrosinfraccionesActionPerformed(evt);
            }
        });
        mnuregistros.add(submenuregistrosinfracciones);

        submenuinfractores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        submenuinfractores.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submenuinfractores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/infractor.png"))); // NOI18N
        submenuinfractores.setMnemonic('a');
        submenuinfractores.setText("Infractores");
        submenuinfractores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenuinfractoresActionPerformed(evt);
            }
        });
        mnuregistros.add(submenuinfractores);

        menuBar.add(mnuregistros);

        mnuconfiguraciones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnuconfiguraciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/configuraciones.png"))); // NOI18N
        mnuconfiguraciones.setText("Configuraciones");
        mnuconfiguraciones.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        submenusuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        submenusuarios.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        submenusuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/acceso.png"))); // NOI18N
        submenusuarios.setText("Usuarios y Accesos");
        submenusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submenusuariosActionPerformed(evt);
            }
        });
        mnuconfiguraciones.add(submenusuarios);

        menuBar.add(mnuconfiguraciones);

        mnusalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        mnusalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/salir1.png"))); // NOI18N
        mnusalir.setText("Salir");
        mnusalir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mnusalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnusalirMouseClicked(evt);
            }
        });
        menuBar.add(mnusalir);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submenuinfraccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuinfraccionesActionPerformed
        // Creo un objeto que haga instancia a frminfraccion
        frminfraccion form = new frminfraccion();
        //Agrego al objeto al escritorio al frente y lo hago visible
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        form.setLocation(120,70);
             
                
        
    }//GEN-LAST:event_submenuinfraccionesActionPerformed

    private void submenuinfractoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuinfractoresActionPerformed
        // Creo un objeto que haga instancia a frminfractor
        frminfractor form = new frminfractor();
        //Agrego al objeto al escritorio al frente y lo hago visible
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        form.setLocation(70,70);
    }//GEN-LAST:event_submenuinfractoresActionPerformed

    private void submenuregistrosinfraccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenuregistrosinfraccionesActionPerformed
        // Creo un objeto que haga instancia a frmregistro
        frmregistro form = new frmregistro();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        //lblpersona guarda la persona que esta iniciando sesion y esto envio al txtidempleado
        frmregistro.txtidempleado.setText(lblidpersona.getText());
        frmregistro.txtempleado.setText(lblnombre.getText() + " " + lblapellido.getText());
        //Para saber que usuario edita los registros
        frmregistro.idusuario=Integer.parseInt(lblidpersona.getText());
        form.setLocation(70,40);
        
    }//GEN-LAST:event_submenuregistrosinfraccionesActionPerformed

    private void submenusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submenusuariosActionPerformed
        // Creo un objeto que haga instancia a frmempleado
        frmempleado form = new frmempleado();
        //Agrego al objeto al escritorio al frente y lo hago visible
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        form.setLocation(40,20);
    }//GEN-LAST:event_submenusuariosActionPerformed

    private void mnusalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnusalirMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_mnusalirMouseClicked

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
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frminicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frminicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane escritorio;
    public static javax.swing.JLabel lblacceso;
    public static javax.swing.JLabel lblapellido;
    public static javax.swing.JLabel lblidpersona;
    public static javax.swing.JLabel lblnombre;
    private javax.swing.JMenuBar menuBar;
    public static javax.swing.JMenu mnuarchivo;
    public static javax.swing.JMenu mnuconfiguraciones;
    private javax.swing.JMenu mnuregistros;
    private javax.swing.JMenu mnusalir;
    private javax.swing.JMenuItem submenuinfracciones;
    private javax.swing.JMenuItem submenuinfractores;
    private javax.swing.JMenuItem submenuregistrosinfracciones;
    private javax.swing.JMenuItem submenusuarios;
    // End of variables declaration//GEN-END:variables

}