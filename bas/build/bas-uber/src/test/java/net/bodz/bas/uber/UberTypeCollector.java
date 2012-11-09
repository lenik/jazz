package net.bodz.bas.uber;

import net.bodz.bas.c.type.TypeCollector;
import net.bodz.bas.c.type.TypeExtensions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;

public class UberTypeCollector {

    static Logger logger = LoggerFactory.getLogger(UberTypeCollector.class);

    public static void main(String[] args)
            throws Exception {
        for (Class<?> base : TypeExtensions.forClassWithAnnotation(IndexedType.class)) {
            logger.info("Indexed-Type: ", base);

            @SuppressWarnings({ "rawtypes", "unchecked" })
            TypeCollector<?> collector = new NetBodzTypeCollector(base, null);

            collector.collect();
            // Check it later.
            // for (Class<?> extension : TypeExtensions.forClass(base))
            // logger.info("    Extension: ", extension);
        }
    }

}
