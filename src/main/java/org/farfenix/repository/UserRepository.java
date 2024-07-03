package org.farfenix.repository;

import org.farfenix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existByEmail(String email);

    void deleteByEmaik(String email);

    Optional<User> findByEmail(String email);
}
