package app.web.beans;

import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HeroListBean extends BaseBean {

    private List<HeroViewModel> hero;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroListBean() {
    }

    @Inject
    public HeroListBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.setHero(this.heroService.getAll().stream()
        .map(h->this.modelMapper.map(h,HeroViewModel.class))
        .collect(Collectors.toList()));
    }

    public List<HeroViewModel> getHero() {
        return hero;
    }

    public void setHero(List<HeroViewModel> hero) {
        this.hero = hero;
    }
}
