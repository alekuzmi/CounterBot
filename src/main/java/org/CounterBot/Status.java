package org.CounterBot;

import java.util.HashMap;
import java.util.Map;

public class Status {


    public Status() {
        this.statusChat = new HashMap<Long, Map<Integer , Integer>>();
    }

    public synchronized String getCount(long ChatId, int  userId) {

        return userId + " send " + statusChat.get(ChatId).get(userId) + " message";
    }

    public synchronized void incCount(long ChatId, int userId) {

        Map <Integer, Integer> statusUser= statusChat.getOrDefault(ChatId, new HashMap<Integer, Integer>(){{put(userId, 0);}});
        int count=statusUser.getOrDefault(userId, 0);
        statusUser.put(userId,count+1);
        statusChat.put(ChatId, statusUser);

    }

    Map<Long, Map<Integer, Integer>> statusChat;



}
