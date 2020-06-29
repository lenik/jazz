package net.bodz.bas.t.range;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IParser;

public class AbstractRangeParserTest
        extends Assert {

    @Test
    public void testParse()
            throws ParseException {
        IntRange range = new IntRange();
        range.parse("[2, 3)");
        IntRange expected = new IntRange(true, 2, false, 3);
        assertEquals(expected, range);
    }

    @Test
    public void testResolveParser()
            throws ParseException {
        IParser<IntRange> parser = Typers.getTyper(IntRange.class, IParser.class);
        if (parser == null)
            throw new NotImplementedException("no parser");
        IntRange range = parser.parse("(10, 30]");
        IntRange expected = new IntRange(false, 10, true, 30);
        assertEquals(expected, range);
    }

}
