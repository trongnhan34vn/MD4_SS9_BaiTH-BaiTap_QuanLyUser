package service;

import model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    void save(User User);
    User findById(int id);
    void remove(int id);
}
