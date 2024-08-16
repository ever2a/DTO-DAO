package com.cerberuz.dto_dao.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cerberuz.dto_dao.persistence.dao.interfaces.IUserDao;
import com.cerberuz.dto_dao.persistence.entity.UserEntity;
import com.cerberuz.dto_dao.presentation.dto.UserDto;
import com.cerberuz.dto_dao.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public List<UserDto> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        return this.iUserDao.findAll()
            .stream()
            .map(entity -> modelMapper.map(entity, UserDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<UserEntity> userEntity = this.iUserDao.findById(id);

        if (userEntity.isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity currentUser = userEntity.get();

            return modelMapper.map(currentUser, UserDto.class);
        } else {
            return new UserDto();
        }
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
            this.iUserDao.saveUser(userEntity);
            return userDto;
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al crear un usuario");
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        Optional<UserEntity> userEntity = this.iUserDao.findById(id);

        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();

            currentUser.setName(userDto.getName());
            currentUser.setEmail(userDto.getEmail());
            currentUser.setAge(userDto.getAge());

            this.iUserDao.updateUser(currentUser);

            ModelMapper modelMapper = new ModelMapper();
            return modelMapper.map(currentUser, UserDto.class);
        } else {
            throw new IllegalArgumentException("El usuario no existe");
        }
    }

    @Override
    public String deleteUser(Long id) {
        Optional<UserEntity> userEntity = this.iUserDao.findById(id);

        if (userEntity.isPresent()) {
            UserEntity currentUser = userEntity.get();
            this.iUserDao.deleteUser(currentUser);
            return "El usuario con ID: " + id + " ha sido eliminado";
        } else {
            return "El usuario con ID: " + id + " no existe";
        }
    }
    
}
