package main;

import configuration.ProjectConfiguration;
import model.Comment;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import proxies.EmailCommentNotificationProxy;
import repositories.DBCommentRepository;
import services.CommentService;
//import services.CommentService2;
import services.UserService;

public class Main {
    public static void main(String[] args) {
        var c = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        //m1();
        //m2();
        //m3();
        m4(c);

    }

    private static void m4(AnnotationConfigApplicationContext context){
        System.out.println("Antes de usar CommentService");
        var cs = context.getBean(CommentService.class);
        var us = context.getBean(UserService.class);
        System.out.println("Despues de recuperar CommentService");

        boolean areEquals = cs.getCommentRepository() == us.getCommentRepository();
        System.out.println("Are repositories equeals ? " + areEquals);
    }

    private static void m3(){
        var c = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var cs = c.getBean(CommentService.class);
        var us = c.getBean(UserService.class);

        boolean areEquals = cs.getCommentRepository() == us.getCommentRepository();

        System.out.println("Both repositories are equal ? " + areEquals);
    }

    private static void m1() {
        //instancio las dependencias
        var commentRepository = new DBCommentRepository();
        var commentNotificationProxy = new EmailCommentNotificationProxy();

        //instancio el servicio que usa las dependencias
        var commentService = new CommentService(commentRepository,commentNotificationProxy);

        var comment = new Comment();
        comment.setAuthor("Smbustillo");
        comment.setText("Demo comment");

        commentService.publishComment(comment);
    }

    private static void m2(){
        var c = new AnnotationConfigApplicationContext(ProjectConfiguration.class);

        var cs1 = c.getBean("commentService", CommentService.class);
        var cs2 = c.getBean("commentService", CommentService.class);

        boolean areEquals = cs1 == cs2;

        System.out.println("son iguales cs1 y cs2? " + areEquals);
    }
}
