package com.blog.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.dao.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.UserDto;
import com.blog.app.repositories.UserRepository;
import com.blog.app.service.UserService;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto addUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		User addeduser = userRepository.save(user);
		return this.modelMapper.map(addeduser, UserDto.class);
	}

	@Override
	public UserDto updateuser(UserDto UserDto, Integer userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Resource not found for given user id : " + userId));
				
		existingUser.setId(userId);
		existingUser.setName(UserDto.getName());
		existingUser.setPassword(UserDto.getPassword());
		existingUser.setEmail(UserDto.getEmail());
		existingUser.setAbout(UserDto.getAbout());
		User updatedUser = userRepository.save(existingUser);
		return this.modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found for given user id : " + id));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> allUsers = userRepository.findAll();
		List<UserDto> allUsersDto = allUsers.stream().map(user->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	return allUsersDto;
	}

	@Override
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource not found for given user id : " + id));
		userRepository.deleteById(id);
		
	}
}
