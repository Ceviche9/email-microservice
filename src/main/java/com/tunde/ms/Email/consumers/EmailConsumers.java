package com.tunde.ms.Email.consumers;

import com.tunde.ms.Email.dtos.EmailDTO;
import com.tunde.ms.Email.model.Email;
import com.tunde.ms.Email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumers {
    private static final Logger logger = LoggerFactory.getLogger(EmailConsumers.class);

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        emailService.sendEmail(email);
        logger.info("Email staus: " + email.getStatusEmail().toString());
    }

}
