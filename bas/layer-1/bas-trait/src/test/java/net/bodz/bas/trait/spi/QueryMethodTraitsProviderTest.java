package net.bodz.bas.trait.spi;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.traits.AbstractCommonTraits;
import net.bodz.bas.traits.IFormatter;

public class QueryMethodTraitsProviderTest
        extends Assert {

    static class StaticFoo {

        static <T> T query(Class<T> traitsType) {
            if (traitsType.isAssignableFrom(BlahTraits.class)) {
                BlahTraits traits = new BlahTraits("static");
                return traitsType.cast(traits);
            }
            return null;
        }
    }

    static class NonstaticFoo {

        private String user;

        public NonstaticFoo(String user) {
            this.user = user;
        }

        <T> T query(Class<T> traitsType) {
            if (traitsType.isAssignableFrom(BlahTraits.class)) {
                BlahTraits traits = new BlahTraits(user);
                return traitsType.cast(traits);
            }
            return null;
        }

    }

    static class BlahTraits
            extends AbstractCommonTraits<Object> {

        private String message;

        public BlahTraits(String blah) {
            super(Object.class);
            this.message = blah;
        }

        @Override
        protected Object query(int traitIndex) {
            switch (traitIndex) {
            case IFormatter.traitIndex:
                return this;
            }
            return null;
        }

        @Override
        public String format(Object object) {
            return message + " blah";
        }

    }

    QueryMethodTraitsProvider provider = new QueryMethodTraitsProvider();

    @Test
    public void testGetTraitsStaticDefined() {
        IFormatter<StaticFoo> formatter = provider.getTrait(StaticFoo.class, IFormatter.class);
        assertNotNull(formatter);

        String format = formatter.format(new StaticFoo());
        assertEquals("static blah", format);
    }

    @Test
    public void testGetTraitsStaticUndefined() {
        InputStream traits = provider.getTrait(StaticFoo.class, InputStream.class);
        assertNull(traits);
    }

    @Test
    public void testGetTraitsNonStaticDefined() {
        NonstaticFoo foo1 = new NonstaticFoo("foo1");
        NonstaticFoo foo2 = new NonstaticFoo("foo2");

        IFormatter<NonstaticFoo> formatter1 = provider.getTrait(NonstaticFoo.class, foo1, IFormatter.class);
        IFormatter<NonstaticFoo> formatter2 = provider.getTrait(NonstaticFoo.class, foo2, IFormatter.class);
        assertNotNull(formatter1);
        assertNotNull(formatter2);

        assertEquals("foo1 blah", formatter1.format(foo1));
        assertEquals("foo2 blah", formatter2.format(foo2));
    }

    @Test
    public void testGetTraitsNonStaticUndefined() {
        InputStream traits = provider.getTrait(NonstaticFoo.class, InputStream.class);
        assertNull(traits);
    }

}
