package net.bodz.bas.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Array;

public class Relations {

    public static void _fail(String comment, Object expected, Object actual) {
        StringBuffer buffer = new StringBuffer();
        if (comment != null) {
            buffer.append(comment);
            if (comment.length() > 0)
                buffer.append(' ');
        }
        buffer.append("(expected=");
        buffer.append(expected);
        buffer.append(", actual=");
        buffer.append(actual);
        buffer.append(")");
        fail(buffer.toString());
    }

    public static class OK implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
        }
    }

    public static class T implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertTrue(comment, (Boolean) actual);
        }
    }

    public static class F implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertFalse(comment, (Boolean) actual);
        }
    }

    public static class N implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertNull(comment, actual);
        }
    }

    public static class NN implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertNotNull(comment, actual);
        }
    }

    public static class EQU implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertEquals(comment, expected, actual);
        }
    }

    public static class NEQ implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            if (expected == actual)
                _fail(comment, expected, actual);
            if (expected != null && expected.equals(actual))
                _fail(comment, expected, actual);
        }
    }

    public static class ArrayEQU implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertArrayEquals(comment, (Object[]) expected, (Object[]) actual);
        }
    }

    public static class ArrayNEQ implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            if (expected == null)
                assertNotNull(comment, actual);
            else {
                int len1 = Array.getLength(expected);
                int len2 = Array.getLength(actual);
                if (len1 != len2)
                    return;
                int len = Math.min(len1, len2);
                for (int i = 0; i < len; i++) {
                    Object e = Array.get(expected, i);
                    Object a = Array.get(actual, i);
                    if (e == a)
                        continue;
                    if (e == null || a == null)
                        return;
                    if (!e.equals(a))
                        return;
                }
                fail(comment);
            }
        }
    }

    public static class _EQ implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertSame(comment, expected, actual);
        }
    }

    public static class _NE implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assertNotSame(comment, expected, actual);
        }
    }

    static abstract class Compare implements Relation {
        @SuppressWarnings("unchecked")
        @Override
        public final void test(String comment, Object expected, Object actual) {
            assert expected != null : "use EQU/NEQ to compare with nulls";
            assert actual != null : "use EQU/NEQ to compare with nulls";
            assert expected instanceof Comparable;
            Comparable<Object> _expected = (Comparable<Object>) expected;
            if (!test(_expected.compareTo(actual)))
                _fail(comment, expected, actual);
        }

        protected abstract boolean test(int cmp);
    }

    public static class EQ extends Compare {
        @Override
        protected boolean test(int comparation) {
            return comparation == 0;
        }
    }

    public static class NE extends Compare {
        @Override
        protected boolean test(int comparation) {
            return comparation != 0;
        }
    }

    public static class LT extends Compare {
        @Override
        protected boolean test(int comparation) {
            return comparation < 0;
        }
    }

    public static class LE extends Compare {
        @Override
        protected boolean test(int comparation) {
            return comparation <= 0;
        }
    }

    public static class GT extends Compare {
        @Override
        protected boolean test(int comparation) {
            return comparation > 0;
        }
    }

    public static class GE extends Compare {
        @Override
        protected boolean test(int comparation) {
            return comparation >= 0;
        }
    }

    static abstract class _Number implements Relation {
        public void test(String comment, Object expected, Object actual) {
            assert expected instanceof Number;
            Number _expected = (Number) expected;
            if (!(actual instanceof Number))
                throw new IllegalArgumentException("not a number: " + actual);
            Number _actual = (Number) actual;
            if (!test(_expected, _actual))
                _fail(comment, _expected, _actual);
        }

        protected abstract boolean test(Number expected, Number actual);
    }

    public static class EQf extends _Number {
        protected double error;

        public EQf(double error) {
            this.error = error;
        }

        public EQf() {
            this(Double.MIN_VALUE);
        }

        @Override
        protected boolean test(Number expected, Number actual) {
            double a = expected.doubleValue();
            double b = actual.doubleValue();
            return Math.abs(a - b) < error;
        }
    }

    public static class NEf extends _Number {
        protected double error;

        public NEf(double error) {
            this.error = error;
        }

        public NEf() {
            this(Double.MIN_VALUE);
        }

        @Override
        protected boolean test(Number expected, Number actual) {
            double a = expected.doubleValue();
            double b = actual.doubleValue();
            return Math.abs(a - b) >= error;
        }
    }

    public static class InstOf implements Relation {
        @Override
        public void test(String comment, Object expected, Object actual) {
            assert expected instanceof Class;
            Class<?> _class = (Class<?>) expected;
            assertTrue(comment, _class.isInstance(actual));
        }
    }

    public static class ArrayAll implements Relation {
        protected final Relation component;

        public ArrayAll(Relation component) {
            assert component != null;
            this.component = component;
        }

        public void test(String comment, Object expected, Object actual) {
            if (expected == null) {
                assertNull(comment, actual);
                return;
            }
            if (comment == null)
                comment = "";
            else
                comment += ": ";
            int expectedLength = Array.getLength(expected);
            int actualLength = Array.getLength(actual);
            assertEquals(comment + "length", expectedLength, actualLength);
            for (int i = 0; i < expectedLength; i++) {
                Object exp = Array.get(expected, i);
                Object act = Array.get(actual, i);
                component.test(comment + "[" + i + "]", exp, act);
            }
        }
    }

    public static class ArrayAny implements Relation {
        protected final Relation component;

        public ArrayAny(Relation component) {
            assert component != null;
            this.component = component;
        }

        public void test(String comment, Object expected, Object actual) {
            if (expected == null) {
                assertNull(comment, actual);
                return;
            }
            if (comment == null)
                comment = "";
            else
                comment += ": ";
            int expectedLength = Array.getLength(expected);
            int actualLength = Array.getLength(actual);
            assertEquals(comment + "length", expectedLength, actualLength);
            AssertionError error1 = null;
            for (int i = 0; i < expectedLength; i++) {
                Object exp = Array.get(expected, i);
                Object act = Array.get(actual, i);
                try {
                    component.test(comment + "[" + i + "]", exp, act);
                    // any succeeded.
                    return;
                } catch (AssertionError e) {
                    if (error1 != null)
                        error1 = e;
                }
            }
            if (error1 != null)
                throw error1;
        }
    }

    public static final OK       OK       = new OK();
    public static final T        T        = new T();
    public static final F        F        = new F();
    public static final N        N        = new N();
    public static final NN       NN       = new NN();
    public static final EQU      EQU      = new EQU();
    public static final NEQ      NEQ      = new NEQ();
    public static final ArrayEQU ArrayEQU = new ArrayEQU();
    public static final ArrayNEQ ArrayNEQ = new ArrayNEQ();
    public static final _EQ      _EQ      = new _EQ();
    public static final _NE      _NE      = new _NE();
    public static final EQ       EQ       = new EQ();
    public static final NE       NE       = new NE();
    public static final LT       LT       = new LT();
    public static final LE       LE       = new LE();
    public static final GT       GT       = new GT();
    public static final GE       GE       = new GE();
    public static final EQf      EQf      = new EQf();
    public static final NEf      NEf      = new NEf();
    public static final InstOf   InstOf   = new InstOf();

    public static final ArrayAll ArrayAll(Relation component) {
        return new ArrayAll(component);
    }

    public static final ArrayAny ArrayAny(Relation component) {
        return new ArrayAny(component);
    }

}
