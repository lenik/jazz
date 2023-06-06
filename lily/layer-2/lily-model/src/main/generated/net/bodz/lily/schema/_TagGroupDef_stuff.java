package net.bodz.lily.schema;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _TagGroupDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_SCHEMA_ID = _ord_ID + 10;
    private static final int _ord_TOPIC = _ord_SCHEMA_ID + 1;
    private static final int _ord_REPLY = _ord_TOPIC + 1;

    @Id
    @NotNull
    int id;

    @NotNull
    boolean topic;

    @NotNull
    boolean reply;

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

    @Ordinal(_ord_TOPIC)
    @Precision(value = 1)
    @Column(name = "topic", nullable = false, precision = 1)
    public boolean isTopic() {
        return topic;
    }

    public void setTopic(boolean value) {
        this.topic = value;
    }

    @Ordinal(_ord_REPLY)
    @Precision(value = 1)
    @Column(name = "reply", nullable = false, precision = 1)
    public boolean isReply() {
        return reply;
    }

    public void setReply(boolean value) {
        this.reply = value;
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
