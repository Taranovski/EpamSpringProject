package com.epam.training.movie.theater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alyx on 20.03.2016.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(@RequestParam("name") String name) {
        return "hello, " + name;
    }

    @RequestMapping(value = "/helloVelocity", method = RequestMethod.GET)
    public ModelAndView sayHelloVelocity(@RequestParam("name") String name, @RequestParam(value = "number", defaultValue = "1") Integer someNumber) {
        return new ModelAndView("helloVelocity","message","hello, " + name + (1 / someNumber));
    }
}
