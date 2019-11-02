package app.service;

import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void register(UserServiceModel user);

    UserServiceModel getById(String id);

    UserServiceModel getByUsernameAndPassword(String username, String password);

    void addFriend(UserServiceModel userServiceModel);

    void remove(String userId,String friendId);

    List<UserServiceModel> getAll();
}
