package com.keshav.Job.App.Rest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitoringAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoringAspect.class);
    @Around("execution(* com.keshav.Job.App.Rest.service.JobService.*(..))")// Around is used to test performance
    // it is like something to monitor execution speed , it should be there at start and end time of execution
    public Object MonitorTime(ProceedingJoinPoint jp) throws Throwable { // To calculate time we need ProceedingJointPoint which will at start and end of execution
        long start = System.currentTimeMillis();
       Object obj = jp.proceed(); // it will calculate the time for our method getPost by help of start and end.
        //Proceed return Object so we change MonitorTime method type void to Object, //otherwise data will not be shown but
        // in console we see the time but in Postman we want to see data we have tu use Object type method
        long end = System.currentTimeMillis();
        LOGGER.info("Time Taken by " + ": " + jp.getSignature().getName()+ " :" +(end-start) + " ms");
        return obj;

    }



}
