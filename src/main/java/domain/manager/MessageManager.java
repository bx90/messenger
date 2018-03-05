package domain.manager;

import bean.domain.Message;
import bean.helper.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MessageManager {
    private Map<Long, Message> messages = DataBase.getMessages();

    public MessageManager() { }

    public List<Message> getAllMessages() {
        return new ArrayList<>(messages.values());
    }

    public Message getMessage(Long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId((long) (messages.size() + 1));
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Long id) {
        return messages.remove(id);
    }
}
