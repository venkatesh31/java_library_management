package com.repository;


import com.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByUserNameAndPassword(String userName,String password);
    UserEntity findByUserName(String userName);
    UserEntity findByUserId(Integer userId);
    List<UserEntity> findByUserType(Integer userType);
}