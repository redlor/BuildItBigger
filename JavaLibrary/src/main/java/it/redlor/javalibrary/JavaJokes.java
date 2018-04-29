package it.redlor.javalibrary;

import java.util.Random;

public class JavaJokes {

    private String jokes[] = {
      "A Neapolitan Joke: O' ciuccio chiamm recchie long o' cavall\n" + "\n" + "(The donkey tells the horse it has long ears)" ,
      "An English joke: Marriage is an institution of three rings. Engagement ring, wedding ring and suffeRing.",
      "A French joke: C'est l'histoire de deux pommes de terre. Une d'elles se fait ecraser et l'autre s'ecrie 'Oh puree !'\n" +
        "\n" + "(This is the story of two potatoes. One of them is run over and the other one says 'Oh puree!')",
      "A Spanish joke: Cuales son las vacas mas perezosas? Vacaciones!\n" + "\n" + "(What's the laziest type of cow? A vacation!)",
      "A German joke: Drei in einem Buro und einer arbeitet? Zwei Beamte und ein Ventilator!\n" + "\n" +
        "(Three in the office, and one working? Two state officials and a fan!)"
    };

    public String getRandomJoke() {
        Random random = new Random();
        int randomValue = random.nextInt(jokes.length);
        return jokes[randomValue];
    }
}
