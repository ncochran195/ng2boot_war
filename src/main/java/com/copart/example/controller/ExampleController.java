package com.copart.example.controller;


import com.copart.example.model.Greeting;
import com.copart.example.util.RestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kashah on 1/23/2017.
 */
@RestController
public class ExampleController {

    @RequestMapping("/greetings.json")
    public String getGreetings() throws Exception {
    	Thread.sleep(2000);
        Greeting [] g = new Greeting[2];
        g[0] = new Greeting(1, "Hello");
        g[1] = new Greeting(2, "Bonjour");
        Map<String, Object> params = new HashMap<>();
        params.put("data", g);
        return RestUtils.getSuccessResponseObject(params);
    }
}
