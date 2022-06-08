package itpatagonia.com.sprinboottest.utils;

import itpatagonia.com.sprinboottest.model.Actor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Funciones {
    public static void main(String[] args) {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor(1L, "Reeves", "Keanu", LocalDate.of(1970,12,12)));
        actorList.add(new Actor(2L, "Reeves1", "Keanu1", LocalDate.of(1990,11,11)));
        actorList.add(new Actor(3L, "Reeves2", "Keanu1", LocalDate.of(2015,10,10)));

        Function<Actor,Actor> funcion = (miactor) -> { miactor.setSurname("Al Pacino");
                                                    return miactor;};
        System.out.println(funcion.apply(actorList.get(0)));

        BiFunction<Actor,Actor,String> biFuncion = (actor1,actor2) -> actor1.getSurname().concat(actor2.getSurname());
        System.out.println(biFuncion.apply(actorList.get(1),actorList.get(2)));
    }
}
