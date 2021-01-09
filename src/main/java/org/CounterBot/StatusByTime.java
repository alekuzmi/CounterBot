package org.CounterBot;

import org.CounterBot.tg.CounterBot;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;




public class StatusByTime extends TimerTask implements Runnable {
    Long timeSent;




    @Override
    public void run() {

//            if (timeSent >= System.currentTimeMillis() - 15000) {

                CounterBot status = CounterBot.getInstance();

                status.sendAllStatistic();
//                timeSent = System.currentTimeMillis();
//            } else return;

        System.out.println("hy");


    }
}
