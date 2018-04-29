package com.udacity.gradle.builditbigger.backend;

import it.redlor.javalibrary.JavaJokes;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private JavaJokes javaJokes;

    public MyBean() {
        this.javaJokes = javaJokes;
    }

    public String getJoke() {
        return javaJokes.getRandomJoke();
    }


}