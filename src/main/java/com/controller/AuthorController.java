package com.controller;

import com.entity.AuthorEntity;
import com.entity.PublisherEntity;
import com.response.CommonResponse;
import com.service.AuthorService;
import com.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add/{name}")
    public ResponseEntity<CommonResponse> add(@PathVariable String name) {
        AuthorEntity authorEntity= authorService.add(name);
       if(authorEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(authorEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<CommonResponse> getAll() {
        List<AuthorEntity> authorEntityList= authorService.getAll();
        if(authorEntityList==null || authorEntityList.isEmpty()){
            CommonResponse response = new CommonResponse();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        CommonResponse response = new CommonResponse();
        response.setData(authorEntityList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
