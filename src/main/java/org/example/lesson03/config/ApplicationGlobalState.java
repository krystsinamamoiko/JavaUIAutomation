package org.example.lesson03.config;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private final String BASE_URL = "http://automationpractice.com/index.php";
    private final String EMAIL = "krystsina.mamoiko@gmail.co";
    private final String PASSWORD = "aA1111111#";
    private final String USERNAME = "Krystsina Mamoiko";
    private static String VALID_ADDRESS = "242 Gentle Trace";
    private static String VALID_PHONE = "276-151-9740";
    private static String VALID_POSTCODE = "50385";
    private static String VALID_CITY = "Los Angeles";
    private final long IMPLICIT_WAIT_SEC = 3;
    private final long EXPLICIT_WAIT_SEC = 5;

    private ApplicationGlobalState() {
    }

    // It is not thread safety code (it is used just to simplify implementation)
    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    public String getBaseUrl() {
        return this.BASE_URL;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getEmail() {
        return EMAIL;
    }

    public static String getValidAddress() {
        return VALID_ADDRESS;
    }

    public static String getValidPhone() {
        return VALID_PHONE;
    }

    public static String getValidPostcode() {
        return VALID_POSTCODE;
    }

    public static String getValidCity() {
        return VALID_CITY;
    }

    public long getImplicitWait() {
        return IMPLICIT_WAIT_SEC;
    }

    public long getExplicitWait() {
        return EXPLICIT_WAIT_SEC;
    }
}
