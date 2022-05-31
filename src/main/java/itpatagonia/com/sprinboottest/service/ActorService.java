package itpatagonia.com.sprinboottest.service;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Actor;
import itpatagonia.com.sprinboottest.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    private ActorRepository actorRepository;

    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    public Actor findById(Long id) throws NoEntityException {
        return actorRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Actor con " + id));
    }

    public Actor updateActor(Actor actor) throws NoEntityException {
        Actor actorOld = actorRepository.findById(actor.getId()).orElseThrow(
                () -> new NoEntityException("No existe Actor con " + actor.getId()));
        actorOld.setSurname(actor.getSurname());
        actorOld.setName(actor.getName());
        actorOld.setBirthday(actor.getBirthday());
        return actorRepository.save(actorOld);
    }

    public void deleteActor(Long id) throws NoEntityException {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Actor con " + id));
        actorRepository.delete(actor);
    }

    public int calcularEdad(Long id) throws NoEntityException {
        Optional<Actor> actor = this.actorRepository.findById(id);
            return Period.between(actor.orElseThrow(() -> new NoEntityException("No existe Actor con " + id)).getBirthday(),
                    LocalDate.now()).getYears();

    }

}
