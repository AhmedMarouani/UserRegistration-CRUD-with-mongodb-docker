package com.project.product.service;

import com.project.product.model.User;
import com.project.product.response.MessageResponse;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();

    User getUser(ObjectId id);

    User addUser(User user);

    ResponseEntity<MessageResponse> editUser(ObjectId id, User user);

    ResponseEntity<MessageResponse> deleteUser(ObjectId id);

    User findByEmail(String email);

    Boolean existsByEmail(String email);
}
