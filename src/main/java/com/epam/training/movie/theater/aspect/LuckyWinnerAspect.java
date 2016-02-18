/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.aspect;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author Alyx
 */
@Aspect
public class LuckyWinnerAspect {

    @Pointcut("execution(* *.getDiscount(..))")
    private void discountPointcut() {
    }

    @Around("discountPointcut()")
    public Object countEventAccess(ProceedingJoinPoint proceedingJoinPoint) {
        try {

            if (Math.random() > 0.5) {
                return BigDecimal.ONE;
            }
            return proceedingJoinPoint.proceed();

        } catch (Throwable ex) {
            Logger.getLogger(LuckyWinnerAspect.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }

    }
}
