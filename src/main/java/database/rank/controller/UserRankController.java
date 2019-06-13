package database.rank.controller;


import database.rank.domain.UserRank;
import database.rank.service.UserRankService;
import database.token.security.CheckSecurity;
import io.swagger.annotations.ApiOperation;
import org.hibernate.annotations.Check;
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

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @PostMapping("/save")
    @ApiOperation("Save rank.")
    public ResponseEntity<UserRank> save(@RequestHeader("Authorization") String authorization,
                                         @Valid @RequestBody UserRank userRank){
        return new ResponseEntity<>(service.save(userRank), HttpStatus.CREATED);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @PostMapping("/update/{rankId}")
    @ApiOperation("Update rank.")
    public ResponseEntity<UserRank> update(@RequestHeader("Authorization") String authorization,
                                           @PathVariable Long rankId, @Valid @RequestBody UserRank userRank){
        return new ResponseEntity<>(service.update(rankId, userRank), HttpStatus.CREATED);
    }
    @CheckSecurity(roles = "ADMIN")
    @DeleteMapping("/delete/{rankId}")
    @ApiOperation(value = "Deletes rank.")
    public ResponseEntity<?> deleteById(@RequestHeader("Authorization") String authorization, @PathVariable Long rankId){
        service.deleteById(rankId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/{rankId}")
    @ApiOperation(value = "Finds rank by id.")
    public ResponseEntity<UserRank> findById(@RequestHeader("Authorization") String authorization,@PathVariable Long rankId){
        return new ResponseEntity<>(service.findById(rankId), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/name/{rankName}")
    @ApiOperation("find user role by name")
    public ResponseEntity<UserRank> findByName(@RequestHeader("Authorization") String authorization,
                                               @PathVariable String rankName){
        return new ResponseEntity<>(service.findByName(rankName), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/all")
    @ApiOperation(value = "Finds all ranks.")
    public List<UserRank> findAll(@RequestHeader("Authorization") String authorization){
        return service.findAll();
    }

}
