/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.aspect;

import com.epam.training.movie.theater.domain.User;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 *
 * @author Alyx
 */
@Aspect
@Order(2)
public class DiscountAspect {

    Map<Pair<User, BigDecimal>, AtomicLong> map = new ConcurrentHashMap<>();

    @Pointcut("execution(* *.getDiscount(..))")
    private void discountPointcut() {
    }

    @Around("discountPointcut()")
    public Object recordDiscount(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        User user = (User) args[0];

        Object proceed;
        try {
            proceed = proceedingJoinPoint.proceed();
            BigDecimal discount = (BigDecimal) proceed;

            Pair<User, BigDecimal> pair = Pair.of(user, discount);

            if (map.containsKey(pair)) {
                map.get(pair).incrementAndGet();
            } else {
                map.put(pair, new AtomicLong(1L));
            }

            return proceed;
        } catch (Throwable ex) {
            Logger.getLogger(DiscountAspect.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        }
    }

    Map<BigDecimal, Long> getDiscounsCountForUser(User user) {
        Map<BigDecimal, Long> discounts = new HashMap<>();
        for (Map.Entry<Pair<User, BigDecimal>, AtomicLong> entry : map.entrySet()) {
            if (entry.getKey().getKey().equals(user)) {
                discounts.put(entry.getKey().getValue(), entry.getValue().get());
            }
        }
        return discounts;
    }
}
