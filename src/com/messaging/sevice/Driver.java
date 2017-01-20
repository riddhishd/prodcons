package com.messaging.sevice;

import com.messaging.consumer.ConsumerImpl;
import com.messaging.dao.Message;
import com.messaging.dao.Payload;
import com.messaging.dao.Topic;
import com.messaging.producer.ProducerImpl;

/**
 * Created by riddhish on 19/01/17.
 */
public class Driver {
    public static void main(String[] args) {
        MessageService messageService = new MessageService();

        ProducerImpl producer = new ProducerImpl();

        producer.send(new Message(new Payload("data0"), Topic.TOPIC1), messageService);
        producer.send(new Message(new Payload("data1"), Topic.TOPIC2), messageService);
        producer.send(new Message(new Payload("data2"), Topic.TOPIC3), messageService);
        producer.send(new Message(new Payload("data3"), Topic.TOPIC1), messageService);
        producer.send(new Message(new Payload("data4"), Topic.TOPIC2), messageService);
        producer.send(new Message(new Payload("data5"), Topic.TOPIC1), messageService);
        producer.send(new Message(new Payload("data6"), Topic.TOPIC2), messageService);
        producer.send(new Message(new Payload("data7"), Topic.TOPIC1), messageService);
        producer.send(new Message(new Payload("data8"), Topic.TOPIC3), messageService);
        producer.send(new Message(new Payload("data9"), Topic.TOPIC1), messageService);


        final ConsumerImpl consumer1_1 = new ConsumerImpl("1", Topic.TOPIC1, messageService);
        final ConsumerImpl consumer1_2 = new ConsumerImpl("2", Topic.TOPIC1, messageService);
        final ConsumerImpl consumer2 = new ConsumerImpl("1", Topic.TOPIC2, messageService);
        final ConsumerImpl consumer3 = new ConsumerImpl("1", Topic.TOPIC3, messageService);

        Thread t = new Thread(new Runnable() {
            public void run()
            {
                consumer1_1.consume();

            }
        });
        t.start();
        Thread t1 = new Thread(new Runnable() {
            public void run()
            {
                consumer1_2.consume();
            }
        });
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            public void run()
            {
                consumer2.consume();

            }
        });
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            public void run()
            {
                consumer3.consume();

            }
        });
        t3.start();
    }
}
