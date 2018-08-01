package com.camtech.model;

public final class Intents {

    private Intents() {
    }

    /* Custom intents */
    public static final String HELLO_WORLD = "HelloWorldIntent";
    public static final String UPDATE_STATUS = "UpdateStatusIntent";
    public static final String DIRECT_MESSAGE = "DirectMessageIntent";
    public static final String DIRECT_MESSAGE_COUNT = "DirectMessageCountIntent";

    /* Intents provided by Amazon */
    public static final String AMAZON_HELP = "AMAZON.HelpIntent";
    public static final String AMAZON_STOP = "AMAZON.StopIntent";
    public static final String AMAZON_CANCEL = "AMAZON.CancelIntent";
    public static final String AMAZON_NEXT = "AMAZON.NextIntent";
    public static final String AMAZON_REPEAT = "AMAZON.RepeatIntent";
    public static final String AMAZON_YES = "AMAZON.YesIntent";
    public static final String AMAZON_NO = "AMAZON.NoIntent";

    /* Slots that map to values */
    public static final String SLOT_STATUS = "status";
}
