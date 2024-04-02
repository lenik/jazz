package net.bodz.violet.schema.fab;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _FabStdTestParameter_stuff
        extends IdEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabstdtest_parm";

    public static final String FIELD_TEST_ID = "test";
    public static final String FIELD_REQUIRED = "required";
    public static final String FIELD_PROPERTIES = "props";
    public static final String FIELD_EXPECTED = "expected";

    public static final int N_TEST_ID = 10;
    public static final int N_REQUIRED = 1;
    public static final int N_PROPERTIES = 2147483647;
    public static final int N_EXPECTED = 100;

    private static final int _ord_TEST_ID = 5;
    private static final int _ord_REQUIRED = _ord_TEST_ID + 1;
    private static final int _ord_PROPERTIES = _ord_REQUIRED + 1;
    private static final int _ord_EXPECTED = _ord_PROPERTIES + 1;

    @NotNull
    boolean required;

    JsonVariant properties;

    String expected;

    /**  */
    @NotNull
    FabStdTest test;

    @NotNull
    int testId;

    @Ordinal(_ord_REQUIRED)
    @Precision(value = 1)
    @Column(name = "required", nullable = false, precision = 1)
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean value) {
        this.required = value;
    }

    @Ordinal(_ord_PROPERTIES)
    @Precision(value = 2147483647)
    @Column(name = "props", precision = 2147483647)
    public JsonVariant getProperties() {
        return properties;
    }

    public void setProperties(JsonVariant value) {
        this.properties = value;
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
     * @constraint foreign key (test) references violet.fabstdtest (id)
     */
    @JoinColumn(name = "test")
    @ManyToOne
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
            if (test.getId() == null)
                return 0;
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
