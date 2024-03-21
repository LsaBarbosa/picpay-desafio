package com.santanna.picpaydesafio.service;

import com.santanna.picpaydesafio.dto.NotificationDTO;
import com.santanna.picpaydesafio.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email,message);
//
//        String url = "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6";
//        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(url, notificationRequest,String.class);
//
//        boolean isNotificationResponseOk = notificationResponse.getStatusCode() == HttpStatus.OK;
//        if (!isNotificationResponseOk){
//            System.out.println("Erro ao enviar notificação");
//            throw new Exception("Serviço de notificação fora do ar");
//        }
        System.out.println("Notificação enviada");
    }
}
