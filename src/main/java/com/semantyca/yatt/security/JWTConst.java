package com.semantyca.yatt.security;


final class JWTConst {
    static final String JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf";
    static final String TOKEN_HEADER = "Authorization";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String TOKEN_TYPE = "JWT";
    static final String TOKEN_ISSUER = "secure-api";
    static final String TOKEN_AUDIENCE = "secure-app";

    private JWTConst() {
        throw new IllegalStateException("Cannot create instance of static util class");
    }
}

