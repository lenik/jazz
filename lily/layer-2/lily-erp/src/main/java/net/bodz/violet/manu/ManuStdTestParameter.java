package net.bodz.violet.manu;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public class ManuStdTestParameter
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public interface Props {
        String expected = "expected";
        String format = "format";
        String range = "range";
    }

    ManuStdTest test;
    boolean required;
    String expected;

    public ManuStdTestParameter() {
    }

    public ManuStdTest getTest() {
        return test;
    }

    public void setTest(ManuStdTest test) {
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
