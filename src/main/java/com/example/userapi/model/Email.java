package com.example.userapi.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Email model")

public class Email {

    public Integer id;
    @ApiModelProperty(notes = "Recipient", example = "test@gmail.co m")
    public String to;
    @ApiModelProperty(notes = "Sender", example = "testreceipient@gmail.com")
    public String from;
    @ApiModelProperty(notes = "Subject of the email", example = "")
    public String subject;

    @ApiModelProperty(notes = "Content of the email", example = "")
    public String content;


    public Email() {
    }

    public Email(Integer id, String to, String from, String subject, String content) {
        this.id = id;
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.content = content;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}