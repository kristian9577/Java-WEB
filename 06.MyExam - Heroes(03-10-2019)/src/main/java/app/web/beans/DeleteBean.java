package app.web.beans;

import app.domain.models.view.HeroViewModel;
import app.service.HeroService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class DeleteBean extends BaseBean {

    private HeroService heroService;
    private ModelMapper modelMapper;

    public DeleteBean() {
    }

    @Inject
    public DeleteBean(HeroService heroService, ModelMapper modelMapper) {
        this.heroService = heroService;
        this.modelMapper = modelMapper;
    }
    public HeroViewModel getHeroById(String id) {
        return this.modelMapper.map(this.heroService.getById(id), HeroViewModel.class);
    }
    public void delete(){
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest()).getParameter("id");

        this.heroService.delete(id);
        this.redirect("/home");
        System.out.println();
    }
}

