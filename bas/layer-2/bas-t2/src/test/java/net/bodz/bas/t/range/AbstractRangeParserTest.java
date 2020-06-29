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
        IntegerRange range = new IntegerRange();
        range.parse("[2, 3)");
        IntegerRange expected = new IntegerRange(true, 2, false, 3);
        assertEquals(expected, range);
    }

    @Test
    public void testResolveParser()
            throws ParseException {
        IParser<IntegerRange> parser = Typers.getTyper(IntegerRange.class, IParser.class);
        if (parser == null)
            throw new NotImplementedException("no parser");
        IntegerRange range = parser.parse("(10, 30]");
        IntegerRange expected = new IntegerRange(false, 10, true, 30);
        assertEquals(expected, range);
    }

}
