package com.ayoub.controle.notification.kafka.config;

import com.ayoub.controle.notification.model.Notification;
import com.ayoub.controle.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consumeNotification(String message) {
        System.out.println("Message reçu depuis Kafka : " + message);

        // Convertir le message en objet Notification (si nécessaire)
        Notification notification = new Notification();
        notification.setMessage(message);

        // Sauvegarder ou traiter la notification
        notificationService.saveNotification(notification);
    }
}
