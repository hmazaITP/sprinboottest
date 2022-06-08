package itpatagonia.com.sprinboottest.utils;

import itpatagonia.com.sprinboottest.model.Actor;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Comparador {
    public static void main(String[] args) {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor(1L, "Reeves", "Keanu", LocalDate.of(1970,12,12)));
        actorList.add(new Actor(2L, "Reeves1", "Keanu1", LocalDate.of(1990,11,11)));
        actorList.add(new Actor(3L, "Reeves2", "Keanu1", LocalDate.of(2015,10,10)));

        Comparator<Actor> comparator = (actor1,actor2 ) -> Period.between(actor1.getBirthday(),LocalDate.now()).getYears()
                - Period.between(actor2.getBirthday(),LocalDate.now()).getYears();

        System.out.println(comparator.compare(actorList.get(0),actorList.get(1)));
    }
}
