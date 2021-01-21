package org.arncar.keycloak.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RealmRepresentation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class KeycloakService {

    @ConfigProperty(name = "keycloak.admin.server-url")
    String serverUrl;
    @ConfigProperty(name = "keycloak.admin.realm")
    String realm;
    @ConfigProperty (name = "keycloak.admin.client-id")
    String clientId;
    @ConfigProperty(name = "keycloak.admin.username")
    String username;
    @ConfigProperty(name = "keycloak.admin.password")
    String password;

    Keycloak keycloak;

    @PostConstruct
    public void init() {
        keycloak = KeycloakBuilder.builder() //
                .serverUrl(serverUrl) //
                .realm(realm) //
                .clientId(clientId)
                .username(username)
                .password(password) //
                .build();
    }

    public List<RealmRepresentation> getRealms() {
        return keycloak.realms()
                .findAll();
    }

    public RealmRepresentation createRealm(String name) {
        final RealmRepresentation realmRepresentation = new RealmRepresentation();

        realmRepresentation.setDisplayName(name);
        keycloak.realms().create(realmRepresentation);

        return getRealm(name);
    }

    public RealmRepresentation getRealm(String name) {

        return keycloak.realms()
                .realm(name)
                .toRepresentation();
    }
}
