package itpatagonia.com.sprinboottest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    Actor actor = null;
    @BeforeEach
    void setUp() {
        actor = new Actor(1L,"Cruise","Tom", LocalDate.of(2000,12,12));
    }

    @Test
    void getId() {
        assertEquals(1L,actor.getId());
    }

    @Test
    void getSurname() {
        assertEquals("Cruise", actor.getSurname());
    }

    @Test
    void getName() {
        assertEquals("Tom", actor.getName());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(2000,12,12), actor.getBirthday());
    }

    @Test
    void setId() {
        Long id = 2L;
        actor.setId(id);
        assertEquals(id,actor.getId());
    }

    @Test
    void setSurname() {
        String surname = "Surname";
        actor.setSurname(surname);
        assertEquals(surname,actor.getSurname());
    }

    @Test
    void setName() {
        String name = "Name";
        actor.setName(name);
        assertEquals(name,actor.getName());
    }

    @Test
    void setBirthday() {
        LocalDate birthday = LocalDate.now();
        actor.setBirthday(birthday);
        assertEquals(birthday,actor.getBirthday());
    }

    @Test
    void testToString() {
        assertEquals("Actor(id=1, surname=Cruise, name=Tom, birthday=2000-12-12)",actor.toString());
    }

    @Test
    void constructorEmpty(){
        actor = new Actor();
        assertNull(actor.getId());
    }
}