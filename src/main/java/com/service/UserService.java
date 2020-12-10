package com.service;

import com.entity.UserEntity;
import com.enums.EUserType;
import com.repository.UserRepository;
import com.request.AddUserRequest;
import com.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity checkLogin(LoginRequest loginRequest){
       return userRepository.findByUserNameAndPassword(loginRequest.getUserName(),loginRequest.getPassword());
    }

    public List<UserEntity> getNormalUser(){
        return userRepository.findByUserType(EUserType.USER.getCode());
    }

    public boolean checkUserName(String userName){
        return userRepository.findByUserName(userName)==null;
    }


    public UserEntity add(AddUserRequest addUserRequest){
        if(!checkUserName(addUserRequest.getUserName())){
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(addUserRequest.getName());
        userEntity.setUserName(addUserRequest.getUserName());
        userEntity.setPassword(addUserRequest.getPassword());
        userEntity.setUserType(EUserType.USER.getCode());
        userRepository.save(userEntity);
        return userEntity;
    }

    public void deleteUser(Integer userId){
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity==null){
            return;
        }
        userRepository.delete(userEntity);
    }
}
