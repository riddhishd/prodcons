package com.messaging.consumer;

import com.messaging.sevice.MessageService;
import com.messaging.dao.Topic;

/**
 * Created by riddhish on 19/01/17.
 */
public abstract class Consumer {
    String consumerID;
    Topic topic;
    MessageService messageService;

    public Consumer(String consumerID, Topic topic, MessageService messageService) {
        this.consumerID = consumerID;
        this.topic = topic;
        this.messageService = messageService;
    }

    public abstract void consume();

}
