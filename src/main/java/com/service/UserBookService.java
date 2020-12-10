package com.service;

import com.entity.UserBookEntity;
import com.enums.EStatus;
import com.repository.UserBookRepository;
import com.request.AddUserBookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserBookService {

    @Autowired
    private UserBookRepository userBookRepository;

    @Autowired
    private BookService bookService;

    public boolean isBookAvailable(Integer bookId){
       return userBookRepository.findByBookIdAndIsBookReturned(bookId,EStatus.ACTIVE.getCode())==null;
    }

    public List<UserBookEntity> getByUserIdAndStatus(Integer userId,Integer status){
       return userBookRepository.findByUserIdAndIsBookReturned(userId,status);
    }

    public UserBookEntity add(AddUserBookRequest addUserBookRequest){
        if(!isBookAvailable(addUserBookRequest.getBookId())){
            return null;
        }
        UserBookEntity userBookEntity = new UserBookEntity();
        userBookEntity.setBookId(addUserBookRequest.getBookId());
        userBookEntity.setUserId(addUserBookRequest.getUserId());
        userBookEntity.setDateCreated(new Date());
        userBookEntity.setIsBookReturned(EStatus.ACTIVE.getCode());
        userBookRepository.save(userBookEntity);
        bookService.updateAvailability(addUserBookRequest.getBookId(),EStatus.ACTIVE.getCode());
        return userBookEntity;
    }

    public UserBookEntity returnBook(Integer userBookId){
        UserBookEntity userBookEntity = userBookRepository.findByUserBookId(userBookId);
        if(userBookEntity==null){
            return null;
        }
        if(userBookEntity.getIsBookReturned().equals(EStatus.INACTIVE.getCode())){
            return null;
        }
        userBookEntity.setIsBookReturned(EStatus.INACTIVE.getCode());
        userBookRepository.save(userBookEntity);
        bookService.updateAvailability(userBookEntity.getBookId(),EStatus.INACTIVE.getCode());
        return userBookEntity;
    }
}
