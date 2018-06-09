package br.gov.to.secad.main.util;

/**
 * Created by thaniel.alves on 03/11/2015.
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 *  * Classe Utilitária que contém métodos para envio de Email através de conta
 * do  * Gmail
  
 */
//Anotações mecessárias para configuração do sistema de
//tarefas agendadas do Spring (@Scheduled)
public class EnviaEmailUtil {

    /**
     * * Construtor sem parametros, ao ser chamado já instancia as
     * configuraççoes de email do Gmail na JVM
     *
     */
    public EnviaEmailUtil() {

        ajustaParametros();
    }

    /**
     * Variavel local para Sessao
     */
    Session session = null;
    String username = "sistemas.secad@gmail.com";
    String senha = "portaldoservidor#atendimento@secad";

    //Teste de envio autmomático de e-mail. Descomentar e setar o horário.
    /*a ordem dos parâmetros:
    1 - Segundos;
    2 - Minutos;
    3 - Horas;
    4 - Dia do mês;
    5 - Mês;
    6 - Dia da semana;
    7 - Ano (opcional).*/
 /*@Scheduled(cron = "0 38 18 * * *")*/
    public void enviarEmailAutomatico() {
        try {
            EnviaEmailUtil ee = new EnviaEmailUtil();
            String[] emails = {"thanielalves@gmail.com"};
            ee.enviarEmailHtml("teste", emails, "Classe de Envio de Notificação do AEDE!");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * * Metodo para envio de mensagem com texto simples
     */
    public void enviarEmail(String remetente, String[] destinatarios, String assunto, String conteudo) throws Exception {

        try {

            Message message = new MimeMessage(session);

            //Configura o Remetente da mensagem
            message.setFrom(new InternetAddress(remetente));

            Address[] toUser = new Address[0];

            //Configura o Destinatário da mensagem
            if (destinatarios != null && destinatarios.length > 0) {
                for (String destinatario : destinatarios) {
                    toUser = InternetAddress
                            .parse(destinatario);
                }
            }

            //Configura o Assunto da mensagem
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);

            //Configura o Conteudo da mensagem
            message.setText(conteudo);

            /**
             * Envia a mensagem criada
             */
            Transport.send(message);

            System.out.println("Email enviado com Sucesso; ");

        } catch (MessagingException e) {
            throw new Exception("Erro ao enviar email! \n" + e.getMessage());
        }
    }

    /**
     * * Metodo para envio de mensagem com texto html
     */
    public String enviarEmailHtml(String assunto, String[] destinatarios, String conteudoHtml) throws Exception {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));

            /*String[] destinatarios = {"naldopalmas@gmail.com","thanielalves@gmail.com","treedbox@gmail.com","wilkinson.wkn@gmail.com"};*/
            //String[] destinatarios = {"wilkinson.wkn@gmail.com"};
            Integer arrayLength = destinatarios.length;

            //Configura os destinatários
            InternetAddress[] enderecosDestinatarios = new InternetAddress[arrayLength];
            for (int i = 0; i < arrayLength; i++) {
                enderecosDestinatarios[i] = new InternetAddress(destinatarios[i]);
            }
            /* Address[] toUser = new Address[3];
            for(String destino : destinatarios) {
                for(int i =0;i<3;) {
                    toUser = InternetAddress.parse(destino);
                    i++;
                }
            }*/
            //TODO retirar essa linha antes de colocar em produção
            //destinatario = "thanielalves@gmail.com";

            /*Address[] toUser = InternetAddress.parse(destinatario);*/
            message.setRecipients(Message.RecipientType.TO, enderecosDestinatarios);
            message.setSubject(assunto);

            Multipart multipart = new MimeMultipart("related");
            BodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(conteudoHtml, "text/html");

            multipart.addBodyPart(htmlPart);

            message.setContent(multipart);

            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
            return "Sucesso";

        } catch (MessagingException e) {
            return e.toString();
            /*throw new Exception("Erro ao enviar email! \n" + e.getMessage());*/
        }

    }

    /**
     * Configura aa propriedades da JVM com parametros do servidor Gmail
     *
     */
    private void ajustaParametros() {

        Properties props = new Properties();

        /**
         * Conexão com servidor SECAD
         */
//        props.put("mail.smtp.ssl.trust", "*");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.smtp.host", "mail.secad.to.gov.br");
//        props.put("mail.smtp.port", "25");

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true"); // added this line

        /**
         * Associa autenticação a sessao de correio
         */
        session = Session.getDefaultInstance(props,
                new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, senha);
            }
        });
    }

}
