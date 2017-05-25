package ru.kpfu.itis.elvira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.elvira.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {


    User findByUsername(String username);
}
