package net.bodz.bas.typer.spi.array;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.typer.Typers;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.bas.typer.std.ISampleGenerator;

public class ArrayTyperProviderTest
        extends Assert {

    @Test
    public void generateFormatParse()
            throws FormatException, ParseException {
        ISampleGenerator<byte[]> generator = Typers.getGenericTyper(byte[].class, ISampleGenerator.class);
        byte[] bytes = generator.newSample();

        IFormatter<byte[]> formatter = Typers.getGenericTyper(byte[].class, IFormatter.class);
        String str = formatter.format(bytes);
        System.out.println(str);

        IParser<byte[]> parser = Typers.getGenericTyper(byte[].class, IParser.class);
        byte[] restore = parser.parse(str);
        Assert.assertArrayEquals(bytes, restore);
    }

}
