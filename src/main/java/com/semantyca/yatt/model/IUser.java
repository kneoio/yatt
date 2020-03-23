package com.semantyca.yatt.model;

import java.security.Principal;
import java.time.ZonedDateTime;


public interface IUser extends Principal {

    String getLogin();

    void setLogin(String string);

    boolean isAuthorized();

    void setAuthorized(boolean isAuthorized);

    void setEditable(boolean b);

    String getEmail();

    void setEmail(String value);

    void setRegDate(ZonedDateTime date);

}
