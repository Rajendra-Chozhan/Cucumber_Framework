package utilities;

import basepackage.BaseClass;
import java.io.File;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import utilities.Readconfigfile;

public class SendEmailWithReport extends BaseClass {

    public static void sendjavaemail(){

        Readconfigfile read = new Readconfigfile();

        String Email = read.getEmail();
        String Emailpwd = read.getEmailpwd();
        Properties props = new Properties();

        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Create session with Authentication
        jakarta.mail.Session session = jakarta.mail.Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(Email, Emailpwd);
            }
        });
        session.setDebug(true);

        try {
            // Create Email message
            jakarta.mail.Message message = new jakarta.mail.internet.MimeMessage(session);
            message.setFrom(new jakarta.mail.internet.InternetAddress(Email));
            message.setRecipients(jakarta.mail.Message.RecipientType.TO, jakarta.mail.internet.InternetAddress.parse(Email));
            message.setSubject("Test Email From QA Automation");

            // Email Body Part
            jakarta.mail.internet.MimeBodyPart textPart = new jakarta.mail.internet.MimeBodyPart();
            textPart.setText("Hello \n\n This is a test email from Java \n\n Regards,\nQA Team");

            // Attachment Part
            jakarta.mail.internet.MimeBodyPart attachmentPart = new jakarta.mail.internet.MimeBodyPart();
            String filePath =System.getProperty("user.dir") + "/target/cucumber-reports/report.html";

            //ExtentReports/SparkReport_ 15_Mar_25 19_07_49/Reports/Spark.html
            System.out.println("Attachment path is - "+filePath);
            attachmentPart.attachFile(new File(filePath));

            // Combine body and attachment parts
            jakarta.mail.internet.MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);

            // Send Email
            Transport.send(message);
            System.out.println("Email Sent Successfully ***");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }


