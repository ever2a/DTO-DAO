package com.cerberuz.dto_dao.service.interfaces;

import java.util.List;

import com.cerberuz.dto_dao.presentation.dto.UserDto;

public interface IUserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Long id);
    String deleteUser(Long id);
}
