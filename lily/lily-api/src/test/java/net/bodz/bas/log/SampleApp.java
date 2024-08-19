package net.bodz.bas.log;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.fmt.json.JsonFn;
import net.bodz.bas.site.json.JsonLogger;

public class SampleApp {

    static final Logger _logger = LoggerFactory.getLogger(SampleApp.class);

    LoggerArray logger = ContextLoggers.compose(SampleApp.class, _logger);

    public SampleApp() {
    }

    public void hello()
            throws FormatException {
        JsonLogger logs = new JsonLogger();
        logger.addLogger(logs);

        logger.info("make greeting");
        logger.mesg("good bye");
        System.out.println("Hello, world");

        logs.dump(false);
        String json = JsonFn.toJson(logs);
        System.out.println(json);
    }

    public static void main(String[] args)
            throws FormatException {
        new SampleApp().hello();
    }

}
