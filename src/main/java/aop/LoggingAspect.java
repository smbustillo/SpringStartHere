package aop;

import model.Comment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Component
@Aspect
public class LoggingAspect {

    private Logger logger = Logger.getLogger(LoggingAspect.class.getName());

    @Around("execution(* services.*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint){

        Object returnedByMethod;
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        logger.info("Method: " + methodName +
                " with parameters: " + Arrays.asList(arguments) +
                " will execute...");

        Comment c = new Comment();
        c.setText("Other text");
        Object[] otherArgs = {c};

        try {
            returnedByMethod = joinPoint.proceed(otherArgs);

            logger.info("Method executed and returned: " + returnedByMethod);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return "FAIL";
    }
}
