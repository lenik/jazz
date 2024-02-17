package net.bodz.lily.schema.meta;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.meta.TextInput;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _TagGroupDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_tagv";

    public static final String FIELD_ID = "id";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_SCHEMA_ID = "schema";
    public static final String FIELD_FOR_TOPIC = "topic";
    public static final String FIELD_FOR_REPLY = "reply";

    public static final int N_ID = 10;
    public static final int N_CODE = 30;
    public static final int N_SCHEMA_ID = 10;
    public static final int N_FOR_TOPIC = 1;
    public static final int N_FOR_REPLY = 1;

    private static final int _ord_ID = 1;
    private static final int _ord_CODE = _ord_ID + 1;
    private static final int _ord_SCHEMA_ID = _ord_CODE + 9;
    private static final int _ord_FOR_TOPIC = _ord_SCHEMA_ID + 1;
    private static final int _ord_FOR_REPLY = _ord_FOR_TOPIC + 1;

    @Id
    @NotNull
    int id;

    String code;

    @NotNull
    boolean forTopic;

    @NotNull
    boolean forReply;

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

    @Ordinal(_ord_FOR_TOPIC)
    @Precision(value = 1)
    @Column(name = "topic", nullable = false, precision = 1)
    public boolean isForTopic() {
        return forTopic;
    }

    public void setForTopic(boolean value) {
        this.forTopic = value;
    }

    @Ordinal(_ord_FOR_REPLY)
    @Precision(value = 1)
    @Column(name = "reply", nullable = false, precision = 1)
    public boolean isForReply() {
        return forReply;
    }

    public void setForReply(boolean value) {
        this.forReply = value;
    }

    /**
     *
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
