package com.camtech.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.camtech.model.Intents;

import java.util.Optional;

/**
 * This is a built-in intent that triggers when the user says:
 * "help", "help me", and so on
 * */
public class HelpIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Intents.AMAZON_HELP));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "With Tweety, you can update your status on Twitter or read your latest direct message";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Tweety", speechText)
                .withReprompt(speechText)
                .build();
    }
}
