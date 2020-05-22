package com.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String welcome(){

        return "welcome";
    }

    @GetMapping(value = "/oups")
    public String triggerException() {
        throw new RuntimeException("Expected: controller used to showcase what " +
            "happens when an exception is thrown");
    }
}
