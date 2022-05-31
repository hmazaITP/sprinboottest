package itpatagonia.com.sprinboottest.repository;

import itpatagonia.com.sprinboottest.model.Actor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ActorRepositoryTest {
    @Autowired
    ActorRepository actorRepository;
    @BeforeEach
    void setUp() {
    }

    @Test
    void findById(){
        System.out.println(actorRepository.findById(12L).orElseThrow());
        assertTrue(actorRepository.findById(12L).isPresent());
    }

    @Test
    void findAll(){
        assertFalse(actorRepository.findAll().isEmpty());
    }

    @Test
    void create(){
        Actor actor = actorRepository.save(new Actor(null,"Doe","Jhon", LocalDate.of(1970,12,12)));
        System.out.println(actor);
        assertTrue( actor.getId()>0);
   }
    @Test
    void update(){
        Actor actor = new Actor(3L,"Doe","Jhon", LocalDate.of(1970,12,12));
        Actor actorActualizado = actorRepository.save(actor);
        System.out.println(actorActualizado);
        assertTrue( actorActualizado.getId()>0);
    }

}