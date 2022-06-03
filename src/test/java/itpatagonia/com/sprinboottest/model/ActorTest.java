package itpatagonia.com.sprinboottest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    Actor actor;
    @BeforeEach
    void setUp() {
        actor = new Actor(1L, "Doe", "Hugo", LocalDate.of(1920,12,12));
    }

    @Test
    void getId() {
        assertTrue(actor.getId()== 1);
        assertEquals(1,actor.getId());
   }

    @Test
    void getSurname() {
        assertEquals("Doe",actor.getSurname());
    }

    @Test
    void getName() {
        assertEquals("Hugo",actor.getName());
    }

    @Test
    void getBirthday() {
        assertEquals(LocalDate.of(1920,12,12),actor.getBirthday());
    }

    @Test
    void setId() {
        actor.setId(Long.parseLong("5"));
        assertEquals(5,actor.getId());
    }

    @Test
    void setSurname() {
        actor.setSurname("Java");
        assertEquals("Java",actor.getSurname());
    }

    @Test
    void setName() {
        actor.setName("Java");
        assertEquals("Java",actor.getName());
    }

    @Test
    void setBirthday() {
        actor.setBirthday(LocalDate.now());
        assertEquals(LocalDate.now(),actor.getBirthday());
    }

    @Test
    void testToString() {
        assertEquals("Actor(id=1, surname=Doe, name=Hugo, birthday=1920-12-12)",actor.toString());
    }
}