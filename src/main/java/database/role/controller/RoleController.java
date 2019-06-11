package database.role.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import database.role.domain.Role;
import database.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("service/role")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @PostMapping("/save")
    @ApiOperation("Save role.")
    public ResponseEntity<Role> saveOrUpdate(@Valid @RequestBody Role role){
        return new ResponseEntity<>(service.saveOrUpdate(role), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{roleId}")
    @ApiOperation(value = "Deletes role.")
    public ResponseEntity<?> deleteById(@PathVariable Long roleId){
        service.deleteById(roleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    @ApiOperation(value = "Finds role by id.")
    public ResponseEntity<Role> findById(@PathVariable Long roleId){
        return new ResponseEntity<>(service.findById(roleId), HttpStatus.OK);
    }

    @GetMapping("/name/{roleName}")
    @ApiOperation("find user role by name")
    public ResponseEntity<Role> findByName(@PathVariable String roleName){
        return new ResponseEntity<>(service.findByName(roleName), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ApiOperation(value = "Finds all roles.")
    public List<Role> findAll(){
        return service.findAll();
    }

}
