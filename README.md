# Alexa-Tweety
A Twitter skill for Alexa that allows users to update their status and read their direct messages.

This is a **WIP** skill for Alexa. Users can update their status or read the direct messages from Twitter. The code in the server folder is hosted as a Heroku app. When Alexa asks the user to link their account (via the Android/iOs companion app), clicking on the link directs to the Heroku-hosted site. Alexa asks the server for an access token and access token secret which is then used in the Java code, assuming authentication was successful. As of now, account linking isn't working (the server doesn't *actually* return the tokens, hey, it's a **WIP**, cut me some slack) so if you want to test this yourself, you'll want to do the following:

0. Head over to [Twitter and create an app](https://apps.twitter.com/). Make sure to take note of the consumer keys
1. Clone this repo and create the following `Keys.java` file:
```java
package com.camtech.utils;

final class Keys {
    /**
     * Keys for the application
     * */
    static final String CONSUMER_KEY = "consumer key here";
    static final String CONSUMER_KEY_SECRET = "consumer secret here";

    /**
     * Keys for a specific account
     * */
    static class Debug {
        static final String CONSUMER_KEY = "consumer key here";
        static final String CONSUMER_KEY_SECRET = "consumer secret here";
        static final String ACCESS_TOKEN = "consumer secret here";
        static final String ACCESS_TOKEN_SECRET = "consumer secret here";
    }
}
```

Note that in the above file, `Keys { ... }` contains the keys for the twitter application, `Keys.Debug { ... }` contains keys pertaining a specific account for testing. You can just make one twitter app (see step 0) and use the same consumer and consumer secret keys.

2. Visit [this link](https://howtodoinjava.com/aws/amazon-alexa-custom-skill-tutorial/) for a pretty good tutorial on how to upload the skill to AWS Lambda. You'll need to upload the jar file to AWS so just execute the command `assembly:assembly -DdescriptorId=jar-with-dependencies package` in the root dir of the project to get a jar file.

3. After you've done all of the above, head to [the Amazon Dev Console](https://developer.amazon.com/alexa/console/ask/) to create/test the skill. See [here](https://developer.amazon.com/docs/devconsole/about-the-developer-console.html) for another pretty good tutorial.

# Sample Conversation
**User**: "Alexa, open tweety."

**Alexa**: "Welcome to tweety..."

**User**: "Update my status to *'hello world'*".

**Alexa**: "Alright, I've updated your status to *'hello world.'*"

**User**: "Read my last message."

**Alexa**: "Message from someDude24 was received at 5:24 AM, *'hey dude, what's up?'*"
