package itpatagonia.com.sprinboottest.controller;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Movie;
import itpatagonia.com.sprinboottest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie act = movieService.createMovie(movie);
        return ResponseEntity.ok(act);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable("id") Long id) {

        try {
            Movie movie = movieService.findById(id);
            return ResponseEntity.ok(movie);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Movie No encontrada", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Movie No encontrada ");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie){
        try {
            Movie mov = movieService.updateMovie(movie);
            return ResponseEntity.ok(mov);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Movie No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Movie No Actualizada ");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") Long id){
        try {
            movieService.deleteMovie(id);
            return ResponseEntity.ok("Movie Eliminada ");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Movie No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Movie No Eliminada");
        }
    }

}
