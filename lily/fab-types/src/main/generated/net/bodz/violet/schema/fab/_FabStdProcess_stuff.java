package net.bodz.violet.schema.fab;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoImaged;
import net.bodz.lily.entity.IdType;
import net.bodz.violet.schema.art.ArtifactModel;

@IdType(Integer.class)
public abstract class _FabStdProcess_stuff
        extends CoImaged<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "fabstdproc";

    public static final String FIELD_VALID = "valid";
    public static final String FIELD_VALID_SINCE = "validsince";
    public static final String FIELD_VALID_UNTIL = "validuntil";
    public static final String FIELD_OUTPUT_ID = "output";
    public static final String FIELD_FUNCTION_ID = "fn";
    public static final String FIELD_DURATION = "duration";
    public static final String FIELD_STRICT = "strict";
    public static final String FIELD_TEST_ID = "test";

    public static final int N_VALID = 1;
    public static final int N_VALID_SINCE = 35;
    public static final int N_VALID_UNTIL = 35;
    public static final int N_OUTPUT_ID = 10;
    public static final int N_FUNCTION_ID = 10;
    public static final int N_DURATION = 10;
    public static final int N_STRICT = 1;
    public static final int N_TEST_ID = 10;

    private static final int _ord_VALID = 15;
    private static final int _ord_VALID_SINCE = _ord_VALID + 1;
    private static final int _ord_VALID_UNTIL = _ord_VALID_SINCE + 1;
    private static final int _ord_OUTPUT_ID = _ord_VALID_UNTIL + 3;
    private static final int _ord_FUNCTION_ID = _ord_OUTPUT_ID + 1;
    private static final int _ord_DURATION = _ord_FUNCTION_ID + 1;
    private static final int _ord_STRICT = _ord_DURATION + 1;
    private static final int _ord_TEST_ID = _ord_STRICT + 1;

    @NotNull
    boolean valid;

    OffsetDateTime validSince;

    OffsetDateTime validUntil;

    @NotNull
    int duration;

    @NotNull
    boolean strict;

    /**  */
    @NotNull
    ArtifactModel output;

    @NotNull
    int outputId;

    /**  */
    FabStdTest test;

    Integer testId;

    /**  */
    FabFn function;

    Integer functionId;

    @Ordinal(_ord_VALID)
    @Precision(value = 1)
    @Column(name = "valid", nullable = false, precision = 1)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean value) {
        this.valid = value;
    }

    @Ordinal(_ord_VALID_SINCE)
    @Precision(value = 35, scale = 6)
    @Column(name = "validsince", precision = 35, scale = 6)
    public OffsetDateTime getValidSince() {
        return validSince;
    }

    public void setValidSince(OffsetDateTime value) {
        this.validSince = value;
    }

    @Ordinal(_ord_VALID_UNTIL)
    @Precision(value = 35, scale = 6)
    @Column(name = "validuntil", precision = 35, scale = 6)
    public OffsetDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(OffsetDateTime value) {
        this.validUntil = value;
    }

    @Ordinal(_ord_DURATION)
    @Precision(value = 10)
    @Column(name = "duration", nullable = false, precision = 10)
    public int getDuration() {
        return duration;
    }

    public void setDuration(int value) {
        this.duration = value;
    }

    @Ordinal(_ord_STRICT)
    @Precision(value = 1)
    @Column(name = "strict", nullable = false, precision = 1)
    public boolean isStrict() {
        return strict;
    }

    public void setStrict(boolean value) {
        this.strict = value;
    }

    /**
     *
     * @constraint foreign key (output) references violet.artmodel (id)
     */
    @JoinColumn(name = "output")
    @ManyToOne
    @NotNull
    public ArtifactModel getOutput() {
        return output;
    }

    /**
     */
    public void setOutput(@NotNull ArtifactModel value) {
        this.output = value;
    }

    @Ordinal(_ord_OUTPUT_ID)
    @Precision(value = 10)
    @Column(name = "output", nullable = false, precision = 10)
    public synchronized int getOutputId() {
        if (output != null) {
            if (output.getId() == null)
                return 0;
            return output.getId();
        }
        return outputId;
    }

    public synchronized void setOutputId(int value) {
        this.outputId = value;
    }

    /**
     *
     * @constraint foreign key (test) references violet.fabstdtest (id)
     */
    @JoinColumn(name = "test")
    @ManyToOne
    public FabStdTest getTest() {
        return test;
    }

    /**
     */
    public void setTest(FabStdTest value) {
        this.test = value;
    }

    @Ordinal(_ord_TEST_ID)
    @Precision(value = N_TEST_ID)
    @Column(name = "test", precision = 10)
    public synchronized Integer getTestId() {
        if (test != null) {
            return test.getId();
        }
        return testId;
    }

    public synchronized void setTestId(Integer value) {
        this.testId = value;
    }

    /**
     *
     * @constraint foreign key (fn) references violet.fabfn (id)
     */
    @JoinColumn(name = "fn")
    @ManyToOne
    public FabFn getFunction() {
        return function;
    }

    /**
     */
    public void setFunction(FabFn value) {
        this.function = value;
    }

    @Ordinal(_ord_FUNCTION_ID)
    @Precision(value = N_FUNCTION_ID)
    @Column(name = "fn", precision = 10)
    public synchronized Integer getFunctionId() {
        if (function != null) {
            return function.getId();
        }
        return functionId;
    }

    public synchronized void setFunctionId(Integer value) {
        this.functionId = value;
    }

    public void initNotNulls() {
    }

}
