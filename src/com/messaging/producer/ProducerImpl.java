package com.messaging.producer;

import com.messaging.exception.QueueFullException;
import com.messaging.sevice.MessageService;
import com.messaging.dao.Message;

/**
 * Created by riddhish on 19/01/17.
 */
public class ProducerImpl implements Producer {
    @Override
    public void send(Message message, MessageService service) {
        try {
            service.addMessageToQueue(message);
        } catch (QueueFullException e) {
            System.out.println("Could not add message : " + e.getMessage());
        }
    }
}
