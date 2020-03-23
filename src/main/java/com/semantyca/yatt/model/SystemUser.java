package com.semantyca.yatt.model;

import javax.security.auth.Subject;
import java.time.ZonedDateTime;

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
    public void setRegDate(ZonedDateTime date) {

    }

    @Override
    public abstract String getName();

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

}
