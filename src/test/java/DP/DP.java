package DP;

import TestGen.TextGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class DP {

    private static Map<String, String> testData;
    private static ResourceBundle resourceBundle;

    private static ResourceBundle getSingleResourceBundle() {
        if (resourceBundle == null) {
            resourceBundle = ResourceBundle.getBundle("mail_config");
        }
        return resourceBundle;
    }

    public static Map<String, String> initTestData() {
        testData = new HashMap<>();
        ResourceBundle rb = getSingleResourceBundle();
        testData.put("login", rb.getString("mail.login"));
        testData.put("domain", rb.getString("mail.domain"));
        testData.put("password", rb.getString("mail.password"));
        testData.put("subject", TextGenerator.getRandomString(7));
        testData.put("body", TextGenerator.getRandomString(25));
        return testData;
    }

    public static String get(String key) {
        if (testData == null) {
            initTestData();
        }
        if (testData.containsKey(key)) {
            return testData.get(key);
        } else {
            System.out.println("Test Data Provider doesn't contain '" + key + "'");
        }
        return "";
    }

    public static void add(String key, String value) {
        if (testData == null) {
            initTestData();
        }
        if (testData.containsKey(key)) {
            System.out.println("Test Data Provider has already contain '" + key + "' key");
            return;
        }
        testData.put(key, value);
    }

}

