package net.bodz.lily.vapp;

import javax.persistence.Column;
import javax.persistence.Id;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.model.base.CoEntity;

@IdType(Long.class)
public abstract class _VAppRequestApi_stuff
        extends CoEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vappreq_api";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_API_ID = "api";

    public static final int N_ID = 19;
    public static final int N_PARENT_ID = 10;
    public static final int N_API_ID = 10;

    private static final int _ord_ID = 1;
    private static final int _ord_PARENT_ID = _ord_ID + 7;
    private static final int _ord_API_ID = _ord_PARENT_ID + 1;

    @Id
    @NotNull
    long id;

    /**  */
    @NotNull
    VAppRequest parent;

    @NotNull
    int parentId;

    /**  */
    @NotNull
    ApiType api;

    @NotNull
    int apiId;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public void id(Long id) {
        setId(id);
    }

    @Id
    @Ordinal(_ord_ID)
    @Precision(value = 19)
    @Column(name = "id", nullable = false, precision = 19)
    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }

    /**
     *
     * @label parent
     * @constraint foreign key (parent) references lily.vappreq (id)
     */
    @NotNull
    public VAppRequest getParent() {
        return parent;
    }

    /**
     */
    public void setParent(@NotNull VAppRequest value) {
        this.parent = value;
    }

    @Ordinal(_ord_PARENT_ID)
    @Precision(value = 10)
    @Column(name = "parent", nullable = false, precision = 10)
    public synchronized int getParentId() {
        if (parent != null) {
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(int value) {
        this.parentId = value;
    }

    /**
     *
     * @label api
     * @constraint foreign key (api) references lily.apitype (id)
     */
    @NotNull
    public ApiType getApi() {
        return api;
    }

    /**
     */
    public void setApi(@NotNull ApiType value) {
        this.api = value;
    }

    @Ordinal(_ord_API_ID)
    @Precision(value = 10)
    @Column(name = "api", nullable = false, precision = 10)
    public synchronized int getApiId() {
        if (api != null) {
            return api.getId();
        }
        return apiId;
    }

    public synchronized void setApiId(int value) {
        this.apiId = value;
    }

    public void initNotNulls() {
    }

}
