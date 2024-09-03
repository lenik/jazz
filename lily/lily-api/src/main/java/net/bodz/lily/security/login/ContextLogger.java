package net.bodz.lily.security.login;

import java.util.List;

import net.bodz.bas.log.BufferedLogger;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerArray;
import net.bodz.bas.log.LoggerFactory;

public class ContextLogger {

    ThreadLocal<List<Logger>> tls;

    static Logger getLogger(Logger other) {
        LoggerArray logv = new LoggerArray();
        return null;
    }

}

class App {

    static final Logger _logger = LoggerFactory.getLogger(App.class);
    Logger logger = ContextLogger.getLogger(_logger);

}

class User {
    {
        ContextLogger.addLogger(new BufferedLogger());
        App app = new App();

    }
}
