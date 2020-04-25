package com.semantyca.yatt.model;

import java.security.Principal;
import java.util.List;


public interface IUser extends Principal {

    String getLogin();

    void setLogin(String string);

    String getPwd();

    List<String> getRoles();

    boolean isAuthorized();

    void setAuthorized(boolean isAuthorized);

    String getEmail();

    void setEmail(String value);


}
