package app.repository;

import app.domain.entities.User;

import java.util.List;

public interface UserRepo {

    void save(User user);

    User findById(String id);

    User findByUsernameAndPassword(String username, String password);

    void update(User user);

    List<User> findAll();
}
