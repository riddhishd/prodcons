package com.messaging.producer;

import com.messaging.dao.Message;
import com.messaging.sevice.MessageService;

/**
 * Created by riddhish on 19/01/17.
 */
public interface Producer {

     void send(Message message, MessageService service);

}
