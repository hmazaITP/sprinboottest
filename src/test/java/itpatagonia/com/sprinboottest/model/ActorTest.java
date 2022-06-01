package itpatagonia.com.sprinboottest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void getId() {
        Actor actor = new Actor(1L, "Doe", "Hugo", LocalDate.of(1920,12,12));
        assertTrue(actor.getId()== 1);
        assertEquals(1,actor.getId());
   }

    @Test
    void getSurname() {
        Actor actor = new Actor(1L, "Doe", "Hugo", LocalDate.of(1920,12,12));
        assertEquals("Doe",actor.getSurname());
    }

    @Test
    void getName() {
    }

    @Test
    void getBirthday() {
    }

    @Test
    void setId() {
        Actor actor = new Actor();
        actor.setId(Long.parseLong("5"));
        assertEquals(5,actor.getId());
    }

    @Test
    void setSurname() {
    }

    @Test
    void setName() {
    }

    @Test
    void setBirthday() {
    }

    @Test
    void testToString() {
        Actor actor = new Actor(1L, "Doe", "Hugo", LocalDate.of(1920,12,12));
        assertEquals("Actor(id=1, surname=Doe, name=Hugo, birthday=1920-12-12)",actor.toString());
    }
}