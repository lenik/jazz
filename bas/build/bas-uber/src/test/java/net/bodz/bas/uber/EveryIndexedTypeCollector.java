package net.bodz.bas.uber;

import net.bodz.bas.c.loader.scan.TypeCollector;
import net.bodz.bas.c.type.TypeExtensions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;

public class EveryIndexedTypeCollector {

    static Logger logger = LoggerFactory.getLogger(EveryIndexedTypeCollector.class);

    public static void main(String[] args)
            throws Exception {
        for (Class<?> base : TypeExtensions.forClassWithAnnotation(IndexedType.class)) {
            logger.info("Indexed-Type: ", base);

            @SuppressWarnings({ "rawtypes", "unchecked" })//
            TypeCollector<?> collector = new NetBodzTypeCollector(base, null);

            collector.collect();
        }
    }

}
