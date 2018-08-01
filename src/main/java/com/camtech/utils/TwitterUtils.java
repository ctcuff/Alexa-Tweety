package com.camtech.utils;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterUtils {

    public static Twitter getTwitter() {
        return new TwitterFactory(getConfig()).getInstance();
    }

    /**
     * Returns a {@link Twitter} object. Note that this will be called
     * after a user has successfully linked their Amazon Alexa account with
     * Twitter. If the link was successful, you can grab the accessToken and
     * accessTokenSecret from the Alexa session.
     */
    public static Twitter getTwitter(String accessToken, String accessTokenSecret) {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(Keys.CONSUMER_KEY, Keys.CONSUMER_KEY_SECRET);
        twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
        return twitter;
        // return new TwitterFactory(getConfig(accessToken)).getInstance();
    }

    /**
     * Helper method to build the configuration. This is mostly
     * used for debug testing as it's specific to a single account
     */
    private static Configuration getConfig() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(Keys.Debug.CONSUMER_KEY)
                .setOAuthConsumerSecret(Keys.Debug.CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(Keys.Debug.ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(Keys.Debug.ACCESS_TOKEN_SECRET);
        return cb.build();
    }
}
