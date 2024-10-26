package services;

import model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import processors.CommentProcessor;
import proxies.CommentNotificationProxy;
import repositories.CommentRepository;

import java.util.logging.Logger;


@Service
public class CommentService {
    private Logger logger = Logger.getLogger(CommentService.class.getName());

    private final CommentRepository commentRepository;
    private final CommentNotificationProxy commentNotificationProxy;

    @Autowired
    private ApplicationContext context;

    //como solo hay un constructor no es necesario el Autowired
    public CommentService(CommentRepository commentRepository, @Qualifier("EMAIL") CommentNotificationProxy commentNotificationProxy) {

        this.commentRepository = commentRepository;
        this.commentNotificationProxy = commentNotificationProxy;
    }

    public String publishComment(Comment comment){
        logger.info("Publishing comment...");
        CommentProcessor p = context.getBean(CommentProcessor.class);
        p.setComment(comment);
        p.processComment(comment);
        p.validateComment(comment);

        commentRepository.storeComment(comment);
        commentNotificationProxy.sendComment(comment);

        return "SUCCESS";
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }
}
