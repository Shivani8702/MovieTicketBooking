package com.cg.mtb.util;

import java.util.List;
import java.util.stream.Collectors;

import com.cg.mtb.dto.UserDto;
import com.cg.mtb.entity.UserEntity;

public class UserUtil {
	public static UserDto convertToDTO(UserEntity user) {
	if (user == null) {
        return null;
    }
    return new UserDto(
        user.getUserId(),
        user.getUserName(),
        user.getEmail(),
        user.getPhone(),
        user.getPassword(),
        user.getRole(),
        AddressUtil.convertToDTO(user.getAddress())
    );
}

public static UserEntity convertToEntity(UserDto userDTO) {
    if (userDTO == null) {
        return null;
    }
    UserEntity user = new UserEntity();
    user.setUserId(userDTO.getUserId());
    user.setUserName(userDTO.getUserName());
    user.setEmail(userDTO.getEmail());
    user.setPhone(userDTO.getPhone());
    user.setPassword(userDTO.getPassword());
    user.setRole(userDTO.getRole());
    user.setAddress(AddressUtil.convertToEntity(userDTO.getAddress()));
    return user;
}

public static List<UserDto> convertToDTOList(List<UserEntity> users) {
    return users.stream()
            .map(UserUtil::convertToDTO)
            .collect(Collectors.toList());
}

public static List<UserEntity> convertToEntityList(List<UserDto> userDTOs) {
    return userDTOs.stream()
            .map(UserUtil::convertToEntity)
            .collect(Collectors.toList());
}

}
