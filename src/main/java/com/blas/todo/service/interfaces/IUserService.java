package com.blas.todo.service.interfaces;

import com.blas.todo.entity.User;

public interface IUserService {
    public User findUserByEmail(String email);
    public void saveUser(User user);
}
