package net.bodz.lily.schema.vapp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.meta.NotNull;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.concrete.IdEntity;
import net.bodz.lily.entity.IdType;

@IdType(Long.class)
public abstract class _VAppRequestApi_stuff
        extends IdEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "lily";
    public static final String TABLE_NAME = "vappreq_api";

    public static final String FIELD_PARENT_ID = "parent";
    public static final String FIELD_API_ID = "api";

    public static final int N_PARENT_ID = 10;
    public static final int N_API_ID = 10;

    private static final int _ord_PARENT_ID = 8;
    private static final int _ord_API_ID = _ord_PARENT_ID + 1;

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

    /**
     *
     * @constraint foreign key (parent) references lily.vappreq (id)
     */
    @JoinColumn(name = "parent")
    @ManyToOne
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
            if (parent.getId() == null)
                return 0;
            return parent.getId();
        }
        return parentId;
    }

    public synchronized void setParentId(int value) {
        this.parentId = value;
    }

    /**
     *
     * @constraint foreign key (api) references lily.apitype (id)
     */
    @JoinColumn(name = "api")
    @ManyToOne
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
            if (api.getId() == null)
                return 0;
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
