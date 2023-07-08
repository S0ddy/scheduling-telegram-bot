package com.telegrambot.bot.repository;

import com.telegrambot.bot.entity.Service;
import org.springframework.data.repository.CrudRepository;

public interface ServiceRepository extends CrudRepository<Service, Integer> {
}
