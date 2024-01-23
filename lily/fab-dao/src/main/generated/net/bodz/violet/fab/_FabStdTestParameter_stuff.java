package net.bodz.violet.fab;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _FabStdTestParameter_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabstdtest_parm";

    public static final String FIELD_ID = "id";
    public static final String FIELD_TEST_ID = "test";
    public static final String FIELD_REQUIRED = "required";
    public static final String FIELD_EXPECTED = "expected";

    public static final int N_ID = 10;
    public static final int N_TEST_ID = 10;
    public static final int N_REQUIRED = 1;
    public static final int N_EXPECTED = 100;

    private static final int _ord_ID = 1;
    private static final int _ord_TEST_ID = _ord_ID + 4;
    private static final int _ord_REQUIRED = _ord_TEST_ID + 1;
    private static final int _ord_EXPECTED = _ord_REQUIRED + 2;

    @Id
    @NotNull
    int id;

    @NotNull
    boolean required;

    String expected;

    /**  */
    @NotNull
    FabStdTest test;

    @NotNull
    int testId;

    @Override
    public Integer id() {
        return getId();
    }

    @Override
    public void id(Integer id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 10)
    @Column(name = "id", nullable = false, precision = 10)
    public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    @Ordinal(_ord_REQUIRED)
    @Precision(value = 1)
    @Column(name = "required", nullable = false, precision = 1)
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean value) {
        this.required = value;
    }

    @Ordinal(_ord_EXPECTED)
    @Precision(value = N_EXPECTED)
    @TextInput(maxLength = N_EXPECTED)
    @Column(name = "expected", length = N_EXPECTED)
    public String getExpected() {
        return expected;
    }

    public void setExpected(String value) {
        this.expected = value;
    }

    /**
     *
     * @label test
     * @constraint foreign key (test) references violet.fabstdtest (id)
     */
    @NotNull
    public FabStdTest getTest() {
        return test;
    }

    /**
     */
    public void setTest(@NotNull FabStdTest value) {
        this.test = value;
    }

    @Ordinal(_ord_TEST_ID)
    @Precision(value = 10)
    @Column(name = "test", nullable = false, precision = 10)
    public synchronized int getTestId() {
        if (test != null) {
            return test.getId();
        }
        return testId;
    }

    public synchronized void setTestId(int value) {
        this.testId = value;
    }

    public void initNotNulls() {
    }

}
