package com.semantyca.yatt.model;

import java.util.Date;

public class User extends AppEntity<Integer> implements IUser{
    private String login;
    private String email;
    private boolean authorized;

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean isAuthorized() {
        return authorized;
    }

    @Override
    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    @Override
    public void setEditable(boolean b) {

    }

    @Override
    public String getName() {
        return login;
    }

    public Date getLastPasswordResetDate() {
        return new Date();
    }
}
