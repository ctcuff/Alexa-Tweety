package com.camtech.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.camtech.model.Intents;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

/**
 * Triggers when you say "Alexa stop" or "Alexa cancel"
 * */
public class CancelAndStopIntent implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(intentName(Intents.AMAZON_STOP).or(intentName(Intents.AMAZON_CANCEL)));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        return handlerInput.getResponseBuilder()
                .withSpeech("Goodbye")
                .withSimpleCard("Tweety", "Goodbye")
                .build();
    }
}
