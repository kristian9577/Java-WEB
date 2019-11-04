package app.repository;

import app.domain.entities.Hero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class HeroRepositoryImpl implements HeroRepository {

    private final EntityManager entityManager;

    @Inject
    public HeroRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Hero hero) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(hero);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Hero> findAll() {
        this.entityManager.getTransaction().begin();
        List<Hero> heroes = this.entityManager.createQuery(
                "select h from Hero h", Hero.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return heroes;
    }

    @Override
    public Hero findById(String id) {
        this.entityManager.getTransaction().begin();
        Hero hero = this.entityManager.createQuery(
                "SELECT j FROM Hero j where j.id = :id", Hero.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return hero;
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();

        this.entityManager.createQuery("DELETE from Hero j where j.id =:id")
                .setParameter("id", id).executeUpdate();

        this.entityManager.getTransaction().commit();
    }
}
