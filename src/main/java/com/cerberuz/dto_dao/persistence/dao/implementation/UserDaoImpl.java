package com.cerberuz.dto_dao.persistence.dao.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cerberuz.dto_dao.persistence.dao.interfaces.IUserDao;
import com.cerberuz.dto_dao.persistence.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements IUserDao {

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return this.em.createQuery("SELECT u FROM UserEntity u").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(this.em.find(UserEntity.class, id));
    }

    @Override
    @Transactional
    public void saveUser(UserEntity userEntity) {
        this.em.persist(userEntity);
        this.em.flush();
    }

    @Override
    @Transactional
    public void updateUser(UserEntity userEntity) {
        this.em.merge(userEntity);
    }

    @Override
    @Transactional
    public void deleteUser(UserEntity userEntity) {
        this.em.remove(userEntity);
    }
    
}
