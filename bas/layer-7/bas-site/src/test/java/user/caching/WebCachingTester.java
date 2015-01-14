package user.caching;

import net.bodz.bas.http.config.ServletContextConfig;
import net.bodz.uni.echo.test.AbstractWebAppRunner;

public class WebCachingTester
        extends AbstractWebAppRunner {

    @Override
    protected ServletContextConfig createConfig() {
        return new WebCachingConfig();
    }

    public static void main(String[] args)
            throws Exception {
        new WebCachingTester().makeClient().go("s/123");
    }

}
