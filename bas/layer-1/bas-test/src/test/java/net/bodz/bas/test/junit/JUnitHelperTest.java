package net.bodz.bas.test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitHelperTest
        extends Assert {

    public static class Example {

        @BeforeClass
        public static void beforeClass() {
            System.err.println("beforeClass");
        }

        @AfterClass
        public static void afterClass() {
            System.err.println("afterClass");
        }

        @Before
        public void beforeMethod() {
            System.err.println("beforeMethod");
        }

        @After
        public void afterMethod() {
            System.err.println("afterMethod");
        }

        public void play() {
            System.err.println("Play");
        }

        @Override
        public String toString() {
            return "Example@" + getClass().getSimpleName();
        }

    }

    @Test
    public void test() {
        Example example = JUnitHelper.createWrappedInstance(Example.class);
        example.play();
    }

}
