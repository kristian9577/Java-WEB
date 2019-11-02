package app.service;

import app.domain.entities.User;
import app.domain.models.service.UserServiceModel;
import app.repository.UserRepo;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserServiceModel user) {
        this.userRepo.save(this.modelMapper.map(user, User.class));
    }

    @Override
    public UserServiceModel getById(String id) {
        return this.modelMapper.map(this.userRepo.findById(id), UserServiceModel.class);
    }

    @Override
    public UserServiceModel getByUsernameAndPassword(String username, String password) {
        return this.modelMapper.map(this.userRepo.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }

    @Override
    public void addFriend(UserServiceModel userServiceModel) {
        this.userRepo.update(this.modelMapper.map(userServiceModel,User.class));
    }

    @Override
    public void remove(String userId, String friendId) {
        User user1=this.userRepo.findById(userId);
        User user2=this.userRepo.findById(friendId);

        user1.getFriends().remove(user2);
        user2.getFriends().remove(user1);
        this.userRepo.update(user1);
        this.userRepo.update(user2);
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepo.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }
}
