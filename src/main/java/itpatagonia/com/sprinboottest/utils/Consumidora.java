package itpatagonia.com.sprinboottest.utils;

import itpatagonia.com.sprinboottest.model.Actor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Consumidora {
    public static void main(String[] args) {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor(1L, "Reeves", "Keanu", LocalDate.of(1970,12,12)));
        actorList.add(new Actor(2L, "Reeves1", "Keanu1", LocalDate.of(1990,11,11)));
        actorList.add(new Actor(3L, "Reeves2", "Keanu1", LocalDate.of(2015,10,10)));

        Consumer<Actor> actorConsumer = (actor) -> System.out.println("Hola Chicas");
        actorConsumer.accept(actorList.get(1));

        Consumer<String> actorConsumerAnonima = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        actorConsumerAnonima.accept("Hola Chicas 2 ");

        Consumer<String> actorConsumerString = (miobjetostring) -> System.out.println(miobjetostring);
        actorConsumerString.accept("Hola Chicas 3");
    }
}
