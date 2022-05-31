package itpatagonia.com.sprinboottest.controller;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Actor;
import itpatagonia.com.sprinboottest.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors/v1")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/create")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
        Actor act = actorService.createActor(actor);
        return ResponseEntity.ok(act);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Actor>> getActores(){
        return ResponseEntity.ok(actorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getActorById(@PathVariable("id") Long id) {

        try {
            Actor actor = actorService.findById(id);
            return ResponseEntity.ok(actor);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Actor No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Actor No encontrado ");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Actor> updateActor(@RequestBody Actor actor){
        Actor act = new Actor();
        try {
            act = actorService.updateActor(actor);
            return ResponseEntity.ok(act);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Actor No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.status(400).body(act);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteActor(@PathVariable("id") Long id){
        try {
            actorService.deleteActor(id);
            return ResponseEntity.ok("Actor Eliminado ");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Actor No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Actor No Eliminado");
        }
    }

    @GetMapping("/calcularedad/{id}")
    public int getEstimateAge(@PathVariable Long id){
        try {
            //return new ResponseEntity<>(actorService.calcularEdad(id), HttpStatusCode.valueOf(200));
            return actorService.calcularEdad(id);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
