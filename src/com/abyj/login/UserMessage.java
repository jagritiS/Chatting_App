package com.abyj.login;

public class UserMessage
{
    private String username;
    private String message;

    private boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String password) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

}
