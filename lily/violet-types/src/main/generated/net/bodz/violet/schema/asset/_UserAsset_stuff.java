package net.bodz.violet.schema.asset;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Ordinal;
import net.bodz.bas.repr.form.validate.Precision;
import net.bodz.lily.entity.IdType;
import net.bodz.lily.schema.account.User;

@IdType(Long.class)
public abstract class _UserAsset_stuff
        extends AbstractAsset {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "asset_user";

    public static final String FIELD_OWNER_ID = "owner";
    public static final String FIELD_BATCH = "batch";

    public static final int N_OWNER_ID = 10;
    public static final int N_BATCH = 2147483647;

    private static final int _ord_OWNER_ID = 15;
    private static final int _ord_BATCH = _ord_OWNER_ID + 3;

    JsonVariant batch;

    /** (User Account) */
    @NotNull
    User owner;

    @NotNull
    int ownerId;

    @Ordinal(_ord_BATCH)
    @Precision(value = 2147483647)
    @Column(name = "batch", precision = 2147483647)
    public JsonVariant getBatch() {
        return batch;
    }

    public void setBatch(JsonVariant value) {
        this.batch = value;
    }

    /**
     * {inheritDoc User}
     * User Account
     *
     * @constraint foreign key (owner) references lily.user (id)
     */
    @JoinColumn(name = "owner")
    @ManyToOne
    @NotNull
    public User getOwner() {
        return owner;
    }

    /**
     * User Account
     */
    public void setOwner(@NotNull User value) {
        this.owner = value;
    }

    @Ordinal(_ord_OWNER_ID)
    @Precision(value = 10)
    @Column(name = "owner", nullable = false, precision = 10)
    public synchronized int getOwnerId() {
        if (owner != null) {
            if (owner.getId() == null)
                return 0;
            return owner.getId();
        }
        return ownerId;
    }

    public synchronized void setOwnerId(int value) {
        this.ownerId = value;
    }

    public void initNotNulls() {
    }

}
