package buisnessObjects;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Mail {
    private static String mailAddressee;
    private static String mailTopic;
    private static String mailContent;
    private static Mail mail;
    private static ResourceBundle rb;

    private Mail() {
    }

    public static Mail getMail() {
        if (mail == null) {
            mail = new Mail();
            rb = ResourceBundle.getBundle("mail_config");
            mailAddressee = rb.getString("mail.login") + "@" + rb.getString("mail.domain");
            mailTopic = rb.getString("mail.topic");
            mailContent = setMailContent();
        }
        return mail;
    }

    public static String setMailContent() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        mailContent = LocalDateTime.now().toString();
        return mailContent;
    }

    public static String getMailContent() {

        return mailContent;
    }

    public static String getMailAddressee() {
        return mailAddressee;
    }

    public static String getMailTopic() {
        return mailTopic;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
