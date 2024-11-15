package com.keshav.Job.App.Rest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

   // @Before("execution(* com.keshav.Job.App.Rest.service.JobService.getPost(..))") // I determined when i want a log and that point is getPost is the concept of Joint Point
    @Before("execution(* com.keshav.Job.App.Rest.service.JobService.getPost(..)) || execution(* com.keshav.Job.App.Rest.service.JobService.deleteJob(..))")
    //Here we are using Joint Point for two method simultaneously.
    public void logMethodCall(JoinPoint jp){
        LOGGER.info(" Method Called " + jp.getSignature().getName()); // it gives log before our method called
    }

    @After("execution(* com.keshav.Job.App.Rest.service.JobService.getPost(..)) || execution(* com.keshav.Job.App.Rest.service.JobService.deleteJob(..))")
    public void logMethodCallAfter(JoinPoint jp){
        LOGGER.info(" Method Called After " + jp.getSignature().getName()); // it gives log when our method called
    }


    @AfterThrowing("execution(* com.keshav.Job.App.Rest.service.JobService.getPost(..)) || execution(* com.keshav.Job.App.Rest.service.JobService.deleteJob(..))")
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info(" Method Called Crashed " + jp.getSignature().getName()); // it gives log when code gives exception
    }

    @AfterReturning("execution(* com.keshav.Job.App.Rest.service.JobService.getPost(..)) || execution(* com.keshav.Job.App.Rest.service.JobService.deleteJob(..))")
    public void logMethodCallSuccess(JoinPoint jp){
        LOGGER.info(" Method Called Successfully " + jp.getSignature().getName()); /// it gives log when our execution completely without interrupted by any exception

}}

