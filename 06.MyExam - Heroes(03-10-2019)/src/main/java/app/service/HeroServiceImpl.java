package app.service;

import app.domain.entities.Hero;
import app.domain.models.service.HeroServiceModel;
import app.repository.HeroRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;
    private final ModelMapper modelMapper;

    @Inject
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper modelMapper) {
        this.heroRepository = heroRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(HeroServiceModel hero) {
        this.heroRepository.save((this.modelMapper.map(hero, Hero.class)));
    }

    @Override
    public List<HeroServiceModel> getAll() {
        return this.heroRepository.findAll()
                .stream()
                .map(h -> this.modelMapper.map(h, HeroServiceModel.class))
                .collect(Collectors.toList());
    }


    @Override
    public HeroServiceModel getById(String id) {

        return this.modelMapper.map(this.heroRepository.findById(id), HeroServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.heroRepository.delete(id);
    }
}
