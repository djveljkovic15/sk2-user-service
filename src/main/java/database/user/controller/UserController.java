package database.user.controller;


import database.role.domain.Role;
import database.role.service.RoleService;
import database.token.domain.TokenRequest;
import database.token.security.CheckSecurity;
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
    private final RoleService roleService;

    public UserController(UserService service, RoleService roleService){
        this.service = service;
        this.roleService = roleService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "Saves  user.")
    public ResponseEntity<User> save(@Valid @RequestBody User user, Role userRole){
        if(userRole.getId()!=null && roleService.findById(userRole.getId())!=null)

            user.setUserRole(roleService.save(userRole));
        return new ResponseEntity<>(service.save(user, userRole), HttpStatus.CREATED);
    }
    @PostMapping("/update/userId")
    @ApiOperation(value = "Updates user.")
    public ResponseEntity<User> update(@PathVariable Long userId, @Valid @RequestBody User user, Role userRole){
        if(findById(userId)==null)
            return null;
        user.setId(userId);
        if(userRole.getId()!=null && roleService.findById(userRole.getId())!=null)
            user.setUserRole(roleService.save(userRole));
        return new ResponseEntity<>(service.update(userId, user, userRole), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{userId}")
    @CheckSecurity(roles = "ADMIN")
    @ApiOperation(value = "Deletes user.")
    public ResponseEntity<?> deleteById(@RequestHeader("Authorization")String authorization, @PathVariable Long userId){
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
//
//    @PostMapping("/login")
//    public ResponseEntity<TokenRequest> loginUser(@RequestBody @Valid TokenRequest tokenRequest){
//        return new ResponseEntity<>(service.login(tokenRequest), HttpStatus.OK);
//    }

}
