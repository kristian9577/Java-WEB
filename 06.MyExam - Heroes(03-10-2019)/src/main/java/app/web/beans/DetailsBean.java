package app.web.beans;

import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailsBean extends BaseBean {

    private HeroService heroService;
    private ModelMapper modelMapper;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    public HeroViewModel getHeroById(String id) {
        return this.modelMapper.map(this.heroService.getById(id), HeroViewModel.class);
    }
}
