package com.ascendion.libraryManagementService.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //Named pointcut
    @Pointcut("execution(* com.ascendion.libraryManagementService.service.*.*(..))")
    private void anyMethod() {
    }

    @Around("anyMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around");
        Object result = pjp.proceed();
        System.out.println("after around");
        return result;
    }

    @Pointcut("within(com.ascendion.libraryManagementService.service.BorrowerService)")
    private void withinBorrowerService() {
        System.out.println("withinBorrowerService");
    }

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void withinService() {
        System.out.println("withinService");
    }
    //Explain this withinService method -


    //@After("execution(* com.ascendion.libraryManagementService.service.BorrowerService.borrowBook(..))")  //pointcut expression
    @Before("execution(* com.ascendion.libraryManagementService.service.BorrowerService.*(..))")
    public void log() {
        System.out.println("Aspect log called");
    }

    @Around("execution(* com.ascendion.libraryManagementService.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Aspect log called");
        //method/joinpoint call
        Object result = joinPoint.proceed();
        System.out.println("Aspect after log called");
        return result;
    }

}
