package org.CounterBot;

import org.CounterBot.tg.CounterBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(CounterBot.getInstance());

        } catch (TelegramApiException e) {
            e.printStackTrace();

        }

        Calendar today = Calendar.getInstance();


        Timer timer = new Timer();
        timer.schedule(new StatusByTime(), today.getTime(), TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS));





//        Timer t = new Timer();
//        StatusByTime status = new StatusByTime();
//        t.scheduleAtFixedRate(status, 0, 10000);
//







    }
}
