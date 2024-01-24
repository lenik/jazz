package net.bodz.violet.schema.plan;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _DiaryReview_stuff
        extends CoMessage<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "diaryrev";

    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_DIARY_ID = "diary";
    public static final String FIELD_PARENT_ID = "parent";

    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_DIARY_ID = 19;
    public static final int N_PARENT_ID = 19;

    private static final int _ord_FORM_ARGUMENTS = 15;
    private static final int _ord_DIARY_ID = _ord_FORM_ARGUMENTS + 1;
    private static final int _ord_PARENT_ID = _ord_DIARY_ID + 1;

    String formArguments;

    /**  */
    @NotNull
    Diary diary;

    @NotNull
    long diaryId;

    /**  */
    DiaryReview parent;

    Long parentId;

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

    /**
     *
     * @label diary
     * @constraint foreign key (diary) references violet.diary (id)
     */
    @NotNull
    public Diary getDiary() {
        return diary;
    }

    /**
     */
    public void setDiary(@NotNull Diary value) {
        this.diary = value;
    }

    @Ordinal(_ord_DIARY_ID)
    @Precision(value = 19)
    @Column(name = "diary", nullable = false, precision = 19)
    public synchronized long getDiaryId() {
        if (diary != null) {
            if (diary.getId() == null)
                return 0L;
            return diary.getId();
        }
        return diaryId;
    }

    public synchronized void setDiaryId(long value) {
        this.diaryId = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references violet.diaryrev (id)
     */
    public DiaryReview getParent() {
        return parent;
    }

    /**
     */
    public void setParent(DiaryReview value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = N_PARENT_ID)
    @Column(name = "parent", precision = 19)
    public synchronized Long getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(Long value) {
        this.parentId = value;
    }

    public void initNotNulls() {
    }

}
