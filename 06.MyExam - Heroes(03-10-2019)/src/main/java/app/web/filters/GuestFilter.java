package app.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/faces/views/details-hero.xhtml","/faces/views/delete-hero.xhtml", "/faces/views/home.xhtml",
        "/faces/views/create-hero.xhtml"})
public class GuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String id = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("userId");

        if (id == null){
            ((HttpServletResponse)servletResponse).sendRedirect("/faces/views/login.xhtml");
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
