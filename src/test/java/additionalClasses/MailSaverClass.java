package additionalClasses;


import buisnessObjects.Mail;

import java.util.HashMap;

public class MailSaverClass {

    private static HashMap<String, Object> mailBuffer;

    public static HashMap<String, Object> getMailBuffer() {
        if (null == mailBuffer) {
            mailBuffer = new HashMap<String, Object>();
        }

        return mailBuffer;
    }

    public static Object getMailSubjectAndBody() {
        return ((Mail)mailBuffer.get("MailForComparsion")).getMailTopicAndContent();
    }

    public static Object getMailAddressee() {
        return ((Mail)mailBuffer.get("MailForComparsion")).getMailAddressee();
    }

    public static void saveAsMailForComparsion(String subjectAndBody, String addressee) {
        mailBuffer.put("MailForComparsion", new Mail(addressee,subjectAndBody));
    }

    public static void clearBuffer() {
        mailBuffer.remove("MailForComparsion");
        mailBuffer = null;
    }

}
