package net.bodz.bas.aspect.typeinfo;

import static org.junit.Assert.assertEquals;
import net.bodz.bas.api.exceptions.ParseException;

public class TestUtils {

    public static <T> void testConvertLoss(Parser<T> parser, Formatter<T> formatter, T value)
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
