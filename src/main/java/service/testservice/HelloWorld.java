package service.testservice;

import bean.domain.Message;
import service.message.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


@Path("/helloworld")
public class HelloWorld {

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String helloWorld() {
//        return "Hello world.!!";
//    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> helloWorld() {
        MessageService ms = new MessageService();
        List<Message> result = new ArrayList<>();
        result.add(new Message(1L, "aaa", "bsun"));
        result.add(new Message(2L, "bbb", "bsun"));
        result.add(new Message(3L, "ccc", "bsun"));
        result.add(new Message(4L, "ddd", "bsun"));
        return result;
    }
}
