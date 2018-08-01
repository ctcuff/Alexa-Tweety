package com.camtech.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.camtech.model.Attributes;
import com.camtech.model.Intents;

import java.util.Map;
import java.util.Optional;

/**
 * A built-in intent that triggers when the user says:
 * "Repeat that", "Could you repeat that", etc...
 * */
public class RepeatIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Intents.AMAZON_REPEAT));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
        String response = "I'm sorry, there is noting to repeat";
        // If the stored attribute isn't null, this means that Alexa just
        // spoke a message, so we'll retrieve it from the session attribute
        if (sessionAttributes.get(Attributes.CURRENT_DIRECT_MESSAGE) != null) {
            response = (String) sessionAttributes.get(Attributes.CURRENT_DIRECT_MESSAGE);
        }

        return handlerInput.getResponseBuilder()
                .withShouldEndSession(false)
                .withSpeech(response)
                .withSimpleCard("Direct Message", response)
                .build();
    }
}
