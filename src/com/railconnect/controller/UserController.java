package com.railconnect.controller;

import com.railconnect.serviceimpl.UserServiceImpl;

public class UserController {
	
	
	
	UserServiceImpl service=new UserServiceImpl();
	
	
	void getMsg(String msg) {
		String welcomemsg = service.welcome(msg);
		System.out.println(welcomemsg);
	}

}
