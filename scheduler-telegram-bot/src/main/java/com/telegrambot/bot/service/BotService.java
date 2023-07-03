package com.telegrambot.bot.service;

import com.telegrambot.bot.entity.User;
import com.telegrambot.bot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class BotService extends TelegramLongPollingBot {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onUpdateReceived(Update update) {

        User user = new User(update.getMessage().getFrom().getId().toString(),
                update.getMessage().getFrom().getUserName(),
                update.getMessage().getFrom().getFirstName(),
                update.getMessage().getFrom().getLastName());
        System.out.println(user);

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
