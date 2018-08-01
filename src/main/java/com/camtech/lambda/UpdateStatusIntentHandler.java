package com.camtech.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.*;
import com.amazon.ask.request.Predicates;
import com.camtech.model.Intents;
import com.camtech.utils.TwitterUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.Map;
import java.util.Optional;

/**
 * Triggers when the user says:
 * "Ask tweety to update my status to {status}", or
 * "update my status to {status}"
 * */
public class UpdateStatusIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Intents.UPDATE_STATUS));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        Request request = handlerInput.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        // The slot {status}, which is spoken in the UpdateStatusIntent,
        // contains the text we need to actually update the status
        Map<String, Slot> slots = intent.getSlots();
        String message = slots.get(Intents.SLOT_STATUS).getValue();

        Twitter twitter = TwitterUtils.getTwitter();
        Status status = null;
        try {
            status = twitter.updateStatus(message);
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        String response = status != null
                ? "Alright, I've updated your status to " + status.getText()
                : "An error occurred while updating your status";

        return handlerInput.getResponseBuilder()
                .withSpeech(response)
                .withSimpleCard("Status update", response)
                .build();
    }
}
