package net.bodz.violet.fab;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _FabTrackTestParameter_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabtrack_test_parm";

    public static final String FIELD_ID = "id";
    public static final String FIELD_TEST_ID = "test";
    public static final String FIELD_PARAMETER_ID = "parm";
    public static final String FIELD_TESTER_ID = "tester";
    public static final String FIELD_ACTUAL = "actual";
    public static final String FIELD_VALID = "valid";

    public static final int N_ID = 19;
    public static final int N_TEST_ID = 19;
    public static final int N_PARAMETER_ID = 10;
    public static final int N_TESTER_ID = 10;
    public static final int N_ACTUAL = 100;
    public static final int N_VALID = 1;

    private static final int _ord_ID = 1;
    private static final int _ord_TEST_ID = _ord_ID + 4;
    private static final int _ord_PARAMETER_ID = _ord_TEST_ID + 1;
    private static final int _ord_TESTER_ID = _ord_PARAMETER_ID + 1;
    private static final int _ord_ACTUAL = _ord_TESTER_ID + 1;
    private static final int _ord_VALID = _ord_ACTUAL + 1;

    @Id
    @NotNull
    long id;

    @NotNull
    String actual;

    @NotNull
    boolean valid;

    /**  */
    @NotNull
    FabStdTestParameter parameter;

    @NotNull
    int parameterId;

    /**  */
    FabStdTester tester;

    Integer testerId;

    /**  */
    @NotNull
    FabTrackTest test;

    @NotNull
    long testId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    @Ordinal(_ord_ACTUAL)
    @NotNull
    @Precision(value = N_ACTUAL)
    @TextInput(maxLength = N_ACTUAL)
    @Column(name = "actual", nullable = false, length = N_ACTUAL)
    public String getActual() {
        return actual;
    }

    public void setActual(@NotNull String value) {
        this.actual = value;
    }

    @Ordinal(_ord_VALID)
    @Precision(value = 1)
    @Column(name = "valid", nullable = false, precision = 1)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        this.valid = value;
    }

    /**
     *
     * @label parm
     * @constraint foreign key (parm) references violet.fabstdtest_parm (id)
     */
    @NotNull
    public FabStdTestParameter getParameter() {
        return parameter;
    }

    /**
     */
    public void setParameter(@NotNull FabStdTestParameter value) {
        this.parameter = value;
    }

    @Ordinal(_ord_PARAMETER_ID)
    @Precision(value = 10)
    @Column(name = "parm", nullable = false, precision = 10)
    public synchronized int getParameterId() {
        if (parameter != null) {
            return parameter.getId();
        }
        return parameterId;
    }

    public synchronized void setParameterId(int value) {
        this.parameterId = value;
    }

    /**
     *
     * @label tester
     * @constraint foreign key (tester) references violet.fabstdtester (id)
     */
    public FabStdTester getTester() {
        return tester;
    }

    /**
     */
    public void setTester(FabStdTester value) {
        this.tester = value;
    }

    @Ordinal(_ord_TESTER_ID)
    @Precision(value = N_TESTER_ID)
    @Column(name = "tester", precision = 10)
    public synchronized Integer getTesterId() {
        if (tester != null) {
            return tester.getId();
        }
        return testerId;
    }

    public synchronized void setTesterId(Integer value) {
        this.testerId = value;
    }

    /**
     *
     * @label test
     * @constraint foreign key (test) references violet.fabtrack_test (id)
     */
    @NotNull
    public FabTrackTest getTest() {
        return test;
    }

    /**
     */
    public void setTest(@NotNull FabTrackTest value) {
        this.test = value;
    }

    @Ordinal(_ord_TEST_ID)
    @Precision(value = 19)
    @Column(name = "test", nullable = false, precision = 19)
    public synchronized long getTestId() {
        if (test != null) {
            return test.getId();
        }
        return testId;
    }

    public synchronized void setTestId(long value) {
        this.testId = value;
    }

    public void initNotNulls() {
        this.actual = "";
    }

}
