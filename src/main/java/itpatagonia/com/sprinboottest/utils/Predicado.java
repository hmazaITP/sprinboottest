package itpatagonia.com.sprinboottest.utils;

import itpatagonia.com.sprinboottest.model.Actor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Predicado {
    public static void main(String[] args) {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor(1L, "Reeves", "Keanu", LocalDate.of(1970,12,12)));
        actorList.add(new Actor(2L, "Reeves1", "Keanu1", LocalDate.of(1990,11,11)));
        actorList.add(new Actor(3L, "Reeves2", "Keanu1", LocalDate.of(2015,10,10)));

        Predicate<Actor> predicado = actor1 -> actor1.getBirthday().getYear()<2020;
        System.out.println(predicado.test(actorList.get(2)));
    }
}
