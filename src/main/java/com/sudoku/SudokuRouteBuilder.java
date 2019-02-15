package com.sudoku;

import org.apache.camel.builder.RouteBuilder;

public class SudokuRouteBuilder extends RouteBuilder {

    public void configure() {
        from("telegram:bots/681997552:AAG-Ht8fMywQu6JtjerNdTPwZSrF8G1jBJY")
                .bean(TelegramBot.class)
                .log("Received Telegram message. Forwarding it to MQTT-Topic.")
                .to("mqtt:sudoku?publishTopicName=sudoku/OUT");

        from("mqtt:sudoku?subscribeTopicNames=sudoku/IN")
                .transform(body().convertToString())
                .log("Received fieldConfiguration via MQTT-Topic. Forwarding it to Telegram Chat.")
                .to("telegram:bots/681997552:AAG-Ht8fMywQu6JtjerNdTPwZSrF8G1jBJY?chatId=-1001457965485");

    }
}
