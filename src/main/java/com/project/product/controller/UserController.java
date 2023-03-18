package com.project.product.controller;

import com.project.product.model.User;
import com.project.product.response.MessageResponse;
import com.project.product.service.UserService;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/Users")
@RestController
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findByEmail")
    public User findByEmail(String email){
        User UserByEmail = userService.findByEmail(email);
        return UserByEmail;
    }

    @GetMapping("/existsByEmail")
    public Boolean existsByEmail(String email){
        Boolean UserExistsByEmail = userService.existsByEmail(email);
        return UserExistsByEmail;
    }


    @GetMapping("/")
    public List<User> getAllUsers(){
        List<User> Users =  userService.getUsers();
        return Users;
    }
    @GetMapping("/{id}")
    public User getUserByID(@PathVariable("id") ObjectId id){
        User user = userService.getUser(id);
        return user;
    }
    @PostMapping("/addUser")
    public User addNewUser(@RequestBody User user){
        User user1 = userService.addUser(user);
        return user1;
    }
    @PutMapping("/editUser/{id}")
    public ResponseEntity<MessageResponse>  updateUser(@PathVariable("id") ObjectId id, @RequestBody User user){
        ResponseEntity<MessageResponse> response = userService.editUser(id, user);
        return response;
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable("id") ObjectId id){
        ResponseEntity<MessageResponse> response = userService.deleteUser(id);
        return response;
    }
}
