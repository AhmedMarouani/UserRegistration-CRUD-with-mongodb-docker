package com.project.product.service;


import com.project.product.model.Product;
import com.project.product.model.Role;
import com.project.product.repository.RoleRepository;
import com.project.product.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public ResponseEntity<MessageResponse> editRole(Integer id, Role role) {
        Optional<Role> editedRole = roleRepository.findById(id);

        if(editedRole.isPresent()){
            Role existingRole = editedRole.orElseThrow(()->new RuntimeException("role not fount"));

            existingRole.setName(role.getName());
            roleRepository.save(existingRole);

            MessageResponse messageResponse = new MessageResponse("Product edited succesfully");
            return ResponseEntity.ok(messageResponse);
        }else {
            throw new ResolutionException("product not found");
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
        MessageResponse messageResponse = new MessageResponse("Role Deleted!");
        return ResponseEntity.ok(messageResponse);
    }
}
