package com.project.product.service;

import com.project.product.model.User;
import com.project.product.repository.UserRepository;
import com.project.product.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(ObjectId id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<MessageResponse> editUser(ObjectId id, User user) {
        User editedUser = userRepository.findById(id).get();

            editedUser.setUsername(user.getUsername());
            editedUser.setEmail(user.getEmail());
            editedUser.setPassword(user.getPassword());
            editedUser.setRoles(user.getRoles());

             userRepository.save(editedUser);
            MessageResponse messageResponse = new MessageResponse("User edited successfully");

        return ResponseEntity.ok(messageResponse);
    }

    @Override
    public ResponseEntity<MessageResponse> deleteUser(ObjectId id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            MessageResponse messageResponse = new MessageResponse("User deleted successfully");
            return ResponseEntity.ok(messageResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public User  findByEmail(String email) {
            return userRepository.findByEmail(email).get();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
