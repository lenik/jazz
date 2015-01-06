package net.bodz.bas.t.predef;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class PredefTypers<T extends Predef<T, K>, K extends Comparable<K>>
        extends AbstractCommonTypers<T> {

    private PredefMetadata<T, K> metadata;
    private List<T> values;

    public PredefTypers(Class<T> type) {
        super(type);

        metadata = PredefMetadata.forClass(type);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IFormatter.typerIndex:
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public String format(T object, IOptions options) {
        return object.name;
    }

    @Override
    public T parse(String text, IOptions options)
            throws ParseException {
        T object = metadata.ofName(text);
        return object;
    }

    @Override
    public T newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);

        if (values == null) {
            values = new ArrayList<T>(metadata.getValues());
        }

        int index = prng.nextInt() % values.size();
        return values.get(index);
    }

}