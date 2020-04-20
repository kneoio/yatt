package com.semantyca.yatt.model.system;

import com.semantyca.yatt.model.IUser;

import javax.security.auth.Subject;

public abstract class SystemUser implements IUser {

    @Override
    public boolean isAuthorized() {
        return true;
    }

    @Override
    public void setAuthorized(boolean isAuthorized) {

    }

    @Override
    public abstract String getLogin();

    @Override
    public void setLogin(String string) {

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
    public abstract String getName();

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

}
