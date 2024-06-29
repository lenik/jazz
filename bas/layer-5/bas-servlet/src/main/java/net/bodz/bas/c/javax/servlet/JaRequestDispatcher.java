package net.bodz.bas.c.javax.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import net.bodz.bas.c.jakarta.servlet.JxServletRequest;
import net.bodz.bas.c.jakarta.servlet.JxServletResponse;
import net.bodz.bas.c.util.MapGlobal;

public class JaRequestDispatcher
        implements
            RequestDispatcher {

    jakarta.servlet.RequestDispatcher ja;

    public JaRequestDispatcher(jakarta.servlet.RequestDispatcher ja) {
        if (ja == null)
            throw new NullPointerException("ja");
        this.ja = ja;
    }

    @Override
    public void forward(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        try {
            ja.forward(//
                    JxServletRequest.jx2a.cachedMap(request), //
                    JxServletResponse.jx2a.cachedMap(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void include(ServletRequest request, ServletResponse response)
            throws ServletException, IOException {
        try {
            ja.include(//
                    JxServletRequest.jx2a.cachedMap(request), //
                    JxServletResponse.jx2a.cachedMap(response));
        } catch (jakarta.servlet.ServletException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    public static final MapGlobal<jakarta.servlet.RequestDispatcher, RequestDispatcher> ja2x //
            = MapGlobal.fn(RequestDispatcher.class, (s) -> new JaRequestDispatcher(s));

    // static class Mapper
//            extends MapGlobal<jakarta.servlet.RequestDispatcher, RequestDispatcher> {
//
//        @Override
//        protected RequestDispatcher create(jakarta.servlet.RequestDispatcher source) {
//            return new JaRequestDispatcher(source);
//        }
//
//    }
//
//    public static final Factory FACTORY = new Factory();
//
//    static class Factory
//            implements
//                IAdapterFactory<jakarta.servlet.RequestDispatcher, RequestDispatcher> {
//
//        @Override
//        public Class<jakarta.servlet.RequestDispatcher> getSourceType() {
//            return jakarta.servlet.RequestDispatcher.class;
//        }
//
//        @Override
//        public RequestDispatcher create(jakarta.servlet.RequestDispatcher source) {
//            return new JaRequestDispatcher(source);
//        }
//
//    }

}
