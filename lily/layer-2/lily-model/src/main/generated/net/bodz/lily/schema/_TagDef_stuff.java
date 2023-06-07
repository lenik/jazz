package net.bodz.lily.schema;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Integer.class)
public abstract class _TagDef_stuff
        extends CoEntity<Integer> {

    private static final long serialVersionUID = 1L;

    private static final int _ord_ID = 1;
    private static final int _ord_TAG_GROUP_ID = _ord_ID + 9;

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
     * @label tagv
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
