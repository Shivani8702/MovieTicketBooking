package com.cg.mtb.ctrl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mtb.dto.UserDto;
import com.cg.mtb.entity.UserEntity;
import com.cg.mtb.exception.UserNotFoundException;
import com.cg.mtb.repo.UserRepository;
import com.cg.mtb.service.UserService;

@RestController
@RequestMapping("/user")
//http://localhost:8010/user
public class UserCtrl {
	@Autowired 
	UserService userService;
	@Autowired 
	private UserRepository userrepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManager;
	//http://localhost:8010/user/users
	@GetMapping("/users")
	public List<UserDto> getAllUsers(){
		List<UserDto> users=null;
		try {
			users=userService.getAllUsers();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return users;	
	}
	//http://localhost:8010/user/insertUser
	@PostMapping("/insertUser")
	public UserDto insertUser(@RequestBody UserDto userdto) throws Exception {
		UserDto userObj=userService.createUser(userdto);
		if(userObj==null) {
			throw new Exception("Error occured while inserting user");
		}
		return userdto;
	}
	//http://localhost:8010/user/userById/{uid}
	@GetMapping("/userById/{uid}")
	public UserDto getUserById(@PathVariable("uid")Integer userId){
		UserDto user=null;
		try {
			user=userService.getUserById(userId);
		}
		catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return user;
		
	}
	//http://localhost:8010/user/updateUser/{uid}
	@PutMapping("/updateUser/{uid}")
	public UserDto updateUser(@PathVariable int uid,@RequestBody UserDto userdto) {
		UserDto user=null;
		try {
			user=userService.updateUser(uid, userdto);
		}
		catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	//http://localhost:8010/user/deleteById/{uid}
	@DeleteMapping("/deleteById/{uid}")
	public void deleteUser(@PathVariable("uid")Integer userId) {
		try {
			userService.deleteUser(userId);
		}
		catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	//http://localhost:8010/user/register
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity) {
	    System.out.print(" Register controller ");
	    ResponseEntity<String> response;
	    
	    try {
	        // Encrypt password
	        String hashPwd = passwordEncoder.encode(userEntity.getPassword());
	        userEntity.setPassword(hashPwd);
	        userEntity.setRole("ROLE_USER"); // Assign default role

	        // Save user
	        userrepo.save(userEntity);

	        response = ResponseEntity.status(HttpStatus.CREATED).body("GIVEN USER DETAILS ARE SUCCESSFULLY REGISTERED");
	    } catch (Exception ex) {
	        response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An Exception occurred due to " + ex.getMessage());
	    }
	    
	    return response;
	}
	//http://localhost:8010/user/login
	@PostMapping("/login")
    public String loginUser(@RequestBody Map<String, String> credentials) {
		String email = credentials.get("email");
	    String password = credentials.get("password");
		String s="";
        try {
            s= userService.loginUser(email, password);
        } catch (UserNotFoundException e) {
        	s=e.getMsg();
            System.err.println(e.getMsg());
        }
        return s;
    }


	
	

}
