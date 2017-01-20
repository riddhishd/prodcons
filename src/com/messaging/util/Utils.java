package com.messaging.util;

import com.messaging.dao.Topic;

/**
 * Created by riddhish on 19/01/17.
 */
public class Utils {

    public static String getConsumerKey(String consumerID, Topic topic) {
        return consumerID + ":" + topic;
    }
}
