package com.messaging.dao;

import com.messaging.exception.QueueFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by riddhish on 19/01/17.
 */
public class CustomQueue<P> {

    List<P> queue = Collections.synchronizedList(new ArrayList<P>());

    final static int MAX_SIZE = 100;

    public void add(P payload) throws QueueFullException {
        if(queue.size() == MAX_SIZE) {
            throw new QueueFullException();
        } else {
            queue.add(payload);
        }

    }

    public int getSize() {
        return queue.size();
    }

    public P get(int i) {
        return (P) queue.get(i);
    }
}
