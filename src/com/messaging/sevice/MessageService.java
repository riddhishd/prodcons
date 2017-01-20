package com.messaging.sevice;

import com.messaging.dao.CustomQueue;
import com.messaging.dao.Message;
import com.messaging.dao.Payload;
import com.messaging.dao.Topic;
import com.messaging.exception.QueueFullException;
import com.messaging.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by riddhish on 19/01/17.
 */
public class MessageService {

    private static Map<String, Integer> consKeyToPayloadIndexMap = new HashMap();

    private static Map<Topic, CustomQueue<Payload>> topicToMessageMap = new HashMap<>();

    public void addMessageToQueue(Message message) throws QueueFullException {
        if(topicToMessageMap.containsKey(message.getTopic())) {
            topicToMessageMap.get(message.getTopic()).add(message.getPayload());
        } else {
            CustomQueue<Payload> list = new CustomQueue<Payload>();
            list.add(message.getPayload());
            topicToMessageMap.put(message.getTopic(), list);
        }
    }

    public Payload getNextMessage(String consumerID, Topic topic) {
        String consumerKey = Utils.getConsumerKey(consumerID, topic);


        /** Get message if consumer and topic already exists*/
        if(consKeyToPayloadIndexMap.containsKey(consumerKey) && topicToMessageMap.containsKey(topic)) {

            Integer currentIndex = consKeyToPayloadIndexMap.get(consumerKey);
            return getPayloadForConsumerKeyAtIndex(topic, consumerKey, currentIndex + 1);

        }
        /** Create consumer entry if topic exists and consumer does not exist, assume start with earliest message */
        else if (!consKeyToPayloadIndexMap.containsKey(consumerKey) && topicToMessageMap.containsKey(topic)) {

            consKeyToPayloadIndexMap.put(consumerKey, 0);
            Payload nextMessage = getPayloadForConsumerKeyAtIndex(topic, consumerKey, 0);
            return nextMessage;

        } /** Topic is not created yet, return null */
        else {
            return null;
        }
    }

    public void clearConsumer(String consumerID, Topic topic) {
        String consumerKey = Utils.getConsumerKey(consumerID, topic);
        if(consKeyToPayloadIndexMap.containsKey(consumerKey)) {
            consKeyToPayloadIndexMap.remove(consumerKey);
        }
    }
    private Payload getPayloadForConsumerKeyAtIndex(Topic topic, String consumerKey, Integer currentIndex) {
        CustomQueue<Payload> payloadList = topicToMessageMap.get(topic);
        /** If the size is great such that there at least 1 more element */
        if(payloadList.getSize() > currentIndex) {
            Payload nextMessage = payloadList.get(currentIndex);
            consKeyToPayloadIndexMap.put(consumerKey, currentIndex);
            return nextMessage;
        } else {
            return null;
        }
    }

}
