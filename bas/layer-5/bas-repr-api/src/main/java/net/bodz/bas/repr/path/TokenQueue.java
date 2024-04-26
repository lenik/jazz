package net.bodz.bas.repr.path;

import java.net.URI;
import java.net.URL;

import net.bodz.bas.meta.decl.ThreadUnsafe;

import jakarta.servlet.http.HttpServletRequest;

@ThreadUnsafe
public class TokenQueue
        extends BasicTokenQueue
        implements
            ITokenQueue {

    private static final long serialVersionUID = 1L;

    final String method;
    final String scheme;
    final String host;
    final int port;
    final String userInfo;
    final String query;
    final String fragment;

    public TokenQueue(String[] tokens, boolean entered) {
        super(tokens, entered);
        this.method = null;
        this.scheme = null;
        this.host = null;
        this.port = 0;
        this.userInfo = null;
        this.query = null;
        this.fragment = null;
    }

    public TokenQueue(String method, String scheme, String host, int port, String userInfo, String query,
            String fragment, String[] tokens, boolean entered) {
        super(tokens, entered);
        this.method = method;
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.userInfo = userInfo;
        this.query = query;
        this.fragment = fragment;
    }

    public static TokenQueue ofPath(String path) {
        return new Builder().path(path).build();
    }

    public static TokenQueue ofRequest(HttpServletRequest req) {
        return new Builder().request(req).build();
    }

    @Override
    public TokenQueue clone() {
        TokenQueue o = new TokenQueue(method, scheme, host, port, userInfo, query, fragment, tokens, entered);
        o.initState(this);
        return o;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public String getScheme() {
        return scheme;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getUserInfo() {
        return userInfo;
    }

    @Override
    public String getQuery() {
        return query;
    }

    @Override
    public String getFragment() {
        return fragment;
    }

    public static class Builder
            extends BasicTokenQueue.Builder {

        String method;
        String scheme;
        String host;
        int port;
        String userInfo;
        String query;
        String fragment;

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder request(HttpServletRequest req) {
            method = req.getMethod();
            scheme = req.getScheme();
            host = req.getServerName();
            port = req.getServerPort();
            query = req.getQueryString();

            String pathInfo = req.getPathInfo();
            if (pathInfo.startsWith("/"))
                pathInfo = pathInfo.substring(1);
            path(pathInfo);

            return this;
        }

        public Builder context(URI uri) {
            scheme = uri.getScheme();
            host = uri.getHost();
            port = uri.getPort();
            userInfo = uri.getUserInfo();
            query = uri.getQuery();
            fragment = uri.getFragment();
            return this;
        }

        public Builder context(URL url) {
            scheme = url.getProtocol();
            host = url.getHost();
            port = url.getPort();
            userInfo = url.getUserInfo();
            query = url.getQuery();
            fragment = url.getRef();
            return this;
        }

        public Builder scheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder userInfo(String userInfo) {
            this.userInfo = userInfo;
            return this;
        }

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder fragment(String fragment) {
            this.fragment = fragment;
            return this;
        }

        @Override
        public Builder path(String path) {
            super.path(path);
            return this;
        }

        TokenQueue buildSimple() {
            return new TokenQueue(tokens, entered);
        }

        @Override
        public TokenQueue build() {
            TokenQueue o = new TokenQueue(method, scheme, host, port, userInfo, query, fragment, tokens, entered);
            return o;
        }

    }

}
