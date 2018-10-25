package buisnessObjects;

import java.util.ResourceBundle;

public class User {

    private static ResourceBundle rb;
    private static String userLogin;
    private static String userPassword;
    private static User user;

    public static String getMailLogin() {
        return userLogin;
    }

    public static String getMailPassword() {
        return userPassword;
    }

    private User() {
    }

    public static User getUser() {
        if (user == null) {
            user = new User();
            rb = ResourceBundle.getBundle("mail_config");
            userLogin = rb.getString("mail.login") + "@" + rb.getString("mail.domain");
            userPassword = rb.getString("mail.password");
        }
        return user;
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
        return "User{}";
    }

}
