package itpatagonia.com.sprinboottest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Actor;
import itpatagonia.com.sprinboottest.repository.ActorRepository;
import itpatagonia.com.sprinboottest.service.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class ActorControllerTest {

    @MockBean
    ActorService actorService;

    @MockBean
    ActorRepository actorRepository;

    @Autowired
    private MockMvc mockMvc;

    List<Actor> listActors;
    Actor actor;
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        actor = new Actor(1L , "Doe" , "Joe" , LocalDate.of(1980,10,10));
        listActors = new ArrayList<>();
        listActors.add( new Actor(1L, "Keanu", "Reeves", LocalDate.of(1920,12,12)));
        listActors.add(new Actor(2L, "Bellucci", "Monica", LocalDate.of(1921,11,11)));
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

    }

    @Test
    void getActores() throws Exception {
        List<Actor> actorList = new ArrayList<>();
        actorList.add(new Actor(1L,"Reeves","Keanu", LocalDate.of(1970,12,12)));
        actorList.add(new Actor(2L,"Doe","Joe", LocalDate.of(1971,11,11)));
        when(actorService.findAll()).thenReturn(actorList);
        mockMvc.perform(MockMvcRequestBuilders.get("/actors/v1/list").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$[0].surname").value("Reeves"))
                .andExpect(jsonPath("$[1].surname").value("Doe"))

        ;

    }

    @Test
    void getActorById() throws Exception {
        when(actorService.findById(1L)).thenReturn(actor);

        mockMvc.perform(MockMvcRequestBuilders.get("/actors/v1/1").contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$.surname").value("Doe"))
                ;
    }

    @Test
    void createActor() throws Exception {
        Actor newActor = new Actor(null, "Keanu", "Reeves", LocalDate.of(1920,12,12));
        Actor createdActor = new Actor(1L, "Keanu", "Reeves", LocalDate.of(1920,12,12));

        when(actorService.createActor(any())).thenReturn(createdActor);

        mockMvc.perform(MockMvcRequestBuilders.post("/actors/v1/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newActor)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.surname").value("Keanu"));
    }

    @Test
    void updateActor() throws Exception {
        Actor newActor = new Actor(null, "Keanu", "Reeves", LocalDate.of(1920,12,12));
        Actor updatedActor = new Actor(1L, "Keanu2", "Reeves", LocalDate.of(1920,12,12));

        when(actorService.updateActor(any())).thenReturn(updatedActor);

        mockMvc.perform(MockMvcRequestBuilders.put("/actors/v1/update").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedActor)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.surname").value("Keanu2"));
    }

    @Test
    void deleteActor() throws Exception {
        doNothing().when(actorService).deleteActor(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/actors/v1/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string("Actor Eliminado"))
                .andDo(print());
    }

    @Test
    void getEstimateAge() {
    }
}