package com.example.zuul.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.zuul.domain.User;

@Repository
public class UserDAO {

	private List<User> users;
	
	public UserDAO() {
		users=new ArrayList<User>();
		mock();
	}
	

	
	void mock() {
		users.add(new User("admin","admin"));
		users.add(new User("nacho","123"));
	}
	
	public User findUser(User user) {
	return	users.stream().
		filter(item->item.getUserName().equals(user.getUserName())
					&& item.getPassword().equals(user.getPassword())
				).findFirst().orElse(null);
	}
	

}
