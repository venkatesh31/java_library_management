package com.service;

import com.entity.AuthorEntity;
import com.entity.PublisherEntity;
import com.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public AuthorEntity add(String name){
        AuthorEntity authorEntity= new AuthorEntity();
        authorEntity.setName(name);
        authorRepository.save(authorEntity);
        return authorEntity;
    }

    public List<AuthorEntity> getAll(){
        return authorRepository.findAll();
    }

}
