package com.example.userapi.service;

import com.example.userapi.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    Email email = new Email();

    List<Email> emailList = new ArrayList<>();

    public void send(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("your-sender-email@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);

        System.out.println("Message sent successfully");
    }

    public List<Email> addEmail(Email email) {
        Integer maxSize = emailList == null ? 0 : emailList.size();
        List<Email> e = emailList != null ? new ArrayList<>(emailList) :  new ArrayList<>();

        if(email == null){
            return  null;
        }else{
            email = new Email(
                    maxSize + 1 ,
                    email.to,
                    email.from,
                    email.subject,
                    email.content);
            e.add(email);

            System.out.println("adding email {}"+ maxSize);
            emailList = e;

        }

        return emailList;
    }

    public List<Email> getEmailList() {
        List<Email> e = emailList != null ? new ArrayList<>(emailList) :  new ArrayList<>();

        return e;
    }

    }