package com.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author_id", nullable = false)
    private Integer authorId;

    @Column(name = "publisher_id", nullable = false)
    private Integer publisherId;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "category", nullable = true)
    private String category;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "is_available", nullable = true)
    private Integer isAvailable;

    @OneToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private AuthorEntity author;

    @OneToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private PublisherEntity publisher;


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherEntity publisher) {
        this.publisher = publisher;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }
}