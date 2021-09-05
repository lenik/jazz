package net.bodz.bas.log.log4j;

import org.apache.log4j.spi.LoggerRepository;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface ILog4jConfigurer
        extends
            IPriority {

    void initLog4j(LoggerRepository hierarchy);

}
