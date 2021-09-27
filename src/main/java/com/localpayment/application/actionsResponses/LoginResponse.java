package com.localpayment.application.actionsResponses;

public class LoginResponse {
    String userName;
    String token;

    public LoginResponse() {

    }

    public LoginResponse(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
