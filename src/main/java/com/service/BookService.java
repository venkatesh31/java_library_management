package com.service;

import com.entity.BookEntity;
import com.enums.EStatus;
import com.repository.AuthorRepository;
import com.repository.BookRepository;
import com.repository.PublisherRepository;
import com.request.BookRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public List<BookEntity> getAll(){
        return bookRepository.findAll();
    }

    public void updateAvailability(Integer bookId,Integer availabilty){
        BookEntity bookEntity = bookRepository.findByBookId(bookId);
        if(bookEntity==null){
            return ;
        }
        bookEntity.setIsAvailable(availabilty);
        bookRepository.save(bookEntity);


    }

    public List<BookEntity> searchBook(String searchText){

        List<BookEntity> bookEntityList =new ArrayList<>();
         searchText = "%"+searchText+"%";
        List<BookEntity> bookList = bookRepository.findByNameLikeOrCategoryLike(searchText,searchText);
        if(bookList!=null && !bookList.isEmpty()){
            bookEntityList.addAll(bookList);
        }
        List<Integer> authorIdList = authorRepository.findAuthorIdByNameLike(searchText);
        List<Integer> publisherIdList = publisherRepository.findPublisherIdByNameLike(searchText);
        if(authorIdList!=null && !authorIdList.isEmpty()){
            List<BookEntity> bookAuthor = bookRepository.findDistinctByAuthorIdIn(authorIdList);

            if(bookAuthor!=null && !bookAuthor.isEmpty()){
                bookEntityList.addAll(bookAuthor);
            }
        }
        if(publisherIdList!=null && !publisherIdList.isEmpty()){
            List<BookEntity> bookPublisher = bookRepository.findDistinctByPublisherIdIn(publisherIdList);
            if(bookPublisher!=null && !bookPublisher.isEmpty()){
                bookEntityList.addAll(bookPublisher);
            }

        }
        Set<BookEntity> set = new HashSet<>(bookEntityList);
        bookEntityList.clear();
        bookEntityList.addAll(set);
        return bookEntityList;

    }

    public BookEntity add(BookRequest bookRequest){
        BookEntity bookEntity = bookRepository.findByBookId(bookRequest.getBookId());
        if(bookEntity==null){
             bookEntity = new BookEntity();
        }
        bookEntity.setAuthorId(bookRequest.getAuthorId());
        bookEntity.setPublisherId(bookRequest.getPublisherId());
        bookEntity.setName(bookRequest.getName());
        bookEntity.setCategory(bookRequest.getCategory());
        bookEntity.setDescription(bookRequest.getDescription());
        bookEntity.setIsAvailable(EStatus.INACTIVE.getCode());
        bookEntity.setImageUrl(bookRequest.getImageUrl());
        bookRepository.save(bookEntity);
        return bookEntity;
    }

    public BookEntity edit(BookRequest bookRequest){
        BookEntity bookEntity = bookRepository.findByBookId(bookRequest.getBookId());
        if(bookEntity==null){
            return null;
        }
        bookEntity.setAuthorId(bookRequest.getAuthorId());
        bookEntity.setPublisherId(bookRequest.getPublisherId());
        bookEntity.setName(bookRequest.getName());
        bookEntity.setCategory(bookRequest.getCategory());
        bookEntity.setDescription(bookRequest.getDescription());
        bookEntity.setImageUrl(bookRequest.getImageUrl());

        return bookEntity;
    }
}
