package com.semantyca.yatt.security;

import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.http.RedirectionAction;
import org.pac4j.core.exception.http.RedirectionActionHelper;
import org.pac4j.core.logout.GoogleLogoutActionBuilder;
import org.pac4j.core.logout.LogoutActionBuilder;
import org.pac4j.core.profile.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Auth0LogoutBuilder  implements LogoutActionBuilder {
    private static final Logger logger = LoggerFactory.getLogger(GoogleLogoutActionBuilder.class);

    public Optional<RedirectionAction> getLogoutAction(WebContext context, UserProfile currentProfile, String targetUrl) {
        String redirectUrl = "https://noisy.example.com:8443/mya/home";
        String logoutURL =  "https://semantyca.eu.auth0.com/v2/logout?client_id=TAsGHqLIearxwfnuyYYvVdE791JY4MLU&returnTo=" + redirectUrl;
        logger.info("logout through=" + logoutURL);
        return Optional.of(RedirectionActionHelper.buildRedirectUrlAction(context, logoutURL));
    }
}
