package com.service;

import com.entity.PublisherEntity;
import com.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<PublisherEntity> getAll(){
        return publisherRepository.findAll();
    }

    public PublisherEntity add(String name){
        PublisherEntity publisher= new PublisherEntity();
        publisher.setName(name);
        publisherRepository.save(publisher);
        return publisher;
    }
}
