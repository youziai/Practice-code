package com.itheima.service;

import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {
    void del(Long roleId);

    List<Role> list();

    void save(Role role);
}
