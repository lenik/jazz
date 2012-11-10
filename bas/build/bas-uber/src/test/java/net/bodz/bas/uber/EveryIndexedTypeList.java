package net.bodz.bas.uber;

import net.bodz.bas.c.type.TypeExtensions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.codegen.IndexedType;

public class EveryIndexedTypeList {

    static Logger logger = LoggerFactory.getLogger(EveryIndexedTypeList.class);

    public static void main(String[] args)
            throws Exception {
        for (Class<?> base : TypeExtensions.forClassWithAnnotation(IndexedType.class)) {
            logger.info("Indexed-Type: ", base);

            for (Class<?> extension : TypeExtensions.forClass(base))
                logger.info("    Extension: ", extension);
        }
    }

}
