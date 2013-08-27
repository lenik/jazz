package net.bodz.bas.typer.spi;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.typer.std.IParser;

public class BasCompanionTyperProviderTest
        extends Assert {

    @Test
    public void getIntegerTypers() {
        BasCompanionTyperProvider provider = new BasCompanionTyperProvider();
        IParser<Integer> parser = provider.getTyper(Integer.class, IParser.class);
        assertNotNull(parser);
    }

}
