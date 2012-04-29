package net.bodz.bas.repr.xfiles;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class XFileInterceptor
        implements Filter {

    FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig)
            throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String file = request.getParameter("X");
        String key = request.getParameter("K");
        if (file != null && key != null) {
            XFile xfile = new XFile(file, key);
            if (xfile.validate()) {
                // ...
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
