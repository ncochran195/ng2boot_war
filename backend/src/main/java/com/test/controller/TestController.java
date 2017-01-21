package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.test.model.Greeting;
import com.test.service.TestService;

@Controller
public class TestController {
	@Autowired
	TestService testService;
	
    @RequestMapping("/greetings.ajax")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Greeting[] greetings = testService.getGreetings();
    	Gson gson = new Gson();
		return new ModelAndView("data", "data", gson.toJson(greetings));
    }

}
