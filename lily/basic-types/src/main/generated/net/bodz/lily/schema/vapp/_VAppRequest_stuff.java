package net.bodz.lily.schema.vapp;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.meta.FormDef;

@IdType(Integer.class)
public abstract class _VAppRequest_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vappreq";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_BEGIN_TIME = "t0";
    public static final String FIELD_END_TIME = "t1";
    public static final String FIELD_YEAR = "year";
    public static final String FIELD_SUBJECT = "subject";
    public static final String FIELD_OP_ID = "op";
    public static final String FIELD_RAW_TEXT = "text";
    public static final String FIELD_FORM_ID = "form";
    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_DUMMY = "dummy";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_BEGIN_TIME = 35;
    public static final int N_END_TIME = 35;
    public static final int N_YEAR = 10;
    public static final int N_SUBJECT = 200;
    public static final int N_OP_ID = 10;
    public static final int N_RAW_TEXT = 2147483647;
    public static final int N_FORM_ID = 10;
    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_DUMMY = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = 12;
    private static final int _ord_BEGIN_TIME = _ord_CODE + 4;
    private static final int _ord_END_TIME = _ord_BEGIN_TIME + 1;
    private static final int _ord_YEAR = _ord_END_TIME + 1;
    private static final int _ord_SUBJECT = _ord_YEAR + 1;
    private static final int _ord_OP_ID = _ord_SUBJECT + 1;
    private static final int _ord_RAW_TEXT = _ord_OP_ID + 1;
    private static final int _ord_FORM_ID = _ord_RAW_TEXT + 1;
    private static final int _ord_FORM_ARGUMENTS = _ord_FORM_ID + 1;
    private static final int _ord_DUMMY = _ord_FORM_ARGUMENTS + 1;

    @Id
    @NotNull
    int id;

    String code;

    ZonedDateTime beginTime;

    ZonedDateTime endTime;

    @NotNull
    int year;

    @NotNull
    String subject;

    String rawText;

    String formArguments;

    Integer dummy;

    /** (User Account) */
    User op;

    Integer opId;

    /**  */
    FormDef form;

    Integer formId;

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

    @Ordinal(_ord_CODE)
    @Precision(value = N_CODE)
    @TextInput(maxLength = N_CODE)
    @Column(name = "code", length = N_CODE)
    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        this.code = value;
    }

    @Ordinal(_ord_BEGIN_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t0", precision = 35, scale = 6)
    public ZonedDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(ZonedDateTime value) {
        this.beginTime = value;
    }

    @Ordinal(_ord_END_TIME)
    @Precision(value = 35, scale = 6)
    @Column(name = "t1", precision = 35, scale = 6)
    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime value) {
        this.endTime = value;
    }

    @Ordinal(_ord_YEAR)
    @Precision(value = 10)
    @Column(name = "year", nullable = false, precision = 10)
    public int getYear() {
        return year;
    }

    public void setYear(int value) {
        this.year = value;
    }

    @Ordinal(_ord_SUBJECT)
    @NotNull
    @Precision(value = N_SUBJECT)
    @TextInput(maxLength = N_SUBJECT)
    @Column(name = "subject", nullable = false, length = N_SUBJECT)
    public String getSubject() {
        return subject;
    }

    public void setSubject(@NotNull String value) {
        this.subject = value;
    }

    @Ordinal(_ord_RAW_TEXT)
    @Precision(value = N_RAW_TEXT)
    @TextInput(maxLength = N_RAW_TEXT)
    @Column(name = "text", length = N_RAW_TEXT)
    public String getRawText() {
        return rawText;
    }

    public void setRawText(String value) {
        this.rawText = value;
    }

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

    @Ordinal(_ord_DUMMY)
    @Precision(value = N_DUMMY)
    @Column(name = "dummy", precision = 10)
    public Integer getDummy() {
        return dummy;
    }

    public void setDummy(Integer value) {
        this.dummy = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @constraint foreign key (op) references lily.user (id)
     */
    public User getOp() {
        return op;
    }

    /**
     * User Account
     */
    public void setOp(User value) {
        this.op = value;
    }

    @Ordinal(_ord_OP_ID)
    @Precision(value = N_OP_ID)
    @Column(name = "op", precision = 10)
    public synchronized Integer getOpId() {
        if (op != null) {
            return op.getId();
        }
        return opId;
    }

    public synchronized void setOpId(Integer value) {
        this.opId = value;
    }

    /**
     *
     * @constraint foreign key (form) references lily._form (id)
     */
    public FormDef getForm() {
        return form;
    }

    /**
     */
    public void setForm(FormDef value) {
        this.form = value;
    }

    @Ordinal(_ord_FORM_ID)
    @Precision(value = N_FORM_ID)
    @Column(name = "form", precision = 10)
    public synchronized Integer getFormId() {
        if (form != null) {
            return form.getId();
        }
        return formId;
    }

    public synchronized void setFormId(Integer value) {
        this.formId = value;
    }

    public void initNotNulls() {
        this.subject = "";
    }

}
