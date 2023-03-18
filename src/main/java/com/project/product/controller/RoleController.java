package com.project.product.controller;

import com.project.product.model.Role;
import com.project.product.response.MessageResponse;
import com.project.product.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Roles")
@AllArgsConstructor

public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public List<Role> getAllRoles(){
        List<Role> Roles =  roleService.getAllRoles();
        return Roles;
    }
    @GetMapping("/{id}")
    public Role getRoleByID(@PathVariable("id") Integer id){
        Role role = roleService.getRoleById(id);
        return role;
    }
    @PostMapping("/addRole")
    public Role addNewRole(@RequestBody Role role){
        Role newRole = roleService.addRole(role);
        return newRole;
    }
    @PutMapping("/editRole/{id}")
    public ResponseEntity<MessageResponse> updateRoleByID(@PathVariable("id") Integer id, @RequestBody Role role){

        ResponseEntity<MessageResponse> response = roleService.editRole(id, role);
        return response;
    }
    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<MessageResponse> deleteRoleById(@PathVariable("id") Integer id){
        ResponseEntity<MessageResponse> response = roleService.deleteRoleById(id);
        return response;
    }
}
