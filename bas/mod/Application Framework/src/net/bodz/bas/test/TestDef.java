package net.bodz.bas.test;

import net.bodz.bas.test.Relations.Throws;

public class TestDef {

    public final String   comment;
    public final Object   input;
    public final Relation relation;
    public final Object   expected;

    public TestDef(String comment, Object input, Relation relation,
            Object expected) {
        this.relation = relation;
        this.input = input;
        this.expected = expected;
        this.comment = comment;
    }

    public void check(Object actual) {
        relation.test(comment, expected, actual);
    }

    @SuppressWarnings("unchecked")
    public <T> void test(TestEval<T> eval) {
        if (relation instanceof Throws) {
            Throws throwsp = (Throws) relation;
            try {
                eval.eval((T) input);
            } catch (RuntimeException e) {
                throwsp.test(comment, expected, e);
            } catch (Error e) {
                throwsp.test(comment, expected, e);
            } catch (Throwable e) {
                throwsp.test(comment, expected, e);
            }
        } else {
            try {
                Object actual = eval.eval((T) input);
                check(actual);
            } catch (RuntimeException e) {
                throw e;
            } catch (Error e) {
                throw e;
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

}
