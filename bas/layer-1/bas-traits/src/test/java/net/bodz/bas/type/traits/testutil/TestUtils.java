package net.bodz.bas.type.traits.testutil;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.type.traits.IFormatter;
import net.bodz.bas.type.traits.IParser;
import net.bodz.bas.util.exception.ParseException;

public class TestUtils {

    public static <T> void testConvertLoss(IParser<T> parser, IFormatter<T> formatter, T value)
            throws ParseException {
        String text = formatter.format(value);
        T value1 = parser.parse(text);
        assertEquals("convert-loss-1v", value, value1);
        String text1 = formatter.format(value1);
        assertEquals("convert-loss-2t", text, text1);
        T value2 = parser.parse(text1);
        assertEquals("convert-loss-2v", value, value2);
    }

}
