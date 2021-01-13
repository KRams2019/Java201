package com.example.demo.service.serviceImpl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.dao.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public final static Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	UserRepository userRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = convertDtoToEntity(userDto);
		User savedUser = userRepository.save(user);
		UserDto savedUserDto = convertEntityToDto(savedUser);
		log.info("User Added Successfully");
		return savedUserDto;
	}

	public UserDto convertEntityToDto(User user) {
		return modelMapper.map(user, UserDto.class);

	}

	public User convertDtoToEntity(UserDto userDto) {
		return modelMapper.map(userDto, User.class);
	}

}
