package org.CounterBot;

import org.CounterBot.tg.CounterBot;

import java.util.*;


public class StatusByTime extends TimerTask {
//    Long timeSent=System.currentTimeMillis();


    Calendar  calendarSend = new GregorianCalendar();
    public StatusByTime () {
        calendarSend = new GregorianCalendar();
        calendarSend.add(Calendar.DAY_OF_YEAR, -1);

    }








    @Override
    public void run() {


//            if (timeSent <= (System.currentTimeMillis() - 60000)) {
//
//                CounterBot status = CounterBot.getInstance();
//
//                status.sendAllStatistic();
//                timeSent = System.currentTimeMillis();
//            } else return;
        Calendar currentCalendar = new GregorianCalendar();

        if (currentCalendar.get(Calendar.DAY_OF_MONTH) > calendarSend.get(Calendar.DAY_OF_MONTH) &
                currentCalendar.get(Calendar.HOUR) == 0 &
                currentCalendar.get(Calendar.MINUTE)==6){

            CounterBot status = CounterBot.getInstance();
            status.sendAllStatistic();
            calendarSend=Calendar.getInstance();

        }






    }
}
