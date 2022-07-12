package net.bodz.bas.c.java.util;

import java.util.UUID;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class UUIDTypers
        extends AbstractCommonTypers<UUID> {

    public UUIDTypers() {
        super(UUID.class);
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
    public UUID parse(String text, IOptions options)
            throws ParseException {
        try {
            return UUID.fromString(text);
        } catch (Exception e) {
            throw new ParseException(e.getMessage(), e);
        }
    }

    @Override
    public String format(UUID object, IOptions options) {
        return object.toString();
    }

    @Override
    public UUID newSample(IOptions options)
            throws CreateException {
        return UUID.randomUUID();
    }

    public static final UUIDTypers INSTANCE = new UUIDTypers();

}
