package com.example.onlinebookshop.model.dto;

import java.util.Date;

public class RatingDTO {
    private String username;
    private Long bookId;
    private String bookName;
    private byte[] photo;
    private Date ratingDate;
    private Float levelRating;
    private String comment;

    public RatingDTO(String username, Long bookId, String bookName, byte[] photo, Date ratingDate, Float levelRating, String comment) {
        this.username = username;
        this.bookId = bookId;
        this.bookName = bookName;
        this.photo = photo;
        this.ratingDate = ratingDate;
        this.levelRating = levelRating;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(Date ratingDate) {
        this.ratingDate = ratingDate;
    }

    public Float getLevelRating() {
        return levelRating;
    }

    public void setLevelRating(Float levelRating) {
        this.levelRating = levelRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
