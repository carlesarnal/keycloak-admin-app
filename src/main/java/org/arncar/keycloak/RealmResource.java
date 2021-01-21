package org.arncar.keycloak;


import org.arncar.keycloak.service.KeycloakService;
import org.keycloak.representations.idm.RealmRepresentation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@ApplicationScoped
@Path("/realms")
public class RealmResource {

    @Inject
    KeycloakService keycloakService;

    @GET
    public List<RealmRepresentation> getRealms() {
        return keycloakService.getRealms();
    }

    @Path("{realm}")
    @GET
    public RealmRepresentation getRealm(@PathParam("realm") String realm) {
        return keycloakService.getRealm(realm);
    }

    @POST
    public RealmRepresentation createRealm(@HeaderParam("X-Realm-Name") String name) {

        return keycloakService.createRealm(name);
    }
}
