package ru.kpfu.itis.elvira.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.elvira.entity.Role;
import ru.kpfu.itis.elvira.entity.User;
import ru.kpfu.itis.elvira.repository.RoleRepository;
import ru.kpfu.itis.elvira.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        Set<Role> roles = new HashSet<>();
        if(user.getUsername().equals("admin")){
            roles.add(roleRepository.getOne(1L));
        }else roles.add(roleRepository.getOne(2L));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
