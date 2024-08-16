package com.cerberuz.dto_dao.persistence.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.cerberuz.dto_dao.persistence.entity.UserEntity;

public interface IUserDao {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    void saveUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    void deleteUser(UserEntity userEntity);
}
