package service.message;

import bean.domain.Message;
import domain.manager.MessageManager;
import service.comment.CommentService;
import service.profile.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)// to get json conversion support. please check pom.xml
public class MessageService {

    MessageManager msgManager = new MessageManager();

    @GET
    public List<Message> getAllMessages() {
        return msgManager.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") Long id,
                              @Context UriInfo uriInfo) {
        Message message = msgManager.getMessage(id);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comments");
        return msgManager.getMessage(id);
    }


//    @POST
//    public Message addMessages(Message message) {
//        return msgManager.addMessage(message);
//    }

    @POST
    public Response addMessages(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
        Message newMessage = msgManager.addMessage(message);
        // Add the new Id into the URI path.
        URI uri = uriInfo.getAbsolutePathBuilder().path(message.getId().toString()).build();
//        return Response.status(Response.Status.CREATED) // 201 created
//                .entity(newMessage)
//                .build();


        return Response.created(uri) // 201 created
                .entity(newMessage)
                .build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") Long id,
                                 Message message) {
        message.setId(id);
        return msgManager.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public Message deleteMessage(@PathParam("messageId") Long id) {
        return msgManager.removeMessage(id);
    }

    @GET
    @Path("/test")
    public String test() {
        return "Message Service.";
    }

    @Path("/{messageId}/comments")
    public CommentService getCommentResource() {
        return new CommentService();
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessageService.class)
                .path(MessageService.class, "getCommentResource")
                .path(CommentService.class)
                .resolveTemplate("messageId", message.getId())
                .build();
        return uri.toString();
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileService.class)
                .path(message.getAuthor())
                .build();
        return uri.toString();
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) {
        String uri = uriInfo.getBaseUriBuilder()
                .path(MessageService.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
        return uri;
    }
}
