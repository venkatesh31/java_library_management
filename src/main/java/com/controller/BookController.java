package com.controller;

import com.entity.BookEntity;
import com.request.BookRequest;
import com.request.SearchBookRequest;
import com.response.CommonResponse;
import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<CommonResponse> add(@RequestBody BookRequest request) {
       BookEntity bookEntity= bookService.add(request);
       if(bookEntity==null){
           CommonResponse response = new CommonResponse();
           return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
       }
        CommonResponse response = new CommonResponse();
       response.setRecordinfo(bookEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<CommonResponse> edit(@RequestBody BookRequest request) {
        BookEntity bookEntity= bookService.edit(request);
        if(bookEntity==null){
            CommonResponse response = new CommonResponse();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        CommonResponse response = new CommonResponse();
        response.setRecordinfo(bookEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<CommonResponse> search(@RequestBody SearchBookRequest searchBookRequest) {
        List<BookEntity> bookEntityList= bookService.searchBook(searchBookRequest.getSearchText());
        if(bookEntityList==null || bookEntityList.isEmpty()){
            CommonResponse response = new CommonResponse();
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        CommonResponse response = new CommonResponse();
        response.setData(bookEntityList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
