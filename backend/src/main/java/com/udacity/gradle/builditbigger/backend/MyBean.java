package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String jokeText;

    public String getJokeText() {
        return jokeText;
    }

    public void setData(String jokeText) {
        this.jokeText = jokeText;
    }
}