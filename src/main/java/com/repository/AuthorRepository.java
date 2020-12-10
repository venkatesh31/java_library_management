package com.repository;


import com.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {

    AuthorEntity findByAuthorId(Integer authorId);

    @Query(value = "SELECT author_id FROM `author` where name like :text", nativeQuery = true)
    List<Integer> findAuthorIdByNameLike(@Param("text") String text);


    List<AuthorEntity> findAll();

}