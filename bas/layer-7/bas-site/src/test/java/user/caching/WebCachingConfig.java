package user.caching;

import net.bodz.uni.echo._default.DefaultServerConfig;

public class WebCachingConfig
        extends DefaultServerConfig {

    public WebCachingConfig() {
        addServlet(SnailServlet.class, "/s/*");
    }

}
