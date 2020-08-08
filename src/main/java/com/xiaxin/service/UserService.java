package com.xiaxin.service;

import com.xiaxin.entity.Users;
import com.xiaxin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<Users> getUser(Long id) {
		return userMapper.getUser(id);
	}
}
