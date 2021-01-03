package org.CounterBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CounterBot extends TelegramLongPollingBot {
    Status stat;

    public CounterBot() {
        this.stat = new Status();
    }


    @Override
    public String getBotUsername() {
        return "CounterBot";
    }

    @Override
    public String getBotToken() {
        return "1335488648:AAE8LNv_dUlowl5sb8TCbUHNxtELtsvUMak";
    }

    @Override
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message == null || !message.hasText()) {
            return;
        }
        stat.setIdName(message.getFrom().getId(), message.getFrom().getUserName());
        stat.incCount(update.getMessage().getChatId(), message.getFrom().getId());
        if (message.getText().equals("/help"))
            sendMsg(message, "Cry, bitch");
//            else if (update.getMessage().getText().equals("/start")) {
//                try {
//                    execute(sendSex(update.getMessage().getChatId()));
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
        else if (message.getText().equals("/status")) {

            sendMsg(message, stat.getCount(update.getMessage().getChatId(), message.getFrom().getId()));
        }

//
//            else if(update.hasCallbackQuery()){
//                SendMessage here = new SendMessage();
//                here.setText(update.getCallbackQuery().getData());
//                here.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
//                try {
//                    execute(here);
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            else sendMsg(message, "Hy");

    }


    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public SendMessage sendSex(long chatId) {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();

        inlineKeyboardButton1.setText("Male");
        inlineKeyboardButton1.setCallbackData("Button \"Male\" has been pressed");
        inlineKeyboardButton2.setText("Female");
        inlineKeyboardButton2.setCallbackData("Button \"Female\" has been pressed");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage here = new SendMessage();
        here.setChatId(String.valueOf(chatId));
        here.setText("Indicate your gender:");
        here.setReplyMarkup(inlineKeyboardMarkup);

        return here;
    }


}
