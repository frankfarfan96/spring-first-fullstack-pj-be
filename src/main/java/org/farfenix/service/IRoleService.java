package org.farfenix.service;

import org.farfenix.model.Role;

import java.util.List;

public interface IRoleService {
    List<Role> getRoles();
    Role createRole(Role theRole);
    void deleteRole(Long id);
    Role findRoleByName(String name);
}
