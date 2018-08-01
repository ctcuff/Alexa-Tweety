package com.camtech.test.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.camtech.model.Intents;

import java.util.Optional;

/**
 * A Simple test intent that triggers when the user says:
 * "say hello", or "say hi world", etc. For more utterances, look
 * for {@code {"name": "HelloWorldIntent"...}} in
 * IntentSchema.json located in the speechAssets folder
 * */
public class HelloWorldIntentHandler implements RequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Intents.HELLO_WORLD));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Hello world";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Welcome", speechText)
                .build();
    }
}
