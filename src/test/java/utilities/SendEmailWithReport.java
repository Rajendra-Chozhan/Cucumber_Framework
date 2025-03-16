package utilities;

import basepackage.BaseClass;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.activation.*;

public class SendEmailWithReport extends BaseClass {

    public static void sendjavaemail(){


        // Set up the SMTP server settings
        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
//        props.put("mail.smtp.port", "25");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", "smtp.mail.yahoo.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl","true");
        props.put("mail.smtp.auth", "true");




        // Authenticate the email account
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rajchozhan024@yahoo.com", "Chols@135790");
            }
        });

        try {
            // Compose the email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajchozhan024@yahoo.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rajchozhan024@yahoo.com"));
            message.setSubject("Automated Test Report");

            // Email body text
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the attached test report.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the report
            messageBodyPart = new MimeBodyPart();
            String filename = "ExtentReports/"+timestamp()+"/HtmlReport/ExtentHtml.html";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Combine message parts
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
