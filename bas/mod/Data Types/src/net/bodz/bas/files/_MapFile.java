package net.bodz.bas.files;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;

public abstract class _MapFile<T> extends _FileSource<T> {

    public _MapFile(Object in, Object charset) {
        super(in, charset);
    }

    public _MapFile(Object in) {
        super(in);
    }

    protected TypeParser keyParser;
    protected TypeParser valueParser;
    {
        try {
            this.keyParser = TypeParsers.guess(getKeyClass(), "KeyClass"); //$NON-NLS-1$
            this.valueParser = TypeParsers.guess(getValueClass(), "ValueClass"); //$NON-NLS-1$
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    protected Class<?> getKeyClass() {
        return String.class;
    }

    protected Class<?> getValueClass() {
        return String.class;
    }

    protected Object parseKey(String key) throws ParseException {
        return keyParser.parse(key);
    }

    protected Object parseValue(String value) throws ParseException {
        return valueParser.parse(value);
    }

}
