package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<?> getByRole(@PathVariable String role) {
        List<User> users = userService.getByRole(role);
        if (users.isEmpty()) {
            return new ResponseEntity<>(
                    "No users found with role: " + role,
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(users);
    }

   
    @GetMapping("/email/{email:.+}")
    public ResponseEntity<?> getByEmail(@PathVariable String email) {
        Optional<User> userOpt= userService.getByEmail(email);
        if(userOpt.isPresent())
              return ResponseEntity.ok(userOpt.get());
        else{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with email: "+email);
        }
               
                
    } 

    @GetMapping("/page/{page}/{size}")
    public Page<User> paginate(
            @PathVariable int page,
            @PathVariable int size) {

        return userService.paginate(page, size);
    }
}
