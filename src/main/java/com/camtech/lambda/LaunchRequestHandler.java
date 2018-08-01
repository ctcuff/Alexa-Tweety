package com.camtech.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

/**
 * A built-in intent that triggers when the user says:
 * "Alexa, open tweety"
 * */
public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = "Welcome to Tweety, you can tell Tweety to tweet a message or read your latest message";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Welcome", speechText)
                .withReprompt(speechText)
                .build();
    }
}
