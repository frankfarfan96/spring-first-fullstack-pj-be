package org.farfenix.service;

import org.farfenix.model.User;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    List<User> gerUsers();
    void deleteUser(String email);
    User getUser(String email);
}
