package com.repository;


import com.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    BookEntity findByBookId(Integer bookId);

    List<BookEntity> findByNameLikeOrCategoryLike(String name,String category);

    List<BookEntity> findDistinctByAuthorIdIn(List<Integer> authorIdList);

    List<BookEntity> findDistinctByPublisherIdIn(List<Integer> publicationIdList);

    List<BookEntity> findAll();

}