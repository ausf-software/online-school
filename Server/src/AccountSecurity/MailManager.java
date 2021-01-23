package AccountSecurity;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailManager {

    protected String login;
    protected String password;
    protected String hostSMTP;
    protected String portSMTP;

    protected Session session;

    public MailManager(String log, String pas, String host, String port){

        login = log;
        password = pas;
        hostSMTP = host;
        portSMTP = port;

        Properties properties = new Properties();
        properties.put("mail.smtp.host", hostSMTP);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", portSMTP);
        properties.put("mail.smtp.aut", "true");
        properties.put("mail.smtp.user", login);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.auth", "true");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(login, password);
            }
        });

        System.out.println("Соединение с почтовиком установленно");
    }

    public void sentMail(String addressString, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            InternetAddress address = new InternetAddress(addressString);
            message.setFrom(new InternetAddress(login));
            message.setRecipient(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setText(text);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
