package project.ip.ecommerce.service;


import project.ip.ecommerce.entity.EmailDetails;

public interface EmailService {


    // Add this method to the interface
    String sendTokenMail(EmailDetails details);
}
