package com.revature.service;

import org.springframework.stereotype.Service;

import com.revature.bean.User;

@Service
public class UserService
{
	//Service returns null if the person logging in is not John.
	//Returns John otherwise.
	public User auth(String name, String pass)
	{
		User authUser = null;
		if("john".equals(name)&&"p4ssw0rd".equals(pass))
		{
			//authUser =new User("john", "p4ssword", "John Fowler");
		}
		return authUser;
	}
}
