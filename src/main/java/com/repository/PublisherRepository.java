package com.repository;

import com.entity.PublisherEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublisherRepository extends CrudRepository<PublisherEntity, Integer> {

    PublisherEntity findByPublisherId(Integer publisherId);

    @Query(value = "SELECT publisher_id FROM `publisher` where name like :text", nativeQuery = true)
    List<Integer> findPublisherIdByNameLike(@Param("text") String text);


    List<PublisherEntity> findAll();

}