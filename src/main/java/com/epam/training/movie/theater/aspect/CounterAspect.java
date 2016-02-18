/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.training.movie.theater.aspect;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * count how many times each event was accessed by name, how many times its
 * prices were queried, how many times its tickets were booked.
 *
 * @author Alyx
 */
@Aspect
public class CounterAspect {

    private Map<String, AtomicLong> storage = new ConcurrentHashMap<>();

    @Pointcut("execution(* *.getByMovieName(..))")
    private void countEventAccess() {
    }

    @Before("countEventAccess()")
    public void countEventAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String movieName = (String) args[0];
        if (storage.containsKey(movieName)) {
            storage.get(movieName).incrementAndGet();
        } else {
            storage.put(movieName, new AtomicLong(1));
        }
    }

    public Map<String, AtomicLong> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, AtomicLong> storage) {
        this.storage = storage;
    }

}
