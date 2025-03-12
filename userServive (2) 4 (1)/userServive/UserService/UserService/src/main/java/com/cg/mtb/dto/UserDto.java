package com.cg.mtb.dto;

import java.io.Serializable;

public class UserDto implements Serializable {
    private int userId;
    private String userName;
    private String email;
    private String phone;
    private String password;
    private String role;
    private AddressDto address;
    public int getUserId() {
        return userId;
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserDto(int userId, String userName, String email, String phone, String password, String role,
			AddressDto address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.address = address;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
