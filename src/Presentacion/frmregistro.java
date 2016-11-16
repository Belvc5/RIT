/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Datos.Correo;
import Datos.Registro;
import Logica.ControladorCorreo;
import Logica.FunRegistro;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Belen
 */
public class frmregistro extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmregistro
     */
    public frmregistro() {
        initComponents();
        mostrar("");
        inhabilitar();
    }
    
    //Determina que accion se va a ejecutar
    private String accion = "guardar";
    public static int idusuario;
    
    //Oculto las colunmas idregistro,idinfraccion,idinfractor,idempleado de mi tabla
    void ocultar_columnas(){
      tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
      tablalistado.getColumnModel().getColumn(0).setMinWidth(0); 
      tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
      
      tablalistado.getColumnModel().getColumn(4).setMaxWidth(0);
      tablalistado.getColumnModel().getColumn(4).setMinWidth(0); 
      tablalistado.getColumnModel().getColumn(4).setPreferredWidth(0);
      
      tablalistado.getColumnModel().getColumn(8).setMaxWidth(0);
      tablalistado.getColumnModel().getColumn(8).setMinWidth(0); 
      tablalistado.getColumnModel().getColumn(8).setPreferredWidth(0);
      
      tablalistado.getColumnModel().getColumn(12).setMaxWidth(0);
      tablalistado.getColumnModel().getColumn(12).setMinWidth(0); 
      tablalistado.getColumnModel().getColumn(12).setPreferredWidth(0);
      
      tablalistado.getColumnModel().getColumn(11).setMaxWidth(0);
      tablalistado.getColumnModel().getColumn(11).setMinWidth(0); 
      tablalistado.getColumnModel().getColumn(11).setPreferredWidth(0);
      
    }

    void inhabilitar() {
        //No se visualice
        txtidregistro.setVisible(false);
        txtidinfraccion.setVisible(false);
        txtidinfractor.setVisible(false);
        txtidempleado.setVisible(false); 
        txtcorreo.setVisible(false);
        txtplaca.setVisible(false);
        //Deshabilito las cajas de texto y combo box     
        dcfecha.setEnabled(false);
        txthora.setEnabled(false);
        txtlugar.setEnabled(false);
        txttipo_infraccion.setEnabled(false);
        txtdescripcion.setEnabled(false);
        txtmulta.setEnabled(false);
        txtinfractor.setEnabled(false);
        txtempleado.setEnabled(false);
        cbestado.setEnabled(false);
        txtcorreo.setEnabled(false);
        txtplaca.setEnabled(false);
        //Botones
        btnguardar.setEnabled(false);
        btncancelar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnbusca_infraccion.setEnabled(false);
        btnbusca_infractor.setEnabled(false);
        //Dejo en blanco mis cajas de texto
        txtidregistro.setText("");
        txtidinfractor.setText("");
        txtidinfraccion.setText("");
        txtidempleado.setText("");
        txttipo_infraccion.setText("");
        txtinfractor.setText("");
        txtlugar.setText("");        
        txthora.setText("");
        txtdescripcion.setText("");        
        txtmulta.setText("");
        txtcorreo.setText("");
        txtplaca.setText("");
        
    }

    void habilitar() {
        //No se visualice
        txtidregistro.setVisible(false);
        txtidinfraccion.setVisible(false);
        txtidinfractor.setVisible(false);
        txtidempleado.setVisible(false);   
        txtcorreo.setVisible(false);
        txtplaca.setVisible(false);
        //Deshabilito las cajas de texto y combo box     
        dcfecha.setEnabled(true);
        txttipo_infraccion.setEnabled(false);
        txtdescripcion.setEnabled(false);
        txtmulta.setEnabled(false);
        txtinfractor.setEnabled(false);
        txtempleado.setEnabled(false);
        txtlugar.setEnabled(true);
        txthora.setEnabled(true);
        cbestado.setEnabled(true);
        txtcorreo.setEnabled(false);
        txtplaca.setEnabled(false);
        //Botones
        btnguardar.setEnabled(true);
        btncancelar.setEnabled(true);
        btneliminar.setEnabled(true);
        btnbusca_infraccion.setEnabled(true);
        btnbusca_infractor.setEnabled(true);
        //Dejo en blanco mis cajas de texto
        txtidregistro.setText("");
        txtidinfractor.setText("");
        txtidinfraccion.setText("");
        txtidempleado.setText("");
        txttipo_infraccion.setText("");
        txtinfractor.setText("");
        txtlugar.setText("");
        txthora.setText("");
        txtdescripcion.setText("");        
        txtmulta.setText("");
        txtcorreo.setText("");
        txtplaca.setText("");
    }
    
    void mostrar(String buscar){
        try {
            //Instancio a su funcion mostrar de FunRegistro
            DefaultTableModel modelo;
            FunRegistro func = new FunRegistro();
           //Almaceno el resultado de la funcion
            modelo =func.mostrar(buscar);
             //llamo al evento setText
            tablalistado.setModel(modelo);
            ocultar_columnas();
            lbltotalregistros.setText("Total Registros "+ Integer.toString(func.totalregistros));
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    
    public void enviarCorreo(){
        Correo c = new Correo();
        ControladorCorreo co = new ControladorCorreo();
        c.setContrasenia("zimkocbfwaxhfefo");
        c.setUsuarioCorreo("registroinfraccionestransito@gmail.com");
        c.setAsunto("RIT : Nueva Infracción");
        c.setMensaje("Se ha registrado una nueva infracción de tránsito del vehículo con placa " + txtplaca.getText() + " preteneciente a Sr./a " + txtinfractor.getText()+
                " con fecha " + dcfecha.getDate()+ " a la/as " + txthora.getText()  + " en " + txtlugar.getText() + ".\n\nUd a cometido un/a " + txttipo_infraccion.getText() +  ", que según las leyes ecuatorianas de tránsito vigentes dice: "
                + txtdescripcion.getText() + "; dicha ley estipula el pago de " + txtmulta.getText());
        c.setDestino(txtcorreo.getText());
        c.setNombreArchivo("infracciones_transito.png");
        c.setRutaArchivo("infracciones_transito.png");
        //Enviar lo mandamos 
        
        if (co.enviarCorreo(c)) {
            JOptionPane.showMessageDialog(null, "Infracción Enviada");
            mostrar("");
        }else{
            try {
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error" + e);
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtidinfraccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtidregistro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtlugar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txttipo_infraccion = new javax.swing.JTextField();
        txtidinfractor = new javax.swing.JTextField();
        txtinfractor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtidempleado = new javax.swing.JTextField();
        txtempleado = new javax.swing.JTextField();
        cbestado = new javax.swing.JComboBox<>();
        btnbusca_infraccion = new javax.swing.JButton();
        btnbusca_infractor = new javax.swing.JButton();
        dcfecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtdescripcion = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtmulta = new javax.swing.JTextArea();
        txtcorreo = new javax.swing.JTextField();
        txtplaca = new javax.swing.JTextField();
        txthora = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        lbltotalregistros = new javax.swing.JLabel();
        btneliminar = new javax.swing.JButton();
        btnenviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Registro Infracciones");

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registros de Infracciones"));

        txtidinfraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidinfraccionActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Infracción:");

        txtidregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidregistroActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Fecha:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Infractor:");

        txtlugar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtlugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtlugarActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Estado Registro:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Lugar:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Hora:");

        btnuevo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/nuevo.png"))); // NOI18N
        btnuevo.setText("Nuevo");
        btnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnuevoActionPerformed(evt);
            }
        });

        btnguardar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/guardar.png"))); // NOI18N
        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/cancelar.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        txttipo_infraccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txttipo_infraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttipo_infraccionActionPerformed(evt);
            }
        });

        txtidinfractor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidinfractorActionPerformed(evt);
            }
        });

        txtinfractor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtinfractor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtinfractorActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Empleado:");

        txtidempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidempleadoActionPerformed(evt);
            }
        });

        txtempleado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtempleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtempleadoActionPerformed(evt);
            }
        });

        cbestado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Guardada", "Enviada", " " }));

        btnbusca_infraccion.setText("...");
        btnbusca_infraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusca_infraccionActionPerformed(evt);
            }
        });

        btnbusca_infractor.setText("...");
        btnbusca_infractor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusca_infractorActionPerformed(evt);
            }
        });

        dcfecha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Descripción:");

        txtdescripcion.setColumns(20);
        txtdescripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtdescripcion.setRows(5);
        jScrollPane1.setViewportView(txtdescripcion);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Multa:");

        txtmulta.setColumns(20);
        txtmulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtmulta.setRows(5);
        jScrollPane3.setViewportView(txtmulta);

        txthora.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(15, 15, 15)
                        .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtidempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jLabel11)))
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtinfractor)
                                                    .addComponent(txtempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnbusca_infractor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtplaca, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                                                    .addComponent(txtcorreo)))))
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtidinfraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtidinfractor, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txttipo_infraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnbusca_infraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txthora, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dcfecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(359, 359, 359)
                        .addComponent(txtidregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnuevo)
                        .addGap(91, 91, 91)
                        .addComponent(btnguardar)
                        .addGap(87, 87, 87)
                        .addComponent(btncancelar)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtidregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtlugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtidinfraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txttipo_infraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbusca_infraccion))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(54, 54, 54)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtinfractor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbusca_infractor)
                            .addComponent(jLabel4)
                            .addComponent(txtidinfractor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtidempleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jLabel3))
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnuevo)
                    .addComponent(btnguardar)
                    .addComponent(btncancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listado de Registros"));

        tablalistado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tablalistado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablalistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablalistadoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablalistado);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Buscar:");

        txtbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/buscar.png"))); // NOI18N
        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/salir.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        lbltotalregistros.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbltotalregistros.setText("Registros:");

        btneliminar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/eliminar.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnenviar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnenviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/enviar.png"))); // NOI18N
        btnenviar.setText("Enviar");
        btnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel10)
                .addGap(37, 37, 37)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(btnbuscar)
                .addGap(33, 33, 33)
                .addComponent(btneliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addComponent(btnenviar)
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsalir)
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lbltotalregistros)
                        .addGap(264, 264, 264))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnenviar))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lbltotalregistros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMouseClicked
        // Editamos una infraccion de la tabla
        btnguardar.setText("Editar");
        //habilito las cajas de texto
        habilitar();
        //Habilito mi boton eliminar
        btneliminar.setEnabled(true);
        accion="editar";

        //Almaceno la fila donde hago clcik de la tabla listado
        int fila = tablalistado.rowAtPoint(evt.getPoint());
        //Obtengo los datos de la columna de la tabla
        txtidregistro.setText(tablalistado.getValueAt(fila, 0).toString());
        dcfecha.setDate(Date.valueOf(tablalistado.getValueAt(fila, 1).toString()));
        txthora.setText(tablalistado.getValueAt(fila, 2).toString());
        txtlugar.setText(tablalistado.getValueAt(fila, 3).toString());
        txtidinfraccion.setText(tablalistado.getValueAt(fila, 4).toString());
        txttipo_infraccion.setText(tablalistado.getValueAt(fila, 5).toString());
        txtdescripcion.setText(tablalistado.getValueAt(fila, 6).toString());
        txtmulta.setText(tablalistado.getValueAt(fila, 7).toString());
        txtidinfractor.setText(tablalistado.getValueAt(fila, 8).toString());
        txtinfractor.setText(tablalistado.getValueAt(fila, 9).toString());
        txtcorreo.setText(tablalistado.getValueAt(fila, 10).toString());
        txtplaca.setText(tablalistado.getValueAt(fila, 11).toString());
        txtidempleado.setText(tablalistado.getValueAt(fila, 12).toString());
        txtempleado.setText(tablalistado.getValueAt(fila, 13).toString());         
        cbestado.setSelectedItem(tablalistado.getValueAt(fila, 14).toString());
        
        
              
    }//GEN-LAST:event_tablalistadoMouseClicked

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        mostrar(txtbuscar.getText());

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnsalirActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // Verifico que mi caja de texto no este en blanco
        if (!txtidregistro.getText().equals("")) {
            int confirmacion = JOptionPane.showConfirmDialog(rootPane, "Esta seguro de eliminar el Registro de Infracción?","Confirmar",2);
            if(confirmacion == 0){
                Registro dts = new Registro();
                FunRegistro func = new FunRegistro();
                //Envio a mi dts el idinfraccion
                dts.setIdregistro(Integer.parseInt(txtidregistro.getText()));
                //Llamo a mi funcion eliminar
                func.eliminar(dts);
                //metodo mostrar para ver todos
                mostrar("");
                //llamo al procedimiento inhabilitar
                inhabilitar();

            }
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnbusca_infractorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusca_infractorActionPerformed
        // Instancio mi frmvistainfractor
        frmvistainfractor form = new frmvistainfractor();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbusca_infractorActionPerformed

    private void btnbusca_infraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusca_infraccionActionPerformed
        // Instancio mi frmvistainfraccion
        frmvistainfraccion form = new frmvistainfraccion();
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_btnbusca_infraccionActionPerformed

    private void txtempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtempleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtempleadoActionPerformed

    private void txtidempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidempleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidempleadoActionPerformed

    private void txtinfractorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtinfractorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtinfractorActionPerformed

    private void txtidinfractorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidinfractorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidinfractorActionPerformed

    private void txttipo_infraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttipo_infraccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttipo_infraccionActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // Validamos que las cajas de texto esten llenas
        if (txtidinfraccion.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debe seleccionar una Infraccion");
            txtidinfraccion.requestFocus();
            return;
        }
        if (txtidinfractor.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debe seleccionar un Infractor");
            txtidinfractor.requestFocus();
            return;
        }
        if (txtlugar.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debe ingresar un lugar o dirección donde se cometio la Infracción");
            txtlugar.requestFocus();
            return;
        }
        if (txthora.getText().length() == 0) {
            JOptionPane.showConfirmDialog(rootPane, "Debe ingresar la hora de la Infracción");
            txthora.requestFocus();
            return;
        }

        //Instancio la clase Infraccion para crear un nuevo objeto
        //Instancio la clase FunInfraccion para llamar a todas las funciones
        Registro dts = new Registro();
        FunRegistro func = new FunRegistro();
        //Envio los datos uno a uno a la tabla registro
        //Importo mi libreira calendar para agregar la fecha
        Calendar cal;
        int d,m,a;
        //Obtengo la fecha seleccionada
        cal=dcfecha.getCalendar();
        d=cal.get(Calendar.DAY_OF_MONTH);
        m=cal.get(Calendar.MONTH);
        a=cal.get(Calendar.YEAR)-1900; //Tener un formato correcto
        dts.setFecha(new Date(a,m,d));
        
        dts.setHora(txthora.getText());
        dts.setLugar(txtlugar.getText());
        
        dts.setIdinfraccion(Integer.parseInt(txtidinfraccion.getText()));
        dts.setIdinfractor(Integer.parseInt(txtidinfractor.getText()));
        //Creo una variable idusuario para no tener problemas en la edicion d los datos
        dts.setIdempleado(idusuario);
        //Combo box
        int seleccionado = cbestado.getSelectedIndex();
        dts.setEstado((String)cbestado.getItemAt(seleccionado));

        //Guardar o Editar registro
        if (accion.equals("guardar")) {
            //Inserto los datos
            if (func.insertar(dts)) {
                JOptionPane.showMessageDialog(rootPane, "El Registro de Infracción fue guardado exitosamente");
                //muestro todos los datos
                mostrar("");
                inhabilitar();
                               
                
            }
        }

        else if(accion.equals("editar")){
            //Agrego el setIdempleado para saber que empleado esta editando el registro
            dts.setIdregistro(Integer.parseInt(txtidregistro.getText()));
            dts.setIdempleado(Integer.parseInt(txtidempleado.getText()));
            if(func.editar(dts)){
                JOptionPane.showMessageDialog(rootPane, "Se edito el Registro de Infracción exitosamente");
                //muestro todos los datos
                mostrar("");
                inhabilitar();
            }
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnuevoActionPerformed
        // TODO add your handling code here:
        habilitar();
        btnguardar.setText("Guardar");
        accion = "guardar";
    }//GEN-LAST:event_btnuevoActionPerformed

    private void txtlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtlugarActionPerformed
        // TODO add your handling code here:
        txtlugar.transferFocus();
    }//GEN-LAST:event_txtlugarActionPerformed

    private void txtidregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidregistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidregistroActionPerformed

    private void txtidinfraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidinfraccionActionPerformed
        // TODO add your handling code here:
        txtidinfraccion.transferFocus();
    }//GEN-LAST:event_txtidinfraccionActionPerformed

    private void btnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenviarActionPerformed
        // TODO add your handling code here:
        Registro dts2 = new Registro();
        FunRegistro fun2 = new FunRegistro();
        dts2.setIdregistro(Integer.parseInt(txtidregistro.getText()));
        fun2.enviado(dts2);
        enviarCorreo();
        inhabilitar();
        
    }//GEN-LAST:event_btnenviarActionPerformed

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
            java.util.logging.Logger.getLogger(frmregistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmregistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmregistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmregistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmregistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbusca_infraccion;
    private javax.swing.JButton btnbusca_infractor;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnenviar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnuevo;
    private javax.swing.JComboBox<String> cbestado;
    private com.toedter.calendar.JDateChooser dcfecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbltotalregistros;
    private javax.swing.JTable tablalistado;
    private javax.swing.JTextField txtbuscar;
    public static javax.swing.JTextField txtcorreo;
    public static javax.swing.JTextArea txtdescripcion;
    public static javax.swing.JTextField txtempleado;
    private javax.swing.JTextField txthora;
    public static javax.swing.JTextField txtidempleado;
    public static javax.swing.JTextField txtidinfraccion;
    public static javax.swing.JTextField txtidinfractor;
    private javax.swing.JTextField txtidregistro;
    public static javax.swing.JTextField txtinfractor;
    private javax.swing.JTextField txtlugar;
    public static javax.swing.JTextArea txtmulta;
    public static javax.swing.JTextField txtplaca;
    public static javax.swing.JTextField txttipo_infraccion;
    // End of variables declaration//GEN-END:variables
}
