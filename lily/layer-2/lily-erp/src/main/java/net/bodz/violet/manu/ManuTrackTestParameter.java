package net.bodz.violet.manu;

import javax.persistence.Table;

import net.bodz.bas.meta.cache.Derived;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@Table(name = "manutrack_test_parm")
@IdType(Long.class)
public class ManuTrackTestParameter
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    ManuTrackTest test;
    ManuStdTestParameter parameter;
    ManuStdTester tester;
    String actual;
    boolean valid;

    public ManuTrackTestParameter() {
    }

    public ManuTrackTest getTest() {
        return test;
    }

    public void setTest(ManuTrackTest test) {
        this.test = test;
    }

    public ManuStdTestParameter getParameter() {
        return parameter;
    }

    public void setParameter(ManuStdTestParameter parameter) {
        this.parameter = parameter;
    }

    public ManuStdTester getTester() {
        return tester;
    }

    public void setTester(ManuStdTester tester) {
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
