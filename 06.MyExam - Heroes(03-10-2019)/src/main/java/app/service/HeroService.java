package app.service;

import app.domain.models.service.HeroServiceModel;


import java.util.List;

public interface HeroService {
    void save(HeroServiceModel hero);

    List<HeroServiceModel> getAll();


    HeroServiceModel getById(String id);

    void delete(String id);
}
