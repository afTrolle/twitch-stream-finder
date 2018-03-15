package twitch.explorer.restApi.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * append cookie to all session on all calls
 */
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.getSession(true);
        System.out.println(req.getRequestURL());
        System.out.println(req.getQueryString());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
