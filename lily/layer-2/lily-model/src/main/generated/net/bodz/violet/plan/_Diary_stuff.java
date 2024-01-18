package net.bodz.violet.plan;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.t.base.CoMessage;

@IdType(Long.class)
public abstract class _Diary_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "diary";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_CATEGORY_ID = "cat";
    public static final String FIELD_PHASE_ID = "phase";
    public static final String FIELD_VALUE = "value";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_CATEGORY_ID = 10;
    public static final int N_PHASE_ID = 10;
    public static final int N_VALUE = 10;

    private static final int _ord_FORM_ARGUMENTS = 19;
    private static final int _ord_CATEGORY_ID = _ord_FORM_ARGUMENTS + 2;
    private static final int _ord_PHASE_ID = _ord_CATEGORY_ID + 1;
    private static final int _ord_VALUE = _ord_PHASE_ID + 1;

    String formArguments;

    @NotNull
    int value;

    /**  */
    DiaryPhase phase;

    Integer phaseId;

    /**  */
    @NotNull
    DiaryCategory category;

    @NotNull
    int categoryId;

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

    @Ordinal(_ord_VALUE)
    @Precision(value = 10)
    @Column(name = "value", nullable = false, precision = 10)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @label phase
     * @constraint foreign key (phase) references violet.diaryphase (id)
     */
    public DiaryPhase getPhase() {
        return phase;
    }

    /**
     */
    public void setPhase(DiaryPhase value) {
        this.phase = value;
    }

    @Ordinal(_ord_PHASE_ID)
    @Precision(value = N_PHASE_ID)
    @Column(name = "phase", precision = 10)
    public synchronized Integer getPhaseId() {
        if (phase != null) {
            return phase.getId();
        }
        return phaseId;
    }

    public synchronized void setPhaseId(Integer value) {
        this.phaseId = value;
    }

    /**
     *
     * @label cat
     * @constraint foreign key (cat) references violet.diarycat (id)
     */
    @NotNull
    public DiaryCategory getCategory() {
        return category;
    }

    /**
     */
    public void setCategory(@NotNull DiaryCategory value) {
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

    public void initNotNulls() {
    }

}
