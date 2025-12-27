package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User save(User user) {
        return userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow();
    }

    public User update(Long id, User user) {
        user.setUserId(id);
        return userRepo.save(user);
    }

    public List<User> getByRole(String role) {
        return userRepo.findByRole(role);
    }

    public Optional<User> getByEmail(String email) {
        return userRepo.findByEmail(email);
    }


    public Page<User> paginate(int page, int size) {
        return userRepo.findAll(
                PageRequest.of(page, size, Sort.by("userId"))
        );
    }
}
