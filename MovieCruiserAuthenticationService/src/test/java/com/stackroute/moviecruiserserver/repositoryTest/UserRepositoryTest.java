package com.stackroute.moviecruiserserver.repositoryTest;

import java.util.*;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import com.stackroute.moviecruiserserver.MovieCruiserAuthenticationServiceApplication;
import com.stackroute.moviecruiserserver.domain.User;
import com.stackroute.moviecruiserserver.repository.UserRepository;

@ContextConfiguration(classes=MovieCruiserAuthenticationServiceApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Transactional
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;
	
	private User user;
	
	@Before
	public void setUp()throws Exception{
		user=new User("Johny", "Johny jenny", "Bobby", "3467", new Date());
	}
	
	@Test
	public void testRegisterUserSuccessfull(){
		userRepository.save(user);
		Optional<User> object=userRepository.findById(user.getUserId());
		assertThat(object.equals(user));
	}
}
