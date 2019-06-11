package database.user.controller;


import io.swagger.annotations.ApiOperation;
import database.user.domain.User;
import database.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("service/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Saves or updates user.")
    public ResponseEntity<User> saveOrUpdate(@Valid @RequestBody User user){
            return new ResponseEntity<>(service.saveOrUpdate(user), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "Deletes user.")
    public ResponseEntity<?> deleteById(@PathVariable Long userId){
        service.deleteById(userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Finds user by id.")
    public ResponseEntity<User> findById(@PathVariable Long userId){
        return new ResponseEntity<>(service.findById(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Finds all users.")
    public List<User> findAll(){
        return service.findAll();
    }

}
