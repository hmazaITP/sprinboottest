package itpatagonia.com.sprinboottest.controller;

import itpatagonia.com.sprinboottest.Exceptions.NoEntityException;
import itpatagonia.com.sprinboottest.model.Actor;
import itpatagonia.com.sprinboottest.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/actors/v1")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping(value ="/create")
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
        Actor act = actorService.createActor(actor);
        return new ResponseEntity<Actor>(act,HttpStatus.CREATED);
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
            return ResponseEntity.ok("Actor Eliminado");
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            //return new ResponseEntity<>("Actor No encontrado", HttpStatusCode.valueOf(400));
            return ResponseEntity.badRequest().body( HttpStatus.BAD_REQUEST + "Actor No Eliminado");
        }
    }

    @GetMapping("/calcularedad/{id}")
    public int getEstimateAge(@PathVariable Long id) {
        try {
            //return new ResponseEntity<>(actorService.calcularEdad(id), HttpStatusCode.valueOf(200));
            return actorService.calcularEdad(id);
        } catch (NoEntityException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @GetMapping("/calcularedadpromedio")
    public int getAverageAge(){
        List<Actor> actorList = actorService.findAll();
        return (int) actorList.stream().mapToInt(objeto -> Period.between(objeto.getBirthday(),LocalDate.now()).getYears())
                .average().orElseThrow();
    }

    @GetMapping("/mayoresde18")
    public List<Actor> getGreaters(){
        List<Actor> actorList = actorService.findAll();
        return actorList.stream().filter(objeto -> Period.between(objeto.getBirthday(),LocalDate.now()).getYears()>=18 )
                .collect(Collectors.toList());
    }

    @GetMapping("/menoresde18")
    public List<Actor> getMinors(){
        List<Actor> actorList = actorService.findAll();
        return actorList.stream().filter(objeto -> Period.between(objeto.getBirthday(),LocalDate.now()).getYears()<18 )
                .sorted(Comparator.comparing(Actor::getSurname))
                .collect(Collectors.toList());
    }

    Comparator<Actor> comparatorYear = (a1, a2) -> Period.between(a2.getBirthday(), LocalDate.now()).getYears() -
            Period.between(a1.getBirthday(), LocalDate.now()).getYears();

    @GetMapping("/listareversa")
    public List<Actor> getReverseList(){
        List<Actor> actorList = actorService.findAll();

        //return actorList.stream().sorted((a1, a2) -> (int)(Period.between(a2.getBirthday(), LocalDate.now()).getYears() -
        //        Period.between(a1.getBirthday(), LocalDate.now()).getYears())).collect(Collectors.toList());
        return actorList.stream().sorted(Comparator.comparing(Actor::getSurname).reversed()).collect(Collectors.toList());
    }

    @GetMapping("/mayoredad")
    public Actor getGreater(){
        List<Actor> actorList = actorService.findAll();
        return actorList.stream().max((a1, a2) -> Period.between(a1.getBirthday(), LocalDate.now()).getYears() -
                Period.between(a2.getBirthday(), LocalDate.now()).getYears()).orElseThrow();
    }

    @GetMapping("/menoredad")
    public List<Actor> getMinor(){
        List<Actor> actorList = actorService.findAll();
       // return actorList.stream().min((a1, a2) -> Period.between(a1.getBirthday(), LocalDate.now()).getYears() -
        //        Period.between(a2.getBirthday(), LocalDate.now()).getYears()).stream().limit(3).collect(Collectors.toList());
        return actorList.stream().filter(
                        y -> Period.between(y.getBirthday(), LocalDate.now()).getYears()<18).
                sorted(comparatorYear).limit(3).collect(Collectors.toList());
    }

    @GetMapping("/sumaredades")
    public int getYearsTotal(){
        List<Actor> actorList = actorService.findAll();
        return actorList.stream().mapToInt( y -> Period.between(y.getBirthday(), LocalDate.now()).getYears()).reduce(
                        (accumulate,year) -> accumulate + year).orElse(0);
    }
}
