package com.stackroute.moviecruiserserver.controllerTest;

import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.moviecruiserserver.MovieCruiserAuthenticationServiceApplication;
import com.stackroute.moviecruiserserver.controller.*;
import com.stackroute.moviecruiserserver.domain.*;
import com.stackroute.moviecruiserserver.service.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes=MovieCruiserAuthenticationServiceApplication.class)
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMVC;
	@MockBean
	private UserService userService;
	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;
	@InjectMocks
	private UserController userController;
	private User user;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user=new User("John123","john","Peter","123456",new Date());
	}
	
	@Test
	public void testRegisterUser()throws Exception{
		when(userService.saveUser(user)).thenReturn(true);
		mockMVC.perform(post("/api/v1/userservice/register").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isCreated());
		verify(userService,times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(userService);
	}
	
	@Test
	public void testLoginUser()throws Exception{
		String userId="John123";
		String password="123456";
		when(userService.saveUser(user)).thenReturn(true);
		when(userService.findByUserIdAndPassword(userId, password)).thenReturn(user);
		mockMVC.perform(post("/api/v1/userservice/login").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status().isOk());
		verify(userService,times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(userService);
	}
	
	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}
