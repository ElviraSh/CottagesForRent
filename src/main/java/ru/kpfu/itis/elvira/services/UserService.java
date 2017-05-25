package ru.kpfu.itis.elvira.services;

import ru.kpfu.itis.elvira.entity.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
