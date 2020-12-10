package com.controller;

import com.entity.UserBookEntity;
import com.enums.EStatus;
import com.request.AddUserBookRequest;
import com.response.CommonResponse;
import com.service.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/user/book")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> add(@RequestBody AddUserBookRequest request) {
       UserBookEntity userBookEntity=  userBookService.add(request);
       if(userBookEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(userBookEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/return/{userbookid}")
    public ResponseEntity<CommonResponse> add(@PathVariable Integer userbookid) {
       UserBookEntity userBookEntity=  userBookService.returnBook(userbookid);
       if(userBookEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(userBookEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{userid}/{status}")
    public ResponseEntity<CommonResponse> getByUser(@PathVariable Integer userid,@PathVariable Integer status) {
        List<UserBookEntity> userBookEntityList=  userBookService.getByUserIdAndStatus(userid, status);
        CommonResponse response = new CommonResponse();
        response.setData(userBookEntityList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
