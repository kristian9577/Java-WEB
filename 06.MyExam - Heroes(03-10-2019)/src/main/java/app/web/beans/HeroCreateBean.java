package app.web.beans;

import app.domain.entities.Clazz;
import app.domain.models.binding.HeroCreateBindingModel;
import app.domain.models.service.HeroServiceModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class HeroCreateBean extends BaseBean {
    private HeroCreateBindingModel heroCreateBindingModel;
    private HeroService heroService;
    private ModelMapper modelMapper;

    public HeroCreateBean() {
    }

    @Inject
    public HeroCreateBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.heroCreateBindingModel = new HeroCreateBindingModel();
    }

    public void create() {
        HeroServiceModel model = this.modelMapper
                .map(this.heroCreateBindingModel, HeroServiceModel.class);
        Clazz clazz = null;
        try {
            clazz = Clazz.valueOf(this.heroCreateBindingModel.getClazz().toString());
        } catch (Exception ex) {
            this.redirect("/create-hero");
        }
        model.setClazz(clazz);
        this.heroService.save(model);
        this.redirect("/home");
    }

    public HeroCreateBindingModel getHeroCreateBindingModel() {
        return heroCreateBindingModel;
    }

    public void setHeroCreateBindingModel(HeroCreateBindingModel heroCreateBindingModel) {
        this.heroCreateBindingModel = heroCreateBindingModel;
    }
}
