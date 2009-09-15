package net.bodz.bas.lop;

import static net.bodz.bas.test.TestDefs.END;
import static net.bodz.bas.test.TestDefs.EQ;
import static net.bodz.bas.test.TestDefs.EQU;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.lang.Pred1;
import net.bodz.bas.lop.ReflectUtil;
import net.bodz.bas.test.TestDefs;
import net.bodz.bas.test.TestEval;
import net.bodz.bas.test._TestEval;
import net.bodz.bas.types.util.Strings;

import org.junit.Test;

public class ReflectUtilTest {

    public static final String X       = "X";
    public static final String XX      = "X";
    public static final String Y       = "Y";
    public static final String Z       = "Z";

    public static int          HUNDRED = 100;
    public static int          HYAKU   = 100;
    public static int          FIVE    = 5;

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
        TestDefs.tests(new TestEval<Object>() {
            public Object eval(Object value) throws Throwable {
                CatNames catNames = new CatNames();
                ReflectUtil.findField(ReflectUtilTest.class, null, value,
                        catNames);
                return catNames.toString();
            }
        }, //
                EQ("X", "X,XX"), //
                EQ("Y", "Y"), //
                EQ("Z", "Z"), //
                EQ(100, "HUNDRED,HYAKU"), //
                EQ("No such value", ""), //
                END);
    }

    @Test
    public void testFindFieldObjectObjectPredicateOfField() {
        final ReflectUtilTest obj = new ReflectUtilTest();
        TestDefs.tests(new TestEval<Object>() {
            public Object eval(Object value) throws Throwable {
                CatNames catNames = new CatNames();
                ReflectUtil.findField(obj, value, catNames);
                return catNames.toString();
            }
        }, //
                EQ("X", "X,XX"), //
                EQ("Y", "Y"), //
                EQ("Z", "Z"), //
                EQ(100, "HUNDRED,HYAKU"), //
                EQ("No such value", ""), //
                END);
    }

    @Test
    public void testGetFirstFieldNameClassOfQObjectObject() {
        TestDefs.tests(new _TestEval<Object>() {
            public Object eval(Object value) throws Throwable {
                if (isBreakpoint())
                    System.out.println(value);
                return ReflectUtil.getFirstFieldName(ReflectUtilTest.class,
                        null, value);
            }
        }, //
                EQ("X", "X"), // or XX?
                EQ("Y", "Y"), //
                EQ("Z", "Z"), //
                EQ(100, "HUNDRED"), // or HYAKU?
                EQU("No such value", null), //
                END);
    }

}
