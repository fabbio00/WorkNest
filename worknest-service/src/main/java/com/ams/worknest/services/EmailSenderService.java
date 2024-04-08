package com.ams.worknest.services;

public interface EmailSenderService {

    void sendSimpleMessage(String to, String subject, String text);

}
