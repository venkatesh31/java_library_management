package com.controller;

import com.entity.PublisherEntity;
import com.response.CommonResponse;
import com.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping("/add/{name}")
    public ResponseEntity<CommonResponse> add(@PathVariable String name) {
       PublisherEntity publisherEntity= publisherService.add(name);
       if(publisherEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(publisherEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<CommonResponse> getAll() {
        List<PublisherEntity> publisherEntityList= publisherService.getAll();
        if(publisherEntityList==null || publisherEntityList.isEmpty()){
            CommonResponse response = new CommonResponse();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        CommonResponse response = new CommonResponse();
        response.setData(publisherEntityList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
