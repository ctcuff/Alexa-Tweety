package com.camtech.lambda;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.camtech.test.lambda.HelloWorldIntentHandler;

/**
 * The entry point for the Alexa skill. When it's uploaded
 * as a Lambda function, it will look for this file and use
 * is a the main handler.
 * */
public class MainStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelAndStopIntent(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new UpdateStatusIntentHandler(),
                        new DirectMessageIntentHandler(),
                        new RepeatIntentHandler(),
                        new NoIntentHandler())
                .build();
    }
    public MainStreamHandler() {
        super(getSkill());

    }
}
