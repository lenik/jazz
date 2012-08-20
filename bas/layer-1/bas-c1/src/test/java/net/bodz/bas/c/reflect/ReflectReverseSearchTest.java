package net.bodz.bas.c.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import net.bodz.bas.lang.fn.Pred1;

public class ReflectReverseSearchTest
        extends Assert {

    public static final String X = "X";
    public static final String XX = "X";
    public static final String Y = "Y";
    public static final String Z = "Z";

    public static int HUNDRED = 100;
    public static int HYAKU = 100;
    public static int FIVE = 5;

    static class CatNames
            extends Pred1<Field> {
        List<String> list = new ArrayList<String>();

        @Override
        public boolean test(Field field) {
            list.add(field.getName());
            return true;
        }

        @Override
        public String toString() {
            Collections.sort(list);
            StringBuilder buf = new StringBuilder(list.size() * 16);
            for (int i = 0; i < list.size(); i++) {
                if (i != 0)
                    buf.append(",");
                buf.append(list.get(i));
            }
            return buf.toString();
        }
    }

    @Test
    public void testFindFieldClassOfQObjectObjectPredicateOfField() {
        class D {
            void o(Object value, String expected) {
                CatNames catNames = new CatNames();
                ReflectReverseSearch.findField(ReflectReverseSearchTest.class, null, value, catNames);
                String actual = catNames.toString();
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("X", "X,XX"); //
        d.o("Y", "Y"); //
        d.o("Z", "Z"); //
        d.o(100, "HUNDRED,HYAKU"); //
        d.o("No such value", ""); //
    }

    @Test
    public void testFindFieldObjectObjectPredicateOfField() {
        final ReflectReverseSearchTest obj = new ReflectReverseSearchTest();
        class D {
            void o(Object value, String expected) {
                CatNames catNames = new CatNames();
                ReflectReverseSearch.findField(obj, value, catNames);
                String actual = catNames.toString();
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("X", "X,XX"); //
        d.o("Y", "Y"); //
        d.o("Z", "Z"); //
        d.o(100, "HUNDRED,HYAKU"); //
        d.o("No such value", ""); //
    }

    @Test
    public void testGetFirstFieldNameClassOfQObjectObject() {
        class D {
            void o(Object value, String expected) {
                String actual = ReflectReverseSearch.getFirstFieldName(ReflectReverseSearchTest.class, null, value);
                assertEquals(expected, actual);
            }
        }
        D d = new D(); //
        d.o("X", "X"); // or XX?
        d.o("Y", "Y"); //
        d.o("Z", "Z"); //
        d.o(100, "HUNDRED"); // or HYAKU?
        d.o("No such value", null); //
    }

}
