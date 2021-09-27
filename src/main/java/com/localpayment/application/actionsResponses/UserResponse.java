package com.localpayment.application.actionsResponses;

import com.localpayment.domain.entities.Card;
import com.localpayment.domain.entities.User;

public class UserResponse {

    private User user;
    private String message;
    private String status;

    public UserResponse() {

    }


    public UserResponse(User user, String message, String status) {
        this.user = user;
        this.message = message;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
