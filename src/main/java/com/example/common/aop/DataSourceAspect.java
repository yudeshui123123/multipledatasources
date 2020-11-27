package com.example.common.aop;

import com.example.common.CustomerContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/11/25 20:38
 * @description:
 */
@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("execution(* com.example.mapper.MySql*.*(..))")
    public void mysqlTest(){}

    @Pointcut("execution(* com.example.mapper.Oracle*.*(..))")
    public void oracleTest(){}

    @Before("mysqlTest()")
    public void mySqlDataSource(JoinPoint joinPoint) {
        CustomerContextHolder.setCustomerType("mySqlDataSource");
    }

    @Before("oracleTest()")
    public void oracleDataSource(JoinPoint joinPoint) {
        CustomerContextHolder.setCustomerType("oracleDataSource");
    }

    @After("mysqlTest()||oracleTest()")
    public void doAfterReturning(JoinPoint joinPoint) {
        CustomerContextHolder.clearCustomerType();
    }


}
