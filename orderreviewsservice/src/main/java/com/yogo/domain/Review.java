package com.yogo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document
public class Review {
    @Id
    @Field("review_id")
    private String reviewId;
    @Field("order_id")
    private String orderId;
    @Field("review_score")
    private int reviewScore;
    @Field("reviewComment")
    private String reviewComment;
    @Field("creation_date")
    private Date creationDate;
    @Field("answer_date")
    private Date answerDate;

    public Review() {
    }

    public Review(String reviewId, String orderId, int reviewScore, String reviewComment, Date creationDate, Date answerDate) {
        this.reviewId = reviewId;
        this.orderId = orderId;
        this.reviewScore = reviewScore;
        this.reviewComment = reviewComment;
        this.creationDate = creationDate;
        this.answerDate = answerDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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
