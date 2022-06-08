package itpatagonia.com.sprinboottest.utils;

import itpatagonia.com.sprinboottest.model.Actor;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;

public class ActorComparator implements Comparator<Actor> {
    @Override
    public int compare(Actor actor1, Actor actor2)
    {
        int actor1Year = Period.between(actor1.getBirthday(), LocalDate.now()).getYears();
        int actor2Year = Period.between(actor2.getBirthday(), LocalDate.now()).getYears();
        return actor1Year - actor2Year;
    }
}
