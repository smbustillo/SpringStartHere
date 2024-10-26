package main;

import aop.LoggingAspect;
import configuration.ProjectConfiguration;
import model.Comment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.CommentService;

import java.util.logging.Logger;

public class MainSpring {

    private static Logger logger = Logger.getLogger(MainSpring.class.getName());

    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var comment = new Comment();
        comment.setAuthor("Smbustillo");
        comment.setText("Demo 2 comment");

        var commentService = context.getBean(CommentService.class);
        String value = commentService.publishComment(comment);
        logger.info(value);
    }
}
