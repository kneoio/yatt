package com.semantyca.yatt.security;

import org.pac4j.core.util.CommonHelper;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.config.signature.SignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.oidc.client.OidcClient;
import org.pac4j.oidc.config.OidcConfiguration;
import org.pac4j.oidc.profile.OidcProfile;
import org.pac4j.oidc.profile.OidcProfileDefinition;
import org.pac4j.oidc.profile.creator.OidcProfileCreator;

import java.util.ArrayList;
import java.util.List;

public class Auth0Client extends OidcClient<OidcConfiguration> {

    public Auth0Client(OidcConfiguration configuration) {
        super(configuration);
    }

    protected void clientInit() {
        CommonHelper.assertNotNull("configuration", this.getConfiguration());
        OidcProfileCreator profileCreator = new OidcProfileCreator(this.getConfiguration(), this);
        profileCreator.setProfileDefinition(new OidcProfileDefinition((x) -> {
            return new OidcProfile();
        }));
        final List<SignatureConfiguration> signatures = new ArrayList<>();
        signatures.add(new SecretSignatureConfiguration(JWTConst.JWT_SALT));
        JwtAuthenticator authenticator = new JwtAuthenticator(signatures);
        //defaultAuthenticator((Authenticator)authenticator);

        defaultProfileCreator(profileCreator);
        defaultLogoutActionBuilder(new Auth0LogoutBuilder());
        super.clientInit();
    }

}
