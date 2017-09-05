package com.blas.todo.repository;

import com.blas.todo.entity.User;
import com.blas.todo.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);
}
