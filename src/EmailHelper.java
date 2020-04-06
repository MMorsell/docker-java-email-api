import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHelper {
public static void SendEmail(String subject, String textMessage) {
		
		final String username = SecretConverter.GetSecret("USERNAME");
        final String password = SecretConverter.GetSecret("VALUE");

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SecretConverter.GetSecret("USERNAME")));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(SecretConverter.GetSecret("MESSAGETOUSERNAME"))
            );
            message.setSubject(subject);
            message.setText(textMessage);

            Transport.send(message);
            
            System.out.println("");
            System.out.println("message sent");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
    public static void SendOkResponseToUser(BufferedWriter out, int httpCode) throws IOException{
        out.write(GenerateHttpCode(httpCode));
        out.newLine();
        out.write(GenerateSessionId());
        out.newLine();
        out.write("");
        out.newLine();
        out.write("Message sent");
        out.newLine();
        out.flush();
        out.close();
    }
    static String GenerateHttpCode(int number){
        if (number >= 200 && number < 300 ){
            return ("HTTP/1.1 "+ number + " OK");
        }
        return null;
    }

    static String GenerateSessionId(){
        return ("Set-Cookie: sessionId=" + GenerateGUID());
    }

    static UUID GenerateGUID(){
        return java.util.UUID.randomUUID();
    }
}
