package com.stackroute.moviecruiserserver.serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;

import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.exception.*;
import com.stackroute.moviecruiserserver.repository.*;
import com.stackroute.moviecruiserserver.service.*;

public class UserServiceTest {
	
	private User user;
	Optional<User> options;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user=new User("sonu","Chandan","Mishra","123456",new Date());
		options=Optional.of(user);
	}
	
	@Test
	public void testSaveUserSuccess()throws UserNotFoundException,UserAlreadyExistsException{
		when(userRepository.save(user)).thenReturn(user);
		boolean flag =userService.saveUser(user);
		assertEquals("Cannot register user",true, flag);
		verify(userRepository, times(1)).save(user);
	}
	
	@Test(expected=UserAlreadyExistsException.class)
	public void testSaveUserFail()throws UserNotFoundException,UserAlreadyExistsException{
		when(userRepository.findById(user.getUserId())).thenReturn(options);
		when(userRepository.save(user)).thenReturn(user);
		boolean flag =userService.saveUser(user);
	}
	
	@Test
	public void testValidateSuccess()throws UserNotFoundException{
		when(userRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword())).thenReturn(user);
		User userResult=userService.findByUserIdAndPassword(user.getUserId(), user.getPassword());
		assertNotNull(userResult);
		assertEquals("sonu",userResult.getUserId());
		verify(userRepository, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
	}
}