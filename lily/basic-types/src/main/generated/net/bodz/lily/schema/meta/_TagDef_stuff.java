package net.bodz.lily.schema.meta;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.meta.TypeParamType;
import net.bodz.lily.meta.TypeParameters;

@TypeParameters({ TypeParamType.THIS_TYPE })
@IdType(Integer.class)
public abstract class _TagDef_stuff<this_t extends _TagDef_stuff<this_t>>
        extends AbstractDefinition<this_t> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_tag";

    public static final String FIELD_TAG_GROUP_ID = "tagv";

    public static final int N_TAG_GROUP_ID = 10;

    private static final int _ord_TAG_GROUP_ID = 11;

    /**  */
    @NotNull
    TagGroupDef tagGroup;

    @NotNull
    int tagGroupId;

    /**
     *
     * @constraint foreign key (tagv) references lily._tagv (id)
     */
    @JoinColumn(name = "tagv")
    @ManyToOne
    @NotNull
    public TagGroupDef getTagGroup() {
        return tagGroup;
    }

    /**
     */
    public void setTagGroup(@NotNull TagGroupDef value) {
        this.tagGroup = value;
    }

    @Ordinal(_ord_TAG_GROUP_ID)
    @Precision(value = 10)
    @Column(name = "tagv", nullable = false, precision = 10)
    public synchronized int getTagGroupId() {
        if (tagGroup != null) {
            if (tagGroup.getId() == null)
                return 0;
            return tagGroup.getId();
        }
        return tagGroupId;
    }

    public synchronized void setTagGroupId(int value) {
        this.tagGroupId = value;
    }

    public void initNotNulls() {
    }

}
