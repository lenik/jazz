package net.bodz.lily.schema.meta;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.CoEntity;
import net.bodz.lily.entity.IdType;

@IdType(Integer.class)
public abstract class _TagDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "_tag";

    public static final String FIELD_ID = "id";
    public static final String FIELD_TAG_GROUP_ID = "tagv";

    public static final int N_ID = 10;
    public static final int N_TAG_GROUP_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_TAG_GROUP_ID = _ord_ID + 10;

    @Id
    @NotNull
    int id;

    /**  */
    @NotNull
    TagGroupDef tagGroup;

    @NotNull
    int tagGroupId;

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

    /**
     *
     * @constraint foreign key (tagv) references lily._tagv (id)
     */
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
