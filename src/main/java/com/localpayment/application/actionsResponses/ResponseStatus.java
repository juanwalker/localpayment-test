package com.localpayment.application.actionsResponses;

public enum ResponseStatus {
    OK("OK"),KO("KO");

    private String message;


    private ResponseStatus(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }


}
