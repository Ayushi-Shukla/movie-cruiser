package com.stackroute.moviecruiserserver.service;

import java.util.Map;
import com.stackroute.moviecruiserserver.domain.User;

public interface SecurityTokenGenerator {
	
	Map<String,String> generateToken(User user);
}
