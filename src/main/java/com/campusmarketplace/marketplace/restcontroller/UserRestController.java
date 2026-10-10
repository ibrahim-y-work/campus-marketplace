package com.campusmarketplace.marketplace.restcontroller;

import com.campusmarketplace.marketplace.dto.UserRegistrationRequest;
import com.campusmarketplace.marketplace.entity.User;
import com.campusmarketplace.marketplace.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody UserRegistrationRequest request){
        User theUser=userService.addUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(theUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRegistrationRequest request){
        User theUser=userService.updateUser(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(theUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        User theUser=userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(theUser);
    }
}
