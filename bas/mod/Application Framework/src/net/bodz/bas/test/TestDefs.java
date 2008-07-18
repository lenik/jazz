package net.bodz.bas.test;

import static net.bodz.bas.test.Relations.ArrayEQU;
import static net.bodz.bas.test.Relations.ArrayNEQ;
import static net.bodz.bas.test.Relations.EQ;
import static net.bodz.bas.test.Relations.EQU;
import static net.bodz.bas.test.Relations.EQf;
import static net.bodz.bas.test.Relations.F;
import static net.bodz.bas.test.Relations.GE;
import static net.bodz.bas.test.Relations.GT;
import static net.bodz.bas.test.Relations.InstOf;
import static net.bodz.bas.test.Relations.LE;
import static net.bodz.bas.test.Relations.LT;
import static net.bodz.bas.test.Relations.N;
import static net.bodz.bas.test.Relations.NE;
import static net.bodz.bas.test.Relations.NEQ;
import static net.bodz.bas.test.Relations.NEf;
import static net.bodz.bas.test.Relations.NN;
import static net.bodz.bas.test.Relations.OK;
import static net.bodz.bas.test.Relations.T;
import static net.bodz.bas.test.Relations.Throws;
import static net.bodz.bas.test.Relations._EQ;
import static net.bodz.bas.test.Relations._NE;

/**
 * Recommend code template `tst':
 * 
 * <pre>
 * ${:importStatic(
 *     'net.bodz.bas.test.TestDefs.*'
 * )}TestDefs.tests(new TestEval&lt;${Object}&gt;() {
 *     public Object eval(${Object} input) throws Throwable {
 *         return input;
 *     }
 * }, //
 *         EQ(&quot;example test&quot;, 1 + 1, 2), //
 *         EQ(&quot;example test&quot;, 1 + 1, 2), //
 *         END
 * );
 * </pre>
 */
public class TestDefs {

    private String    groupName;
    private TestDef[] defs;

    public TestDefs(String groupName, TestDef... defs) {
        this.groupName = groupName;
        this.defs = defs;
    }

    public TestDefs(TestDef... defs) {
        this(null, defs);
    }

    public <T> void test(TestEval<T> eval) {
        if (groupName == null) {
            for (TestDef def : defs)
                def.test(eval);
        } else {
            for (int i = 0; i < defs.length; i++) {
                TestDef def = defs[i];
                String comment = "[" + i + "]";
                if (groupName != null)
                    comment = groupName + comment;
                if (def.comment != null)
                    comment += " " + def.comment;
                def = new TestDef(comment, def.input, def.relation,
                        def.expected);
                def.test(eval);
            }
        }
    }

    public static <T> void tests(String groupName, TestEval<T> eval,
            TestDef... defs) {
        TestDefs _defs = new TestDefs(groupName, defs);
        _defs.test(eval);
    }

    public static <T> void tests(TestEval<T> eval, TestDef... defs) {
        TestDefs _defs = new TestDefs(defs);
        _defs.test(eval);
    }

    public static TestDef entry(String comment, Relation relation,
            Object input, Object expected) {
        return new TestDef(comment, input, relation, expected);
    }

    public static TestDef entry(Relation relation, Object input, Object expected) {
        return new TestDef(null, input, relation, expected);
    }

    public static final TestDef END;
    static {
        END = new TestDef(null, null, OK, null) {
            @Override
            public <T> void test(TestEval<T> eval) {
            }
        };
    }

    public static TestDef OK(String comment, Object input) {
        return new TestDef(comment, input, OK, null);
    }

    public static TestDef OK(Object input) {
        return new TestDef(null, input, OK, null);
    }

    public static TestDef T(String comment, Object input) {
        return new TestDef(comment, input, T, null);
    }

    public static TestDef T(Object input) {
        return new TestDef(null, input, T, null);
    }

    public static TestDef F(String comment, Object input) {
        return new TestDef(comment, input, F, null);
    }

    public static TestDef F(Object input) {
        return new TestDef(null, input, F, null);
    }

    public static TestDef N(String comment, Object input) {
        return new TestDef(comment, input, N, null);
    }

    public static TestDef N(Object input) {
        return new TestDef(null, input, N, null);
    }

    public static TestDef NN(String comment, Object input) {
        return new TestDef(comment, input, NN, null);
    }

    public static TestDef NN(Object input) {
        return new TestDef(null, input, NN, null);
    }

    public static TestDef EQU(String comment, Object input, Object expected) {
        return new TestDef(comment, input, EQU, expected);
    }

    public static TestDef EQU(Object input, Object expected) {
        return new TestDef(null, input, EQU, expected);
    }

    public static TestDef NEQ(String comment, Object input, Object expected) {
        return new TestDef(comment, input, NEQ, expected);
    }

    public static TestDef NEQ(Object input, Object expected) {
        return new TestDef(null, input, NEQ, expected);
    }

    public static TestDef ArrayEQU(String comment, Object input, Object expected) {
        return new TestDef(comment, input, ArrayEQU, expected);
    }

    public static TestDef ArrayEQU(Object input, Object expected) {
        return new TestDef(null, input, ArrayEQU, expected);
    }

    public static TestDef ArrayNEQ(String comment, Object input, Object expected) {
        return new TestDef(comment, input, ArrayNEQ, expected);
    }

    public static TestDef ArrayNEQ(Object input, Object expected) {
        return new TestDef(null, input, ArrayNEQ, expected);
    }

    public static TestDef _EQ(String comment, Object input, Object expected) {
        return new TestDef(comment, input, _EQ, expected);
    }

    public static TestDef _EQ(Object input, Object expected) {
        return new TestDef(null, input, _EQ, expected);
    }

    public static TestDef _NE(String comment, Object input, Object expected) {
        return new TestDef(comment, input, _NE, expected);
    }

    public static TestDef _NE(Object input, Object expected) {
        return new TestDef(null, input, _NE, expected);
    }

    public static TestDef EQ(String comment, Object input, Object expected) {
        return new TestDef(comment, input, EQ, expected);
    }

    public static TestDef EQ(Object input, Object expected) {
        return new TestDef(null, input, EQ, expected);
    }

    public static TestDef NE(String comment, Object input, Object expected) {
        return new TestDef(comment, input, NE, expected);
    }

    public static TestDef NE(Object input, Object expected) {
        return new TestDef(null, input, NE, expected);
    }

    public static TestDef LT(String comment, Object input, Object expected) {
        return new TestDef(comment, input, LT, expected);
    }

    public static TestDef LT(Object input, Object expected) {
        return new TestDef(null, input, LT, expected);
    }

    public static TestDef LE(String comment, Object input, Object expected) {
        return new TestDef(comment, input, LE, expected);
    }

    public static TestDef LE(Object input, Object expected) {
        return new TestDef(null, input, LE, expected);
    }

    public static TestDef GT(String comment, Object input, Object expected) {
        return new TestDef(comment, input, GT, expected);
    }

    public static TestDef GT(Object input, Object expected) {
        return new TestDef(null, input, GT, expected);
    }

    public static TestDef GE(String comment, Object input, Object expected) {
        return new TestDef(comment, input, GE, expected);
    }

    public static TestDef GE(Object input, Object expected) {
        return new TestDef(null, input, GE, expected);
    }

    public static TestDef EQf(String comment, Object input, Object expected) {
        return new TestDef(comment, input, EQf, expected);
    }

    public static TestDef EQf(Object input, Object expected) {
        return new TestDef(null, input, EQf, expected);
    }

    public static TestDef NEf(String comment, Object input, Object expected) {
        return new TestDef(comment, input, NEf, expected);
    }

    public static TestDef NEf(Object input, Object expected) {
        return new TestDef(null, input, NEf, expected);
    }

    public static TestDef InstOf(String comment, Object input, Object expected) {
        return new TestDef(comment, input, InstOf, expected);
    }

    public static TestDef InstOf(Object input, Object expected) {
        return new TestDef(null, input, InstOf, expected);
    }

    public static TestDef Throws(String comment, Object input, Object expected) {
        return new TestDef(comment, input, Throws, expected);
    }

    public static TestDef Throws(Object input, Object expected) {
        return new TestDef(null, input, Throws, expected);
    }

}
