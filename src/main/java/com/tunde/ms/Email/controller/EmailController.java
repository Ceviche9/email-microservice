package com.tunde.ms.Email.controller;

import com.tunde.ms.Email.dtos.EmailDTO;
import com.tunde.ms.Email.model.Email;
import com.tunde.ms.Email.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        this.emailService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(email);
    }
}
