package services;

import models.entity.User;
import models.service.UserServiceModel;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UsersServiceImpl implements UsersService {

    private final EntityManager entityManager;
    private final HashingService hashingService;
    private final ModelMapper modelMapper;
    private final UsersValidationService usersValidationService;

    @Inject
    public UsersServiceImpl(EntityManager entityManager, HashingService hashingService, ModelMapper modelMapper, UsersValidationService usersValidationService) {
        this.entityManager = entityManager;
        this.hashingService = hashingService;
        this.modelMapper = modelMapper;
        this.usersValidationService = usersValidationService;
    }

    @Override
    public void register(String username, String email, String password, String confirmPassword) throws Exception {
        if(!usersValidationService.canCreateUser(username,email,password,confirmPassword)){
            throw new Exception("User cannot be created!");
        }
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashingService.hash(password));

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public UserServiceModel login(String username, String password) {
        User user = entityManager.createQuery("select u from User u  where u.username= :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(hashingService.hash(password))) {
            return null;
        }
        return modelMapper.map(user,UserServiceModel.class);
    }
}
