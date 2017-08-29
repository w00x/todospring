package com.blas.todo.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    @Column(name="role_id", unique = true, nullable = false)
    private Integer roleId;

    @Column(name="role", nullable = false, length = 45)
    private String role;

    public Role(String role) {
        this.role = role;
    }

    public Role() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
