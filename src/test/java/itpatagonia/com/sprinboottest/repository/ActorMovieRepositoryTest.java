package itpatagonia.com.sprinboottest.repository;

import itpatagonia.com.sprinboottest.model.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ActorMovieRepositoryTest {
    @Autowired
    ActorRepository actorRepository;

    @Test
    void findById(){
        assertTrue(actorRepository.findById(12L).isPresent());
        assertEquals(  "Bellucci"  ,actorRepository.findById(12L).get().getSurname());
   }

    @Test
    void findAll(){
        List<Actor> list = actorRepository.findAll();

        assertFalse(list.isEmpty());
        assertTrue(list.size()>0);

        //assertEquals(  "Bellucci"  ,actorRepository.findById(12L).get().getSurname());
    }

    @Test
    void create(){
        Actor actor = new Actor(null, "Maza", "Hugo", LocalDate.of(1920,12,12));
        System.out.println(actor);
        Actor actorSave = actorRepository.save(actor);
        System.out.println(actorSave);
        assertTrue(actorSave.getId()>0);
    }


}