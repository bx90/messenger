package service.comment;

import bean.domain.Comment;
import domain.manager.CommentManager;

import javax.ws.rs.*;
import java.util.List;

@Path("/")
public class CommentService {
    private CommentManager commentManager = new CommentManager();

    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long messageId) {
        return commentManager.getAllComments(messageId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long messageId,
                              Comment comment) {
        return commentManager.addComment(messageId, comment);
    }

    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long messageId,
                                 @PathParam("commentId") long id,
                                 Comment comment) {
        comment.setId(id);
        return commentManager.updateComment(messageId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public void deleteComment(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId) {
        commentManager.removeComment(messageId, commentId);
    }


    @GET
    @Path("/{commentId}")
    public Comment getMessage(@PathParam("messageId") long messageId,
                              @PathParam("commentId") long commentId) {
        return commentManager.getComment(messageId, commentId);
    }
}
