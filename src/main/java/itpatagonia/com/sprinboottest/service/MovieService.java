package itpatagonia.com.sprinboottest.service;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Movie;
import itpatagonia.com.sprinboottest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) throws NoEntityException {
        return movieRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Movie con " + id));
    }

    public Movie updateMovie(Movie movie) throws NoEntityException {
        Movie movieOld = movieRepository.findById(movie.getId()).orElseThrow(
                () -> new NoEntityException("No existe Movie con " + movie.getId()));
        movieOld.setName(movie.getName());
        return movieRepository.save(movieOld);
    }

    public void deleteMovie(Long id) throws NoEntityException {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new NoEntityException("No existe Movie con " + id));
        movieRepository.delete(movie);
    }
}
