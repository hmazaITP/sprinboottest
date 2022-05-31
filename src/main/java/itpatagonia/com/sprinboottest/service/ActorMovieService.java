package itpatagonia.com.sprinboottest.service;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.ActorMovie;
import itpatagonia.com.sprinboottest.repository.ActorMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorMovieService {
    @Autowired
    private ActorMovieRepository actorMovieRepository;

    public ActorMovie createActorMovie(ActorMovie actorMovie) {
        return actorMovieRepository.save(actorMovie);
    }

    public List<ActorMovie> findAll() {
        return actorMovieRepository.findAll();
    }

    public ActorMovie findById(Long id) throws NoEntityException {
        return actorMovieRepository.findById(id).orElseThrow(
                () -> new NoEntityException("No existe Actor Pelicula con " + id));
    }

    public ActorMovie updateActorMovie(ActorMovie actorMovie) throws NoEntityException {
        ActorMovie actorMovieOld = actorMovieRepository.findById(actorMovie.getId()).orElseThrow(
                () -> new NoEntityException("No existe Actor Pelicula con " + actorMovie.getId()));
        actorMovieOld.setMovieid(actorMovie.getMovieid());
        actorMovieOld.setActorid(actorMovie.getActorid());
        return actorMovieRepository.save(actorMovieOld);
    }

    public void deleteActorMovie(Long id) throws NoEntityException {
        ActorMovie actorMovie = actorMovieRepository.findById(id).orElseThrow(
                () -> new NoEntityException("No existe Actor Pelicula con " + id));
        actorMovieRepository.delete(actorMovie);
    }
}
