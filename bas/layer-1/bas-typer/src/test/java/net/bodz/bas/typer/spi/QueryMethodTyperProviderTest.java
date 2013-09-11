package net.bodz.bas.typer.spi;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.typer.std.AbstractCommonTypers;
import net.bodz.bas.typer.std.IFormatter;

public class QueryMethodTyperProviderTest
        extends Assert {

    static class StaticFoo {

        static <T> T query(Class<T> typerClass) {
            if (typerClass.isAssignableFrom(BlahTypers.class)) {
                BlahTypers typers = new BlahTypers("static");
                return typerClass.cast(typers);
            }
            return null;
        }
    }

    static class NonstaticFoo {

        private String user;

        public NonstaticFoo(String user) {
            this.user = user;
        }

        <T> T query(Class<T> typerClass) {
            if (typerClass.isAssignableFrom(BlahTypers.class)) {
                BlahTypers typers = new BlahTypers(user);
                return typerClass.cast(typers);
            }
            return null;
        }

    }

    static class BlahTypers
            extends AbstractCommonTypers<Object> {

        private String message;

        public BlahTypers(String blah) {
            super(Object.class);
            this.message = blah;
        }

        @Override
        protected Object queryInt(int typerIndex) {
            switch (typerIndex) {
            case IFormatter.typerIndex:
                return this;
            }
            return null;
        }

        @Override
        public String format(Object object, IOptions options) {
            return message + " blah";
        }

    }

    QueryMethodTyperProvider provider = new QueryMethodTyperProvider();

    @Test
    public void testGetTypersStaticDefined() {
        IFormatter<StaticFoo> formatter = provider.getTyper(StaticFoo.class, IFormatter.class);
        assertNotNull(formatter);

        String format = formatter.format(new StaticFoo());
        assertEquals("static blah", format);
    }

    @Test
    public void testGetTypersStaticUndefined() {
        InputStream typers = provider.getTyper(StaticFoo.class, InputStream.class);
        assertNull(typers);
    }

    @Test
    public void testGetTypersNonStaticDefined() {
        NonstaticFoo foo1 = new NonstaticFoo("foo1");
        NonstaticFoo foo2 = new NonstaticFoo("foo2");

        IFormatter<NonstaticFoo> formatter1 = provider.getTyper(NonstaticFoo.class, foo1, IFormatter.class);
        IFormatter<NonstaticFoo> formatter2 = provider.getTyper(NonstaticFoo.class, foo2, IFormatter.class);
        assertNotNull(formatter1);
        assertNotNull(formatter2);

        assertEquals("foo1 blah", formatter1.format(foo1));
        assertEquals("foo2 blah", formatter2.format(foo2));
    }

    @Test
    public void testGetTypersNonStaticUndefined() {
        InputStream typers = provider.getTyper(NonstaticFoo.class, InputStream.class);
        assertNull(typers);
    }

}
