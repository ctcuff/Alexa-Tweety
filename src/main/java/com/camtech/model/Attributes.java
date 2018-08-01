package com.camtech.model;

import java.util.Map;

public final class Attributes {

    private Attributes() {
    }

    /**
     * Used to get the last direct message read by Alexa so we can
     * keep track of which message we're on, in case the user says, 'next'
     */
    public static final String DIRECT_MESSAGE_INDEX = "directMessageIndex";
    /**
     * Used to get the last direct message spoken by Alexa in case the user
     * asks Alexa to repeat it
     */
    public static final String CURRENT_DIRECT_MESSAGE = "currentDirectMessage";

    /**
     * Used to check if the current session attributes contain the value(s) we're looking for
     */
    public static boolean hasKey(Map<?, ?> map, String key) {
        return map.get(key) != null;
    }
}
