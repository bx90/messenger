package bean.helper;

import bean.domain.Message;
import bean.domain.Profile;

import java.util.HashMap;
import java.util.Map;

public class DataBase {
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static private long overAllMessageId;

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

    public static void setOverAllMessageId(long overAllMessageId) {
        DataBase.overAllMessageId = overAllMessageId;
    }

    public static long getOverAllMessageId() {
        return overAllMessageId;
    }
}
