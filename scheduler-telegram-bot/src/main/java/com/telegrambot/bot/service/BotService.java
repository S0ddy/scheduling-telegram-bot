package com.telegrambot.bot.service;

import com.telegrambot.bot.entity.Service;
import com.telegrambot.bot.entity.User;
import com.telegrambot.bot.exception.TelegramBotException;
import com.telegrambot.bot.repository.ServiceRepository;
import com.telegrambot.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class BotService extends TelegramLongPollingBot {
    public final int NUM_OF_COLUMN_FOR_SERVICES = 2;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public void onUpdateReceived(Update update) {
        saveUserInfo(update);

        int session = getSessionStep(update);
        Long chatId = update.getMessage().getChatId();

        try {
            switch (session) {
                //if session step 1 - suggest list of services
                case 1:
                    provideListOfServices(chatId);
                    break;
                //if session step 2 - suggest day picker
                case 2:
                    provideDayPicker();
                    break;
                //if session step 3 - suggest the time
                case 3:
                    provideTimePicker();
                    break;
                default:
                    restartSession();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void restartSession() {
    }

    private void provideTimePicker() {
    }

    private void provideDayPicker() {
    }

    private void provideListOfServices(Long chatId) throws Exception {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Services");
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();
        List<Service> services = (List<Service>) serviceRepository.findAll();
        if(services.isEmpty()) {
            throw new Exception("no services found");
        }
        int numberOfRows = services.size()/NUM_OF_COLUMN_FOR_SERVICES;
        for(int i = 0; i < numberOfRows; i++) {
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // Set each button, you can also use KeyboardButton objects if you need something else than text
            for(int j = 0; j < NUM_OF_COLUMN_FOR_SERVICES; j++) {
                String name = String.valueOf(services.get(i+j).getServiceName());
                String cost = String.valueOf(services.get(i+j).getServiceCost());
                row.add(name + " (" + cost + ")");
            }
            keyboard.add(row);
        }
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private int getSessionStep(Update update) {
        int session = 1;
        //check the session for user.
        //if no session - create new one
        return session;
    }

    private void saveUserInfo(Update update) {
        //get user from DB
        //save/update user if not exist
        User user = new User(update.getMessage().getFrom().getId().toString(),
                update.getMessage().getFrom().getUserName(),
                update.getMessage().getFrom().getFirstName(),
                update.getMessage().getFrom().getLastName());
        userRepository.save(user);
    }

    @Override
    public String getBotUsername() {
        return System.getenv("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return System.getenv("BOT_TOKEN");
    }

}
