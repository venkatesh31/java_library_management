package com.controller;

import com.entity.UserEntity;
import com.request.AddUserRequest;
import com.request.LoginRequest;
import com.response.CommonResponse;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponse> login(@RequestBody LoginRequest request) {
       UserEntity userEntity= userService.checkLogin(request);
       if(userEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(userEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> add(@RequestBody AddUserRequest request) {
       UserEntity userEntity= userService.add(request);
       if(userEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(userEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/normal")
    public ResponseEntity<CommonResponse> getNormalUser() {
        List<UserEntity> userEntityList= userService.getNormalUser();
        if(userEntityList==null || userEntityList.isEmpty()){
            CommonResponse response = new CommonResponse();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        CommonResponse response = new CommonResponse();
        response.setData(userEntityList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/delete/{userid}")
    public ResponseEntity<CommonResponse> deleteUser(@PathVariable Integer userid) {
        userService.deleteUser(userid);
        CommonResponse response = new CommonResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
