package net.bodz.bas.reflect.query;

import org.junit.Assert;

public class ReflectQueryTestBase
        extends Assert {

    public static class A {
        public void f_public() {
        }

        void f_package() {
        }

        protected void f_protected() {
            f_private();
        }

        private void f_private() {
        }
    }

    public static class A1
            extends A {
        @Override
        public void f_public() {
        }

        public void f_public1() {
        }
    }

    public static class A2
            extends A1 {

        @Override
        protected void f_protected() {
        }

        protected void f_protected2() {
        }
    }

}
