package com.cg.mtb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.mtb.dto.UserDto;
import com.cg.mtb.entity.AddressEntity;
import com.cg.mtb.entity.UserEntity;
import com.cg.mtb.exception.UserNotFoundException;
import com.cg.mtb.repo.UserRepository;
import com.cg.mtb.util.UserUtil;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder passwordEncoder;
	

	@Override
	public UserDto createUser(UserDto userDTO) {
		UserEntity user = UserUtil.convertToEntity(userDTO);
        UserEntity savedUser = userRepository.save(user);
        return UserUtil.convertToDTO(savedUser);
	}

	@Override
	public UserDto getUserById(int userId) throws UserNotFoundException {
		UserDto userdto=null;
		Optional<UserEntity> opUser=userRepository.findById(userId);
		if(opUser.isPresent()) {
			userdto=UserUtil.convertToDTO(opUser.get());	
		}
		else {
			throw new UserNotFoundException("User not found");
		}
		return userdto;		
		
	}

	@Override
	public List<UserDto> getAllUsers() {
		return UserUtil.convertToDTOList(userRepository.findAll());
	}

	@Override
	public UserDto updateUser(int userId, UserDto userDTO) throws UserNotFoundException {
		if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        UserEntity user = UserUtil.convertToEntity(userDTO);
        user.setUserId(userId);
        UserEntity updatedUser = userRepository.save(user);
        return UserUtil.convertToDTO(updatedUser);
	}

	@Override
	public void deleteUser(int userId) throws UserNotFoundException {
		if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
	}
	public String loginUser(String email, String password) throws UserNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Invalid email or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Invalid email or password");
        }

        return "login Successfull";
    }





	@Override
	public UserEntity registerUser(int userId, String userName, String email, String phone, String password,String role,
			AddressEntity address) {
		if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already taken");
        }
        String encryptedPassword = passwordEncoder.encode(password);
        UserEntity user = new UserEntity(userId,userName, email, phone, encryptedPassword,role,address);
        return userRepository.save(user);
	}

	

}
