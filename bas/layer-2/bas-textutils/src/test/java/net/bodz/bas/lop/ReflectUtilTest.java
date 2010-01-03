package net.bodz.bas.lop;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.lang2.Pred1;
import net.bodz.bas.text.util.Strings;

import org.junit.Test;

public class ReflectUtilTest {

    public static final String X = "X";
    public static final String XX = "X";
    public static final String Y = "Y";
    public static final String Z = "Z";

    public static int HUNDRED = 100;
    public static int HYAKU = 100;
    public static int FIVE = 5;

    static class CatNames extends Pred1<Field> {
        List<String> list = new ArrayList<String>();

        @Override
        public boolean test(Field field) {
            list.add(field.getName());
            return true;
        }

        @Override
        public String toString() {
            Collections.sort(list);
            return Strings.join(",", list);
        }
    }

    @Test
    public void testFindFieldClassOfQObjectObjectPredicateOfField() {
        class D {
            void o(Object value, String expected) {
                CatNames catNames = new CatNames();
                ReflectUtil.findField(ReflectUtilTest.class, null, value, catNames);
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
        final ReflectUtilTest obj = new ReflectUtilTest();
        class D {
            void o(Object value, String expected) {
                CatNames catNames = new CatNames();
                ReflectUtil.findField(obj, value, catNames);
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
                String actual = ReflectUtil.getFirstFieldName(ReflectUtilTest.class, null, value);
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
