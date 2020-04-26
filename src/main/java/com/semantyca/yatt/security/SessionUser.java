package com.semantyca.yatt.security;

public class SessionUser{
    int userId;
    String login;

    public SessionUser(int userId, String login) {
        this.userId = userId;
        this.login = login;
    }

    public int getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }
}
