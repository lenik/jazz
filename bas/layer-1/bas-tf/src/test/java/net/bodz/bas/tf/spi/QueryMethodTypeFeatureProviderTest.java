package net.bodz.bas.tf.spi;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.tf.std.AbstractCommonTypeFeatures;
import net.bodz.bas.tf.std.IFormatter;

public class QueryMethodTypeFeatureProviderTest
        extends Assert {

    static class StaticFoo {

        static <T> T query(Class<T> typeFeatureClass) {
            if (typeFeatureClass.isAssignableFrom(BlahTypeFeatures.class)) {
                BlahTypeFeatures typeFeatures = new BlahTypeFeatures("static");
                return typeFeatureClass.cast(typeFeatures);
            }
            return null;
        }
    }

    static class NonstaticFoo {

        private String user;

        public NonstaticFoo(String user) {
            this.user = user;
        }

        <T> T query(Class<T> typeFeatureClass) {
            if (typeFeatureClass.isAssignableFrom(BlahTypeFeatures.class)) {
                BlahTypeFeatures typeFeatures = new BlahTypeFeatures(user);
                return typeFeatureClass.cast(typeFeatures);
            }
            return null;
        }

    }

    static class BlahTypeFeatures
            extends AbstractCommonTypeFeatures<Object> {

        private String message;

        public BlahTypeFeatures(String blah) {
            super(Object.class);
            this.message = blah;
        }

        @Override
        protected Object _query(int typeFeatureIndex) {
            switch (typeFeatureIndex) {
            case IFormatter.typeFeatureIndex:
                return this;
            }
            return null;
        }

        @Override
        public String format(Object object) {
            return message + " blah";
        }

    }

    QueryMethodTypeFeatureProvider provider = new QueryMethodTypeFeatureProvider();

    @Test
    public void testGetTypeFeaturesStaticDefined() {
        IFormatter<StaticFoo> formatter = provider.getTypeFeature(StaticFoo.class, IFormatter.class);
        assertNotNull(formatter);

        String format = formatter.format(new StaticFoo());
        assertEquals("static blah", format);
    }

    @Test
    public void testGetTypeFeaturesStaticUndefined() {
        InputStream typeFeatures = provider.getTypeFeature(StaticFoo.class, InputStream.class);
        assertNull(typeFeatures);
    }

    @Test
    public void testGetTypeFeaturesNonStaticDefined() {
        NonstaticFoo foo1 = new NonstaticFoo("foo1");
        NonstaticFoo foo2 = new NonstaticFoo("foo2");

        IFormatter<NonstaticFoo> formatter1 = provider.getTypeFeature(NonstaticFoo.class, foo1, IFormatter.class);
        IFormatter<NonstaticFoo> formatter2 = provider.getTypeFeature(NonstaticFoo.class, foo2, IFormatter.class);
        assertNotNull(formatter1);
        assertNotNull(formatter2);

        assertEquals("foo1 blah", formatter1.format(foo1));
        assertEquals("foo2 blah", formatter2.format(foo2));
    }

    @Test
    public void testGetTypeFeaturesNonStaticUndefined() {
        InputStream typeFeatures = provider.getTypeFeature(NonstaticFoo.class, InputStream.class);
        assertNull(typeFeatures);
    }

}
