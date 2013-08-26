package net.bodz.bas.mf.spi;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.mf.std.AbstractCommonMdaFeatures;
import net.bodz.bas.mf.std.IFormatter;

public class QueryMethodMdaFeaturesProviderTest
        extends Assert {

    static class StaticFoo {

        static <T> T query(Class<T> mdaFeaturesType) {
            if (mdaFeaturesType.isAssignableFrom(BlahMdaFeatures.class)) {
                BlahMdaFeatures mdaFeatures = new BlahMdaFeatures("static");
                return mdaFeaturesType.cast(mdaFeatures);
            }
            return null;
        }
    }

    static class NonstaticFoo {

        private String user;

        public NonstaticFoo(String user) {
            this.user = user;
        }

        <T> T query(Class<T> mdaFeaturesType) {
            if (mdaFeaturesType.isAssignableFrom(BlahMdaFeatures.class)) {
                BlahMdaFeatures mdaFeatures = new BlahMdaFeatures(user);
                return mdaFeaturesType.cast(mdaFeatures);
            }
            return null;
        }

    }

    static class BlahMdaFeatures
            extends AbstractCommonMdaFeatures<Object> {

        private String message;

        public BlahMdaFeatures(String blah) {
            super(Object.class);
            this.message = blah;
        }

        @Override
        protected Object _query(int mdaFeatureIndex) {
            switch (mdaFeatureIndex) {
            case IFormatter.mdaFeatureIndex:
                return this;
            }
            return null;
        }

        @Override
        public String format(Object object) {
            return message + " blah";
        }

    }

    QueryMethodMdaFeaturesProvider provider = new QueryMethodMdaFeaturesProvider();

    @Test
    public void testGetMdaFeaturesStaticDefined() {
        IFormatter<StaticFoo> formatter = provider.getMdaFeature(StaticFoo.class, IFormatter.class);
        assertNotNull(formatter);

        String format = formatter.format(new StaticFoo());
        assertEquals("static blah", format);
    }

    @Test
    public void testGetMdaFeaturesStaticUndefined() {
        InputStream mdaFeatures = provider.getMdaFeature(StaticFoo.class, InputStream.class);
        assertNull(mdaFeatures);
    }

    @Test
    public void testGetMdaFeaturesNonStaticDefined() {
        NonstaticFoo foo1 = new NonstaticFoo("foo1");
        NonstaticFoo foo2 = new NonstaticFoo("foo2");

        IFormatter<NonstaticFoo> formatter1 = provider.getMdaFeature(NonstaticFoo.class, foo1, IFormatter.class);
        IFormatter<NonstaticFoo> formatter2 = provider.getMdaFeature(NonstaticFoo.class, foo2, IFormatter.class);
        assertNotNull(formatter1);
        assertNotNull(formatter2);

        assertEquals("foo1 blah", formatter1.format(foo1));
        assertEquals("foo2 blah", formatter2.format(foo2));
    }

    @Test
    public void testGetMdaFeaturesNonStaticUndefined() {
        InputStream mdaFeatures = provider.getMdaFeature(NonstaticFoo.class, InputStream.class);
        assertNull(mdaFeatures);
    }

}
