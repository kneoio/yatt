package com.semantyca.yatt.security;


import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.oidc.config.OidcConfiguration;
import org.pac4j.springframework.annotation.AnnotationConfig;
import org.pac4j.springframework.component.ComponentConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ComponentConfig.class, AnnotationConfig.class})
public class Pac4jConfig {

    @Bean
    public Config config() {
        final OidcConfiguration oidcConfiguration = new OidcConfiguration();
        oidcConfiguration.defaultDiscoveryURI("https://semantyca.eu.auth0.com/.well-known/openid-configuration");
        oidcConfiguration.setClientId("TAsGHqLIearxwfnuyYYvVdE791JY4MLU");
        oidcConfiguration.setSecret("ciFGVbGT-rZQ_pv1DY1vC15kDZpoqd22xPagUJDaVfGvrrkx4dhLTq-jzR6JP0lA");
        oidcConfiguration.setUseNonce(true);
        oidcConfiguration.setScope("openid email");
        oidcConfiguration.setResponseType("id_token");
        oidcConfiguration.setResponseMode("form_post");
        Auth0Client oidcClient = new Auth0Client(oidcConfiguration);
        String redirectURL = "https://noisy.example.com:8443/mya/callback";
        oidcClient.setName("Auth0Client");
        oidcClient.setCallbackUrl(redirectURL);
        final Clients clients = new Clients(oidcClient);
        return new Config(clients);
    }
}
