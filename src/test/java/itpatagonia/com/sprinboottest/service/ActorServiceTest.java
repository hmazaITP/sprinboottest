package itpatagonia.com.sprinboottest.service;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Actor;
import itpatagonia.com.sprinboottest.repository.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class ActorServiceTest {

    @Mock
    ActorRepository actorRepository;

    @InjectMocks
    ActorService actorService;

    Actor actor;

    @BeforeEach
    void setUp() {
         actor = new Actor(1L,"Reeves","Keanu", LocalDate.of(1970,12,12));
     }

    @Test
    void createActor() {
        Actor newActor = new Actor(null, "Java", "Juan" , LocalDate.now());
        Actor createdActor = new Actor(5L, "Java", "Juan" , LocalDate.now());
        when(actorRepository.save(newActor)).thenReturn(createdActor);
        assertNotNull(actorService.createActor(newActor));

    }

    @Test
    void findAll() {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor(1L,"Reeves","Keanu", LocalDate.of(1970,12,12)));
        actorList.add(new Actor(2L,"Doe","Joe", LocalDate.of(1971,11,11)));
        when(actorRepository.findAll()).thenReturn(actorList);
        //System.out.println(actorService.findAll());
        assertFalse(actorService.findAll().isEmpty());
    }

    @Test
    void findById() throws NoEntityException {
        when(actorRepository.findById(anyLong())).thenReturn(Optional.of(actor));

        assertNotNull(actorService.findById(1L));
    }

    @Test
    void updateActor() throws NoEntityException {
        when(actorRepository.save(actor)).thenReturn(actor);
        when(actorRepository.findById(1L)).thenReturn(Optional.ofNullable(actor));
        Actor updateActor = actorService.updateActor(actor);
        assertNotNull(updateActor);
        assertNotNull(updateActor.getId());

    }

    @Test
    void deleteActor() {
        when(actorRepository.findById(1L)).thenReturn(Optional.empty());
        doNothing().when(actorRepository).delete(actor);
        actorRepository.delete(actor);
        assertFalse(actorRepository.findById(actor.getId()).isPresent());

    }

    @Test
    void calcularEdad() {
    }
}