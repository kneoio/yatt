package com.semantyca.yatt.model;

import java.util.UUID;

public class User extends AppEntity<UUID> implements IUser{

    @Override
    public String getLogin() {
        return null;
    }

    @Override
    public void setLogin(String string) {

    }

    @Override
    public boolean isAuthorized() {
        return false;
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {

    }

    @Override
    public void setEditable(boolean b) {

    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail(String value) {

    }


    @Override
    public String getName() {
        return null;
    }
}
