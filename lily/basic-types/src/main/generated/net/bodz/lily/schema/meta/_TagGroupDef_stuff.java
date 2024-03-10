package net.bodz.lily.schema.meta;

import javax.persistence.Column;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _TagGroupDef_stuff<this_t extends _TagGroupDef_stuff<this_t>>
        extends AbstractDefinition<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_tagv";

    public static final String FIELD_FOR_TOPIC = "topic";
    public static final String FIELD_FOR_REPLY = "reply";

    public static final int N_FOR_TOPIC = 1;
    public static final int N_FOR_REPLY = 1;

    private static final int _ord_FOR_TOPIC = 13;
    private static final int _ord_FOR_REPLY = _ord_FOR_TOPIC + 1;

    @NotNull
    boolean forTopic;

    @NotNull
    boolean forReply;

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

    public void initNotNulls() {
    }

}
