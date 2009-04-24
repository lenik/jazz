package net.bodz.bas.codec;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;

public class DefaultTextCodec<T> extends _TextCodec<T> {

    private final Class<? extends T> type;
    private final TypeParser         parser;

    public DefaultTextCodec(Class<? extends T> type) {
        this(type, TypeParsers.get(type));
    }

    public DefaultTextCodec(Class<? extends T> type, TypeParser parser) {
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
