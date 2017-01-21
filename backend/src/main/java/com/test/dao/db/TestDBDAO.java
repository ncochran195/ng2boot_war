package com.test.dao.db;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.test.dao.TestDAO;
import com.test.model.Greeting;

@Component("TestDAO")
public class TestDBDAO implements TestDAO {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

	@Override
	public Greeting[] getGreetings() {
    	Greeting g1 = new Greeting(counter.incrementAndGet(), String.format(template, "Tom"));
    	Greeting g2 = new Greeting(counter.incrementAndGet(), String.format(template, "John"));
    	Greeting g3 = new Greeting(counter.incrementAndGet(), String.format(template, "Joe"));
    	
    	Greeting[] greetings = new Greeting[3];
    	greetings[0] = g1;
    	greetings[1] = g2;
    	greetings[2] = g3;
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    	return greetings;
	}
	
}
