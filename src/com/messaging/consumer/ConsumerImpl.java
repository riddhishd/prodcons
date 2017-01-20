package com.messaging.consumer;

import com.messaging.dao.Payload;
import com.messaging.sevice.MessageService;
import com.messaging.dao.Topic;

/**
 * Created by riddhish on 19/01/17.
 */
public class ConsumerImpl extends Consumer{

    public ConsumerImpl(String consumerID, Topic topic, MessageService messageService) {
        super(consumerID, topic, messageService);
    }

    @Override
    public void consume() {
        while(true) {
            Payload payload = messageService.getNextMessage(consumerID, topic);
            if(payload != null) {
                System.out.println("Consumer ID : " + consumerID + "\tTopic :" + topic + "\tPayload : " + payload.getData());
            }
        }
    }
}
