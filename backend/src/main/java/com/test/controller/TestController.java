package com.test.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.test.model.Greeting;

@Controller
public class TestController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting.ajax")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Greeting g = new Greeting(counter.incrementAndGet(), String.format(template, name));
    	Gson gson = new Gson();
		return new ModelAndView("data", "data", gson.toJson(g));
    }

}
