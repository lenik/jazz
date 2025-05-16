package net.bodz.bas.c.java.util;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class UUIDTypersTest
        extends Assert {

    @Test
    public void testSample()
            throws ParseException {
        ISampleGenerator<UUID> sg = Typers.getGenericTyper(UUID.class, ISampleGenerator.class);
        UUID u = sg.newSample();
        String s = u.toString();
        System.out.println(s);
        IParser<UUID> parser = Typers.getGenericTyper(UUID.class, IParser.class);
        UUID u2 = parser.parse(s);
        assertEquals(u, u2);
    }

}