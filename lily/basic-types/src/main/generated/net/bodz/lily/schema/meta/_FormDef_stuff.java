package net.bodz.lily.schema.meta;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.meta.decl.TypeParamType;
import net.bodz.bas.meta.decl.TypeParameters;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _FormDef_stuff<this_t extends _FormDef_stuff<this_t>>
        extends AbstractDefinition<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_form";

    public static final String FIELD_SUBJECT = "subject";
    public static final String FIELD_RAW_TEXT = "text";

    public static final int N_SUBJECT = 200;
    public static final int N_RAW_TEXT = 2147483647;

    private static final int _ord_SUBJECT = 13;
    private static final int _ord_RAW_TEXT = _ord_SUBJECT + 1;

    String subject;

    String rawText;

    @Ordinal(_ord_SUBJECT)
    @Precision(value = N_SUBJECT)
    @TextInput(maxLength = N_SUBJECT)
    @Column(name = "subject", length = N_SUBJECT)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String value) {
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

    public void initNotNulls() {
    }

}
