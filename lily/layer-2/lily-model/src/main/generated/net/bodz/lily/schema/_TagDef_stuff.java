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
    private static final int _ord_TAGV_ID = _ord_ID + 9;

    @Id
    @NotNull
    int id;

    /**  */
    @NotNull
    TagGroupDef tagv;

    @NotNull
    int tagvId;

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
    public TagGroupDef getTagv() {
        return tagv;
    }

    /**
     */
    public void setTagv(@NotNull TagGroupDef value) {
        this.tagv = value;
    }

    @Ordinal(_ord_TAGV_ID)
    @Precision(value = 10)
    @Column(name = "tagv", nullable = false, precision = 10)
    public synchronized int getTagvId() {
        if (tagv != null) {
            if (tagv.getId() == null)
                return 0;
            return tagv.getId();
        }
        return tagvId;
    }

    public synchronized void setTagvId(int value) {
        this.tagvId = value;
    }

    public void initNotNulls() {
    }

}
