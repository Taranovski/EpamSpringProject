package com.epam.training.movie.theater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alyx on 23.03.2016.
 */
@Controller
@RequestMapping("home")
public class UserController {

    @RequestMapping(value = "/myHome", method = RequestMethod.GET)
    public ModelAndView home(@RequestParam(value = "userName", required = false) String name) {
        return new ModelAndView("home", "userName", name);
    }
}
