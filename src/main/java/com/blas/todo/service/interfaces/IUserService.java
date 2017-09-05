package com.blas.todo.service.interfaces;

import com.blas.todo.entity.User;
import com.blas.todo.entity.VerificationToken;

public interface IUserService {
    //public User findUserByEmail(String email);
    User saveNewUser(String email, String password);
    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);
    com.blas.todo.entity.User saveUser(com.blas.todo.entity.User user);
    com.blas.todo.entity.User getUserByUserName(String username);
    String resetPassword(com.blas.todo.entity.User user);
    void sendMailNewPassword(User user, String newPassord);
}
