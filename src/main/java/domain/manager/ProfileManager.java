package domain.manager;

import bean.domain.Profile;
import bean.helper.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileManager {

    private Map<String, Profile> profiles = DataBase.getProfiles();

    public ProfileManager() {
        profiles.put("bsun", new Profile(1L , "bsun", "Rick", "S"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId((long) (profiles.size() + 1));
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }

    public Map<String, Profile> getProfiles() {
        return profiles;
    }
}
