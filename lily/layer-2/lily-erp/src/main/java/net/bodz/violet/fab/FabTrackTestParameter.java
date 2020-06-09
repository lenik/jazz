package net.bodz.violet.fab;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "fabtrack_test_parm")
@IdType(Long.class)
public class FabTrackTestParameter
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    FabTrackTest test;
    FabStdTestParameter parameter;
    FabStdTester tester;
    String actual;
    boolean valid;

    public FabTrackTestParameter() {
    }

    public FabTrackTest getTest() {
        return test;
    }

    public void setTest(FabTrackTest test) {
        this.test = test;
    }

    public FabStdTestParameter getParameter() {
        return parameter;
    }

    public void setParameter(FabStdTestParameter parameter) {
        this.parameter = parameter;
    }

    public FabStdTester getTester() {
        return tester;
    }

    public void setTester(FabStdTester tester) {
        this.tester = tester;
    }

    @Derived
    public boolean isAutomated() {
        return tester != null;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
