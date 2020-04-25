package com.semantyca.yatt.model.system;


import java.util.List;

public class AnonymousUser extends SystemUser {
    public final static String USER_NAME = "anonymous";
    public final static int ID = 0;

    @Override
    public String getLogin() {
        return USER_NAME;
    }


    @Override
    public String getName() {
        return USER_NAME;
    }


}
