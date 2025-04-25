package net.bodz.bas.text.parser;

import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public interface IParseCallback<T>
        extends IParsedValueCallback<T>,
                IParseErrorCallback {

    static final Logger logger = LoggerFactory.getLogger(IParseCallback.class);

    @Override
    default boolean parseError(IParseContext context, Throwable e) {
        logger.error("error parse " + context.getSelection(), e);
        return true;
    }

}
