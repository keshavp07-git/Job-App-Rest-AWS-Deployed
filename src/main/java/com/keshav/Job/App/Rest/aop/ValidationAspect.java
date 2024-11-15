package com.keshav.Job.App.Rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.keshav.Job.App.Rest.service.JobService.*(..)) && args(postId)") // We add one more args , so it will be added according to this ,
    // also we gave the name postId
    public Object Validate(ProceedingJoinPoint jp, int postId) throws Throwable { // Here we added int PostId to hold the value to convert from negative to positive
        if (postId < 0) { // here the logic if value is less than zero in PostId then do - , - = + , postId = -postId means it will convert to positive.
            LOGGER.info("Entered value is Negative");
            postId = -postId; // It will Convert into + if entered value is -
            LOGGER.info("Entered value changing to Positive " + postId);
        }
            Object obj = jp.proceed(new Object[]{postId}); // it will proceed and show the output on console , because we are getting new value so
        // we will use new object to store new value given by AOP and its type will be Array because can give multiple value when we change value of postId
        // and in curly braces we passed postId so that we can refer it.
            return obj;
    }
}
