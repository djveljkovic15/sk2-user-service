package database.user.controller;


import com.netflix.client.http.HttpResponse;
import database.role.domain.Role;
import database.role.service.RoleService;
import database.token.domain.TokenRequest;
import database.token.domain.TokenResponse;
import database.token.security.CheckSecurity;
import io.swagger.annotations.ApiOperation;
import database.user.domain.User;
import database.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
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
    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @PostMapping("/save")
    @ApiOperation(value = "Saves  user.")
    public ResponseEntity<User> save(@RequestHeader("Authorization") String authorization,
                                     @Valid @RequestBody User user, Role userRole){
        if(userRole.getId()!=null && roleService.findById(userRole.getId())!=null)

            user.setUserRole(roleService.save(userRole));
        return new ResponseEntity<>(service.save(user, userRole), HttpStatus.CREATED);
    }
    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @PostMapping("/update/{userId}")
    @ApiOperation(value = "Updates user.")
    public ResponseEntity<User> update(@RequestHeader("Authorization") String authorization,
                                       @PathVariable Long userId, @Valid @RequestBody User user, Role userRole){
        if(service.findById(userId)==null)
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

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/{userId}")
    @ApiOperation(value = "Finds user by id.")
    public ResponseEntity<User> findById(@RequestHeader("Authorization") String authorization,
                                         @PathVariable Long userId){
        return new ResponseEntity<>(service.findById(userId), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/all")
    @ApiOperation(value = "Finds all users.")
    public List<User> findAll(@RequestHeader("Authorization") String authorization){
        return service.findAll();
    }

    @CheckSecurity(roles = {"ADMIN"})
    @GetMapping("/ban/{userId}")
    @ApiOperation(value = "Ban user")
    public ResponseEntity<User> ban(@RequestHeader("Authorization") String authorization,
                                    @PathVariable Long userId, @Valid @RequestBody User bannedBy){

        return new ResponseEntity<>(service.banUser(userId, bannedBy), HttpStatus.OK);
    }

    @CheckSecurity(roles = {"ADMIN","REGULAR"})
    @GetMapping("/find")
    @ApiOperation(value = "Finds user by by username and password.")
    public ResponseEntity<User> findByUserAndPass(@RequestHeader("Authorization") String authorization,
                                                  String username, String password){
        return new ResponseEntity<>(service.findByUserAndPass(username,password), HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value = "Logs user and generates token.")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody @Valid TokenRequest tokenRequest){
        return new ResponseEntity<>(service.login(tokenRequest), HttpStatus.OK);
    }


}
