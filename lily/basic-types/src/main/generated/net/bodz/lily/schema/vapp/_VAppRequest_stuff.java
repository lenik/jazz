package net.bodz.lily.schema.vapp;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoMessage;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _VAppRequest_stuff
        extends CoMessage<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vappreq";

    public static final String FIELD_CODE = "code";
    public static final String FIELD_FORM_ARGUMENTS = "formargs";
    public static final String FIELD_DUMMY = "dummy";

    public static final int N_CODE = 30;
    public static final int N_FORM_ARGUMENTS = 2147483647;
    public static final int N_DUMMY = 10;

    private static final int _ord_CODE = 12;
    private static final int _ord_FORM_ARGUMENTS = 23;
    private static final int _ord_DUMMY = _ord_FORM_ARGUMENTS + 1;

    String code;

    String formArguments;

    Integer dummy;

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

    public void initNotNulls() {
    }

}
