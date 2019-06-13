package database.role.controller;


import database.token.security.CheckSecurity;
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

    @CheckSecurity(roles = "ADMIN")
    @PostMapping("/save")
    @ApiOperation("Save role.")
    public ResponseEntity<Role> save(@RequestHeader("Authorization") String authorization,
                                     @Valid @RequestBody Role role){
        return new ResponseEntity<>(service.save(role), HttpStatus.CREATED);
    }
    @CheckSecurity(roles = "ADMIN")
    @PostMapping("/update/{roleId}")
    @ApiOperation("Update role.")
    public ResponseEntity<Role> update(@RequestHeader("Authorization") String authorization,
                                       @PathVariable Long roleId, @Valid @RequestBody Role role){
        return new ResponseEntity<>(service.update(roleId, role), HttpStatus.CREATED);
    }
    @CheckSecurity(roles = {"ADMIN"})
    @DeleteMapping("/delete/{roleId}")
    @ApiOperation(value = "Deletes role.")
    public ResponseEntity<?> deleteById(@RequestHeader("Authorization") String authorization,@PathVariable Long roleId){
        service.deleteById(roleId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/{roleId}")
    @ApiOperation(value = "Finds role by id.")
    public ResponseEntity<Role> findById(@RequestHeader("Authorization") String authorization,
                                         @PathVariable Long roleId){
        return new ResponseEntity<>(service.findById(roleId), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/name/{roleName}")
    @ApiOperation("find user role by name")
    public ResponseEntity<Role> findByName(@RequestHeader("Authorization") String authorization,
                                           @PathVariable String roleName){
        return new ResponseEntity<>(service.findByName(roleName), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/all")
    @ApiOperation(value = "Finds all roles.")
    public List<Role> findAll(@RequestHeader("Authorization") String authorization){
        return service.findAll();
    }

}
