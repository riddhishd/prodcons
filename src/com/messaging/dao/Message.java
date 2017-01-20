package com.messaging.dao;

/**
 * Created by riddhish on 19/01/17.
 */
public class Message {
    Topic topic;
    Payload payload;

    public Message(Payload payload, Topic topic) {
        this.payload = payload;
        this.topic = topic;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
