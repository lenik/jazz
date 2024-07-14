package net.bodz.violet.schema.issue;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _Issue_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "issue";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PHASE_ID = "phase";
    public static final String FIELD_READ_COUNT = "nread";
    public static final String FIELD_VOTE_COUNT = "nvote";
    public static final String FIELD_NLIKE = "nlike";
    public static final String FIELD_VALUE = "value";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_READ_COUNT = 10;
    public static final int N_VOTE_COUNT = 10;
    public static final int N_NLIKE = 10;
    public static final int N_VALUE = 17;

    private static final int _ord_FORM_ARGUMENTS = 16;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 4;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_READ_COUNT = _ord_PHASE_ID + 1;
    private static final int _ord_VOTE_COUNT = _ord_READ_COUNT + 1;
    private static final int _ord_NLIKE = _ord_VOTE_COUNT + 1;
    private static final int _ord_VALUE = _ord_NLIKE + 1;

    String formArguments;

    @NotNull
    int readCount;

    @NotNull
    int voteCount;

    @NotNull
    int nlike;

    @NotNull
    double value;

    /**  */
    @NotNull
    IssueCategory category;

    @NotNull
    int categoryId;

    /**  */
    @NotNull
    IssuePhase phase;

    @NotNull
    int phaseId;

    @Ordinal(_ord_FORM_ARGUMENTS)
    @Precision(value = N_FORM_ARGUMENTS)
    @TextInput(maxLength = N_FORM_ARGUMENTS)
    @Column(name = "formargs", length = N_FORM_ARGUMENTS)
    public String getFormArguments() {
        return formArguments;
    }

    public void setFormArguments(String value) {
        this.formArguments = value;
    }

    @Ordinal(_ord_READ_COUNT)
    @Precision(value = 10)
    @Column(name = "nread", nullable = false, precision = 10)
    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int value) {
        this.readCount = value;
    }

    @Ordinal(_ord_VOTE_COUNT)
    @Precision(value = 10)
    @Column(name = "nvote", nullable = false, precision = 10)
    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int value) {
        this.voteCount = value;
    }

    @Ordinal(_ord_NLIKE)
    @Precision(value = 10)
    @Column(name = "nlike", nullable = false, precision = 10)
    public int getNlike() {
        return nlike;
    }

    public void setNlike(int value) {
        this.nlike = value;
    }

    @Ordinal(_ord_VALUE)
    @Precision(value = 17, scale = 17)
    @Column(name = "value", nullable = false, precision = 17, scale = 17)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     *
     * @constraint foreign key (cat) references violet.issuecat (id)
     */
    @JoinColumn(name = "cat")
    @ManyToOne
    @NotNull
    public IssueCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull IssueCategory value) {
        this.category = value;
    }

    @Ordinal(_ord_CATEGORY_ID)
    @Precision(value = 10)
    @Column(name = "cat", nullable = false, precision = 10)
    public synchronized int getCategoryId() {
        if (category != null) {
            if (category.getId() == null)
                return 0;
            return category.getId();
        }
        return categoryId;
    }

    public synchronized void setCategoryId(int value) {
        this.categoryId = value;
    }

    /**
     *
     * @constraint foreign key (phase) references violet.issuephase (id)
     */
    @JoinColumn(name = "phase")
    @ManyToOne
    @NotNull
    public IssuePhase getPhase() {
        return phase;
    }

    /**
     */
    public void setPhase(@NotNull IssuePhase value) {
        this.phase = value;
    }

    @Ordinal(_ord_PHASE_ID)
    @Precision(value = 10)
    @Column(name = "phase", nullable = false, precision = 10)
    public synchronized int getPhaseId() {
        if (phase != null) {
            if (phase.getId() == null)
                return 0;
            return phase.getId();
        }
        return phaseId;
    }

    public synchronized void setPhaseId(int value) {
        this.phaseId = value;
    }

    public void initNotNulls() {
    }

}
