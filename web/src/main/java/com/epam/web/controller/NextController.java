package com.epam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class NextController {

    @RequestMapping(method = RequestMethod.GET)
    public String hello(ModelMap model) {
        return "index";
    }
}
