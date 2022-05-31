package itpatagonia.com.sprinboottest.controller;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.ActorMovie;
import itpatagonia.com.sprinboottest.service.ActorMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogo/v1")
public class ActorMovieController {

    @Autowired
    private ActorMovieService actorMovieService;

    @PostMapping("/create")
    public ResponseEntity<ActorMovie> createActorMovie(@RequestBody ActorMovie actorMovie){
        ActorMovie act = actorMovieService.createActorMovie(actorMovie);
        return ResponseEntity.ok(act);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ActorMovie>> getActorMovies(){
        return ResponseEntity.ok(actorMovieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getActorMovieById(@PathVariable("id") Long id) {

        try {
            ActorMovie actorMovie = actorMovieService.findById(id);
            return ResponseEntity.ok(actorMovie);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("ActorMovie No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Actor Pelicula No encontrado ");
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateActorMovie(@RequestBody ActorMovie actorMovie){
        try {
            ActorMovie act = actorMovieService.updateActorMovie(actorMovie);
            return ResponseEntity.ok(act);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("ActorMovie No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "ActorMovie No Actualizado ");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteActorMovie(@PathVariable("id") Long id){
        try {
            actorMovieService.deleteActorMovie(id);
            return ResponseEntity.ok("Actor Pelicula Eliminado ");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("ActorMovie No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Actor Pelicula No Eliminado");
        }
    }
}
