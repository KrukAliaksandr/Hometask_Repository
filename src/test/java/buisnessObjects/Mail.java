package buisnessObjects;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Mail {
    private String mailAddressee;
    private String mailTopicAndContent;
    private ResourceBundle rb;

    public Mail(String mailAddressee,String mailTopicAndContent) {
        this.mailAddressee = mailAddressee;
        this.mailTopicAndContent = mailTopicAndContent;
    }

    public Mail(){
        this.mailAddressee = generateSelfAddressForMail();
        this.mailTopicAndContent= generateTopicAndContent();
    }

//    public static Mail getMail() {
//        if (mail == null) {
//            mail = new Mail();
//            rb = ResourceBundle.getBundle("configurationFiles/mail_config");
//            mailAddressee = rb.getString("mail.login") + "@" + rb.getString("mail.domain");
//            mailTopic = rb.getString("mail.topic");
//            mailContent = setMailContent();
//        }
//        return mail;
//    }

    private String generateTopicAndContent() {
        rb = ResourceBundle.getBundle("configurationFiles/mail_config");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return rb.getString("mail.topic")+LocalDateTime.now().toString();
    }

    private String generateSelfAddressForMail() {
        rb = ResourceBundle.getBundle("configurationFiles/mail_config");
        return  rb.getString("mail.login") + "@" + rb.getString("mail.domain");
    }

    public  void setMailAddressee(String mailAddressee) {
        this.mailAddressee = mailAddressee;
    }

    public  void setMailTopicAndContent(String mailTopicAndContent) {
        this.mailTopicAndContent = mailTopicAndContent;
    }

    public String getMailTopicAndContent() {

        return this.mailTopicAndContent;
    }

    public  String getMailAddressee() {
        return this.mailAddressee;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
