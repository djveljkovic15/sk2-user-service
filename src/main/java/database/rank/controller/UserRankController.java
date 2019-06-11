package database.rank.controller;


import database.rank.domain.UserRank;
import database.rank.service.UserRankService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("service/rank")
public class UserRankController {
    private final UserRankService service;

    public UserRankController(UserRankService service) {
        this.service = service;
    }

    @PostMapping("/save")
    @ApiOperation("Save rank.")
    public ResponseEntity<UserRank> saveOrUpdate(@Valid @RequestBody UserRank userRank){
        return new ResponseEntity<>(service.saveOrUpdate(userRank), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{rankId}")
    @ApiOperation(value = "Deletes rank.")
    public ResponseEntity<?> deleteById(@PathVariable Long rankId){
        service.deleteById(rankId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{rankId}")
    @ApiOperation(value = "Finds rank by id.")
    public ResponseEntity<UserRank> findById(@PathVariable Long rankId){
        return new ResponseEntity<>(service.findById(rankId), HttpStatus.OK);
    }

    @GetMapping("/name/{rankName}")
    @ApiOperation("find user role by name")
    public ResponseEntity<UserRank> findByName(@PathVariable String rankName){
        return new ResponseEntity<>(service.findByName(rankName), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Finds all ranks.")
    public List<UserRank> findAll(){
        return service.findAll();
    }

}
