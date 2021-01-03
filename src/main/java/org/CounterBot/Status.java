package org.CounterBot;

import java.util.HashMap;
import java.util.Map;

public class Status {


    public Status() {
        this.status = new HashMap <Long, Integer>();
    }

    public synchronized int getCount(long ChatId) {
        return status.getOrDefault(ChatId, 0);
    }

    public synchronized void incCount(long ChatId) {
        if (status.containsKey(ChatId)) {
            int count = status.get(ChatId);
            status.put(ChatId, count+1);

        } else {
            status.put(ChatId, 1);
        }
    }

    Map<Long, Integer> status;



}
