package project.ip.ecommerce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import project.ip.ecommerce.entity.EmailDetails;
import project.ip.ecommerce.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


    // Add this method to implement the new interface method
    // @Override
    // public String sendTokenMail(EmailDetails details) {
    //     try {
    //         SimpleMailMessage mailMessage = new SimpleMailMessage();
    //         mailMessage.setFrom(sender);
    //         mailMessage.setTo(details.getRecipient());
    //         mailMessage.setText("Your reset password code: " + details.getToken());
    //         mailMessage.setSubject("Reset Password");

    //         javaMailSender.send(mailMessage);
    //         return "Token Sent Successfully...";
    //     } catch (Exception e) {
    //         return "Error while Sending Token";
    //     }
    // }

    //reset v2
    @Override
public String sendTokenMail(EmailDetails details) {
    try {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getRecipient());
        
        // Assuming resetUrl is a variable containing the URL for the reset password page
        String resetUrl = "http://localhost:5173/reset-password?token=" + details.getToken();
        // Providing a clickable link instead of just the token
        mailMessage.setText("Click the link below to reset your password:\n" + resetUrl);
        mailMessage.setSubject("Reset Password");

        javaMailSender.send(mailMessage);
        return "Token Sent Successfully...";
    } catch (Exception e) {
        return "Error while Sending Token";
    }
}
}
