package additionalClasses;


import java.util.HashMap;

public class MailSaverClass {

    private static HashMap<String, String> mailBuffer;

    public static HashMap<String, String> getMailBuffer() {
        if (null == mailBuffer) {
            mailBuffer = new HashMap<String, String>();
        }

        return mailBuffer;
    }

    public static String getMailSubjectAndBody() {
        return mailBuffer.get("SubjectAndBody");
    }

    public static String getMailAddressee() {
        return mailBuffer.get("Addressee");
    }

    public static void saveMailInBuffer(String subjectAndBody, String addressee) {
        mailBuffer.put("SubjectAndBody", subjectAndBody);
        mailBuffer.put("Addressee", addressee);
    }

    public static void clearBuffer() {
        mailBuffer.remove("Addressee");
        mailBuffer.remove("SubjectAndBody");
    }

}
