package net.bodz.bas.test;

public class TestDef {

    public String         comment;
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
    public <T> void test(TestEval<T> eval, TestDefs defs) {
        try {
            if (eval instanceof _TestEval && defs != null)
                ((_TestEval) eval).setBreakpoint(defs.inBreakpoint());
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
