package Filter;

import Beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "AuthorityFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8"),
                @WebInitParam(name = "mainPage", value = "/app/include/*;/bootstrap/*;"),
                @WebInitParam(name = "signInPage", value = "/app/include/signIn.jsp"),
                @WebInitParam(name = "servletPage", value = "/SignInServlet"),
                @WebInitParam(name = "goSignUpPage", value = "/GoSignUpServlet"),
                @WebInitParam(name = "signUpPage", value = "/SignUpServlet")

        }
)
public class AuthorityFilter implements Filter {
    private FilterConfig filterConfig;
    public void destroy() {
        filterConfig = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        String encoding = filterConfig.getInitParameter("encoding");
        String mainPage = filterConfig.getInitParameter("mainPage");
        String signInPage = filterConfig.getInitParameter("signInPage");
        String servletPage = filterConfig.getInitParameter("servletPage");
        String goSignUpPage = filterConfig.getInitParameter("goSignUpPage");
        String signUpPage = filterConfig.getInitParameter("signUpPage");

        req.setCharacterEncoding(encoding);

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = ((HttpServletRequest) req).getSession(true);
        User user = (User) session.getAttribute("user_info");

        String requestPath = request.getServletPath();

        if(user == null && !requestPath.endsWith(mainPage) && !(requestPath.endsWith(servletPage) || requestPath.endsWith(goSignUpPage) || requestPath.endsWith(signUpPage))){
            request.getRequestDispatcher(signInPage).forward(req,resp);
        }else {
            chain.doFilter(req, resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {
        filterConfig = config;
    }

}
