package service.profile;

import bean.domain.Profile;
import domain.manager.ProfileManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileService {
    private ProfileManager profileManager = new ProfileManager();

    @GET
    public List<Profile> getProfiles(@QueryParam("profileName") String profileName) {
        if (profileName != null && profileName.length() > 0) {
            return Arrays.asList(profileManager.getProfile(profileName));
        }

        return profileManager.getAllProfiles();
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileManager.addProfile(profile);
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileManager.getProfile(profileName);
    }

    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileManager.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public Profile deleteProfile(@PathParam("profileName") String profileName) {
        return profileManager.removeProfile(profileName);
    }
}

