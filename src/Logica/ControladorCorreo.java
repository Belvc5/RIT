/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Correo;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Belen
 */
//Logica de enviar el correo
public class ControladorCorreo {
    
    //Creamos un metodo para verificar el envio del correo
    public boolean enviarCorreo(Correo c){
        try {
            Properties p = new  Properties();
            //Envio el smtp del gmail
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.starttls.enable", "true");
            //EL puerto que va utilizar
            p.setProperty("mail.smtp.port", "587");
            //El usuario que va utilizar
            p.setProperty("mail.smtp.user", c.getUsuarioCorreo());
            p.setProperty("mail.smtp.auth", "true");
            
            //Vamos a iniciar una sesion
            Session s = Session.getDefaultInstance(p,null);
            
            //Vamos a realizar el Bodypart
            BodyPart texto = new MimeBodyPart();
            texto.setText(c.getMensaje());
            //Para ver el documento adjunto
            BodyPart adjunto = new MimeBodyPart();
            //Para ver si tiene un documento adjunto; si es diferente es porque ya tiene
            if (!c.getRutaArchivo().equals("")) {
                adjunto.setDataHandler(new DataHandler(new FileDataSource(c.getRutaArchivo())));
                adjunto.setFileName(c.getNombreArchivo());
            }
            
            MimeMultipart m = new MimeMultipart();
            m.addBodyPart(texto);
            
            if (!c.getRutaArchivo().equals("")) {
                m.addBodyPart(adjunto);   
            }
            //Al mensaje le enviamos la sesion
            MimeMessage mensaje = new MimeMessage(s);
            //Enviar para
            mensaje.setFrom(new InternetAddress(c.getUsuarioCorreo()));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(c.getDestino()));
            mensaje.setSubject(c.getAsunto());
            mensaje.setContent(m);
            
            Transport t = s.getTransport("smtp");
            t.connect(c.getUsuarioCorreo(),c.getContrasenia());
            t.sendMessage(mensaje, mensaje.getAllRecipients());
            t.close();
            return true;
                     
        } catch (Exception e) {
            System.out.println("Error" + e);
            return false;
        }
        
       
    }
}
