package app.web.filters;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/views/index.jsf","/views/register.jsf", "/views/login.jsf"})
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String isLogged = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("username");

        if (isLogged != null){
            ((HttpServletResponse) servletResponse).sendRedirect("/views/home.jsf");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}