package com.telegrambot.bot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="services")
@Data
@ToString
public class Service {
    @Id
    @Column(name = "service_id")
    private int serviceId;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "service_cost")
    private String serviceCost;
}
