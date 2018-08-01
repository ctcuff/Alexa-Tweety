package com.camtech.lambda;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.camtech.model.Attributes;
import com.camtech.model.Intents;
import com.camtech.utils.TwitterUtils;
import twitter4j.*;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Optional;

/**
 * Triggers when you say:
 * "read my latest message",
 * "read my last message",
 * "to read my last message",
 * "get my last message",
 * "read my messages"
 *
 * as well as:
 * "how many messages do i have",
 * "what's my message count",
 * "what is my message count"
 * */
public class DirectMessageIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName(Intents.DIRECT_MESSAGE)
                .or(Predicates.intentName(Intents.DIRECT_MESSAGE_COUNT))
                .or(Predicates.intentName(Intents.AMAZON_YES)));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String accessToken = getAccessToken(handlerInput);
        // Have we authenticated the user? If not, display a card asking
        // them to link their account and end this session
        if (accessToken == null) {
            return handlerInput.getResponseBuilder()
                    .withLinkAccountCard()
                    .withShouldEndSession(false)
                    .withSpeech("Please login to your Twitter account")
                    .build();
        }
        int messageIndex = 0;
        // Get the saves session attributes if they exist. This will contain
        // the index of the last message read by Alexa
        Map<String, Object> attrs = handlerInput.getAttributesManager().getSessionAttributes();
        if (Attributes.hasKey(attrs, Attributes.DIRECT_MESSAGE_INDEX)) {
            // If they do exist, we'll get the index of the last message in the
            // response list read by alexa
            messageIndex = (int) attrs.get(Attributes.DIRECT_MESSAGE_INDEX);
            // Only increment the index if the user responded with yes
            if (handlerInput.matches(Predicates.intentName(Intents.AMAZON_YES))) {
                messageIndex++;
            }
        } else {
            // This intent was just launched so we'll start reading from the first message
            attrs.put(Attributes.DIRECT_MESSAGE_INDEX, 0);
        }
        String response = "An error occurred while retrieving your messages";
        ResponseList<DirectMessage> messages = null;
        Twitter twitter = TwitterUtils.getTwitter();
        try {
            // This formats the date to appear as "Sun June 3, 2018 02:30 AM"
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMMM d, yyyy, hh:mm aaa");

            messages = twitter.getDirectMessages();
            DirectMessage dm = messages.get(messageIndex);
            String message = dm.getText();
            String sender = dm.getSenderScreenName();
            String date = formatter.format(dm.getCreatedAt());

            // This formats the date to appear as "Sun June 3, 2018, at 02:30 AM"
            date = new StringBuilder(date).insert(date.length() - 9, " at").toString();
            response = String.format("Message from %s was received at %s. %s", sender, date, message);
        } catch (TwitterException e) {
           response = e.getErrorMessage();
        }

        if (messages != null && messages.size() == 0) {
            response = "You don't have any messages";
        }
        attrs.put(Attributes.CURRENT_DIRECT_MESSAGE, response);
        attrs.put(Attributes.DIRECT_MESSAGE_INDEX, messageIndex);

        // The user asked for their message count so we only want Alexa
        // to speak the count and end the session
        if (handlerInput.matches(Predicates.intentName(Intents.DIRECT_MESSAGE_COUNT))) {
            response = messages != null
                    ? String.format("You have %d messages", messages.size())
                    : "An error occurred while retrieving your messages";
            return handlerInput.getResponseBuilder()
                    .withShouldEndSession(true)
                    .withSpeech(response)
                    .withSimpleCard("Direct Message", response)
                    .build();
        }
        if (messages != null && messages.size() > 1) {
            return handlerInput.getResponseBuilder()
                    .withShouldEndSession(false)
                    .withSpeech(response)
                    .withReprompt("Would you like to hear the next message?")
                    .withSimpleCard("Direct Message", response)
                    .build();
        } else {
            return handlerInput.getResponseBuilder()
                    .withShouldEndSession(true)
                    .withSpeech(response)
                    .withSimpleCard("Direct Message", response)
                    .build();
        }
    }

    private String getAccessToken(HandlerInput handlerInput) {
        return handlerInput
                .getRequestEnvelope()
                .getContext()
                .getSystem()
                .getUser()
                .getAccessToken();
    }
}
