package com.project.product.service;

import com.project.product.model.Role;
import com.project.product.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role addRole(Role role);

    ResponseEntity<MessageResponse> editRole(Integer id, Role role);

    ResponseEntity<MessageResponse> deleteRoleById(Integer id);

}
