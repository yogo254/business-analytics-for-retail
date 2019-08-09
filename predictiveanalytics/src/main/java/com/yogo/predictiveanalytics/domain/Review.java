package com.yogo.predictiveanalytics.domain;

import java.util.Date;

public class Review {

    private String id;
    private String orderId;
    private int reviewScore;
    private String reviewComment;
    private Date creationDate;
    private Date answerDate;


    public Review() {
    }

    public Review(String id, String orderId, int reviewScore, String reviewComment, Date creationDate, Date answerDate) {
        this.id = id;
        this.orderId = orderId;
        this.reviewScore = reviewScore;
        this.reviewComment = reviewComment;
        this.creationDate = creationDate;
        this.answerDate = answerDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }
}
