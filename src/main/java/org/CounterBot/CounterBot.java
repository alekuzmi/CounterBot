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
        return System.getenv("COUNTER_BOT_NAME");
    }

    @Override
    public String getBotToken() {
        String token = System.getenv("COUNTER_BOT_TOKEN");
        return token;

    }

    @Override
    public void onUpdateReceived(Update update) {



        if(update.hasMessage()){
            Message message = update.getMessage();
            stat.setIdName(message.getFrom().getId(), message.getFrom().getUserName());
            stat.incCount(update.getMessage().getChatId(), message.getFrom().getId());

            if(update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("Hello")) {
                    try {
                        execute(sendSex(update.getMessage().getChatId()));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else if (update.getMessage().getText().equals("/help")) {
                    sendMsg(update.getMessage().getChatId(), "Cry, bitch");
                } else if (message.getText().equals("/status")) {

                    sendMsg(update.getMessage().getChatId(), stat.getCount(update.getMessage().getChatId(), message.getFrom().getId()));

                }
            }

        }else if(update.hasCallbackQuery()){
            try {
                SendMessage ans = new SendMessage();
                ans.setText(update.getCallbackQuery().getData());
                ans.setChatId(String.valueOf(update.getCallbackQuery().getMessage().getChatId()));
                execute(ans);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
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

    protected void sendMsg(Long ChatId, String text) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(ChatId.toString());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
