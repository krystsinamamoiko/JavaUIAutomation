package org.example.lesson03.config;

public final class ApplicationGlobalState {

    private static ApplicationGlobalState INSTANCE;
    private final String TARGET_URL = "http://automationpractice.com";
    private final String EMAIL = "krystsina.mamoiko@gmail.com";
    private final String PASSWORD = "aA1111111#";
    private final String USERNAME = "Krystsina Mamoiko";
    private final long IMPLICIT_WAIT = 3;

    private ApplicationGlobalState() {
    }

    // It is not thread safety code (it is used just to simplify implementation)
    public static ApplicationGlobalState getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ApplicationGlobalState();
        }
        return INSTANCE;
    }

    public String getTargetUrl() {
        return this.TARGET_URL;
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

    public long getImplicitWait() {
        return IMPLICIT_WAIT;
    }
}
