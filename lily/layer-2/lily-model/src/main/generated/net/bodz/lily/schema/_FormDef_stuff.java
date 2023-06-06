package net.bodz.lily.schema;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _FormDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final int N_SUBJECT = 200;
    public static final int N_RAW_TEXT = 2147483647;

    private static final int _ord_ID = 1;
    private static final int _ord_SCHEMA_ID = _ord_ID + 10;
    private static final int _ord_SUBJECT = _ord_SCHEMA_ID + 1;
    private static final int _ord_RAW_TEXT = _ord_SUBJECT + 1;

    @Id
    @NotNull
    int id;

    String subject;

    String rawText;

    /**  */
    @NotNull
    SchemaDef schema;

    @NotNull
    int schemaId;

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

    /**
     *
     * @label schema
     * @constraint foreign key (schema) references lily._schema (id)
     */
    @NotNull
    public SchemaDef getSchema() {
        return schema;
    }

    /**
     */
    public void setSchema(@NotNull SchemaDef value) {
        this.schema = value;
    }

    @Ordinal(_ord_SCHEMA_ID)
    @Precision(value = 10)
    @Column(name = "schema", nullable = false, precision = 10)
    public synchronized int getSchemaId() {
        if (schema != null) {
            if (schema.getId() == null)
                return 0;
            return schema.getId();
        }
        return schemaId;
    }

    public synchronized void setSchemaId(int value) {
        this.schemaId = value;
    }

    public void initNotNulls() {
    }

}
