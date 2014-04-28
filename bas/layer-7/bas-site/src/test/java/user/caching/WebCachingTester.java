package user.caching;

import net.bodz.uni.echo.config.EchoServerConfig;
import net.bodz.uni.echo.test.AbstractWebAppRunner;

public class WebCachingTester
        extends AbstractWebAppRunner {

    @Override
    protected EchoServerConfig createConfig() {
        return new WebCachingConfig();
    }

    public static void main(String[] args)
            throws Exception {
        new WebCachingTester().makeClient().go("s/123");
    }

}
