package ru.kpfu.itis.elvira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.elvira.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role getByRole(String role);
}
