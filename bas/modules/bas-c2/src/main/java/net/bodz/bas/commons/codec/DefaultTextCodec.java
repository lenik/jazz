package net.bodz.bas.commons.codec;

import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.commons.typeparser.TypeParsers;

public class DefaultTextCodec<T> extends _TextCodec<T> {

    private final Class<? extends T> type;
    private final Parser parser;

    public DefaultTextCodec(Class<? extends T> type) {
        this(type, TypeParsers.get(type));
    }

    public DefaultTextCodec(Class<? extends T> type, Parser parser) {
        if (type == null)
            throw new NullPointerException("type"); //$NON-NLS-1$
        if (parser == null)
            throw new NullPointerException("parser"); //$NON-NLS-1$
        this.type = type;
        this.parser = parser;
    }

    @Override
    public Class<? extends T> getDecodedType() {
        return type;
    }

    @Override
    public T decode(String encoded) {
        try {
            Object obj = parser.parse(encoded);
            return type.cast(obj);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
