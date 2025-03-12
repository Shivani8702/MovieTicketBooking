package com.cg.mtb.service;

import java.util.List;
import java.util.Optional;

import com.cg.mtb.dto.UserDto;
import com.cg.mtb.entity.AddressEntity;
import com.cg.mtb.entity.UserEntity;
import com.cg.mtb.exception.UserNotFoundException;

public interface UserService {
	public UserDto createUser(UserDto userDTO);
    public UserDto getUserById(int userId) throws UserNotFoundException ;
    public List<UserDto> getAllUsers();
    public UserDto updateUser(int userId, UserDto userDTO) throws UserNotFoundException ;
    public void deleteUser(int userId) throws UserNotFoundException ;
    public String loginUser(String email, String password) throws UserNotFoundException;
    public UserEntity registerUser(int userId,String userName, String email, String phone, String password,String role, AddressEntity address);
	
}
