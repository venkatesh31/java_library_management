package com.repository;


import com.entity.UserBookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookRepository extends CrudRepository<UserBookEntity, Integer> {

    UserBookEntity findByUserBookId(Integer userBookId);

    UserBookEntity findByBookIdAndIsBookReturned(Integer bookId, Integer returnedStatus);

    List<UserBookEntity> findByUserIdAndIsBookReturned(Integer userId, Integer returnedStatus);

    List<UserBookEntity> findByUserId(Integer userId);

}