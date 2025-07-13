/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifro.calama.votacaofeedback.service;

import br.edu.ifro.calama.votacaofeedback.util.ConfigLoaderUtil;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailService {

    public void enviarEmailDeRecuperacao(String emailDestino, String token) throws Exception {
        System.out.println("Iniciando envio de e-mail real para: " + emailDestino);

        final Properties props = ConfigLoaderUtil.getProperties();
        final String meuEmail = props.getProperty("mail.from.user");
        final String minhaSenhaApp = props.getProperty("mail.from.password");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(meuEmail, minhaSenhaApp);
            }
        });

        // --- ATIVANDO O LOG DETALHADO ---
        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(meuEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
        message.setSubject("OctaCore - Código de Recuperação de Senha");
        
        String corpoDoEmail = "Olá,\n\nVocê solicitou a recuperação de senha para o sistema OctaCore.\n\n" +
                              "Seu código de verificação é: " + token + "\n\n" +
                              "Este código irá expirar em 15 minutos.\n\n" +
                              "Se você não solicitou isso, por favor, ignore este e-mail.";
        message.setText(corpoDoEmail);

        Transport.send(message);
        
        System.out.println("E-mail enviado com sucesso!");
    }
}
