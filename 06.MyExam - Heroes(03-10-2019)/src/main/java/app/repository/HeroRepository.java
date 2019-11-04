package app.repository;

import app.domain.entities.Hero;

import java.util.List;

public interface HeroRepository {
    void save(Hero hero);

    List<Hero> findAll();

    Hero findById(String id);

    void delete(String id);
}
