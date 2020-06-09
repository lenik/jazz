package net.bodz.violet.fab;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class FabStdTestParameter
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public interface Props {
        String expected = "expected";
        String format = "format";
        String range = "range";
    }

    FabStdTest test;
    boolean required;
    String expected;

    public FabStdTestParameter() {
    }

    public FabStdTest getTest() {
        return test;
    }

    public void setTest(FabStdTest test) {
        this.test = test;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

}
