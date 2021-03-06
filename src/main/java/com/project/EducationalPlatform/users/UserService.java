package com.project.EducationalPlatform.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repo;

	public void processOAuthPostLogin(String username) {
		System.out.println("name: " + username);
		User existUser = repo.getUserByUsername(username);

		if (existUser == null) {
			User newUser = new User();
			newUser.setName(username);


			repo.save(newUser);
		}

	}
	
	public List<User> listAll() {
		return repo.findAll();
	}
	
	public void save(User user) {
		repo.save(user);
	}
	
	public User get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
