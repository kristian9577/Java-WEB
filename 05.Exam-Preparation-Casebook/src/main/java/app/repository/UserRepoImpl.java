package app.repository;

import app.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    private final EntityManager entityManager;

    @Inject
    public UserRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public User findById(String id) {
        return this.entityManager.createQuery("select u from User u where u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return this.entityManager.createQuery("select u from User u where u.username=:username and " +
                "u.password =: password", User.class)
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public void update(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<User> findAll() {
        return this.entityManager.createQuery("SELECT u from User u", User.class)
                .getResultList();
    }
}
