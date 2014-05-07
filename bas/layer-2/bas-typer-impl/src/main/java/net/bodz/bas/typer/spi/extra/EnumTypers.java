package net.bodz.bas.typer.spi.extra;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class EnumTypers<T extends Enum<T>>
        extends AbstractCommonTypers<T> {

    private List<T> values;

    public EnumTypers(Class<T> type) {
        super(type);
    }

    @Override
    protected Object queryInt(int typerIndex) {
        switch (typerIndex) {
        case IParser.typerIndex:
        case ISampleGenerator.typerIndex:
            return this;
        }
        return null;
    }

    @Override
    public T parse(String text, IOptions options)
            throws ParseException {
        try {
            T value = Enum.valueOf(type, text);
            return value;
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public T newSample(IOptions options)
            throws CreateException {
        Random prng = options.get(Random.class, random);

        if (values == null) {
            EnumSet<T> all = EnumSet.allOf(type);
            values = new ArrayList<T>(all);
        }

        int index = prng.nextInt() % values.size();
        return values.get(index);
    }

}
