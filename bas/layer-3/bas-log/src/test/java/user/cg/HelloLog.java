package user.cg;

import net.bodz.bas.log.ILogSink;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class HelloLog {

    static final Logger logger = LoggerFactory.getLogger(HelloLog.class);

    public static void main(String[] args) {
        logger.info("log4j-loggery");
        ILogSink es = logger.getErrorSink();
        es.p("log4j-error-sink");
    }

}
