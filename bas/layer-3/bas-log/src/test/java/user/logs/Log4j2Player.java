package user.logs;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.log.log4j.DefaultLog4jConfigurer;
import net.bodz.bas.log.log4j.PragmaticLog4jConfigurationFactory;
import net.bodz.bas.log.log4j.PragmaticLog4jLoader;

/**
 * @see LoggerFactory#init()
 *
 * @see DefaultLog4jConfigurer
 *
 * @see PragmaticLog4jConfigurationFactory
 * @see PragmaticLog4jLoader
 */
public class Log4j2Player {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Log4j2Player.class);

        logger.error("bas-log error");
        logger.warn("bas-log warn");
        logger.info("bas-log info");
    }

}
