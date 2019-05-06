/*package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.User;
import hr.java.web.vesliga.moneyapp.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/users", produces = "application/json")
@CrossOrigin
public class UserRestController {

    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findOne(@PathVariable Long id){
        User user =  userRepository.findOne(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>((User) null,HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes="application/json")
    public User save(@Validated @RequestBody User user){

        return userRepository.save(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userRepository.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User newUser, @PathVariable Long id){
        User user = userRepository.findOne(id);

        userRepository.update(id,newUser);
    }
}
*/