package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TestDAO;
import com.test.model.Greeting;

@Service
public class TestService {
	
	@Autowired
	TestDAO testDao;
	
	public Greeting[] getGreetings() {
		return testDao.getGreetings();
	}
}
