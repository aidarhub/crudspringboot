package com.example.crudspringboot.service;

import com.example.crudspringboot.dao.UserRepository;
import com.example.crudspringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> listUsers() {
        return userRepository.listUsers();
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public void addUser(User user, int[] roles) {
        if (roles != null) {
            for (int roleId : roles) {
                user.addRole(roleService.getRoleById((long) roleId));
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user, int[] roles) {
        user.setId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (roles != null) {
            for (int roleId : roles) {
                user.addRole(roleService.getRoleById((long) roleId));
            }
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);

        if (user != null) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User getUserByLogin(String login) {

        return userRepository.findUserByLogin(login);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findUserByLogin(s);
    }
}
