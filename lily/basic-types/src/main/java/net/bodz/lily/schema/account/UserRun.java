package net.bodz.lily.schema.account;

import java.time.OffsetDateTime;

import javax.persistence.Table;

import net.bodz.bas.meta.decl.Redundant;
import net.bodz.lily.entity.PrimaryKeyColumns;
import net.bodz.lily.entity.PrimaryKeyProperties;

@PrimaryKeyColumns("user")
@PrimaryKeyProperties("userId")
@Table(schema = UserRun.SCHEMA_NAME, name = UserRun.TABLE_NAME)
public class UserRun
        extends _UserRun_stuff {

    private static final long serialVersionUID = 1L;

    static final int ONLINE = 1;
    static final int OFFLINE = 2;

    @Redundant
    public String getStateText() {
        return super.getLabel();
    }

    @Redundant
    public OffsetDateTime getActiveTime() {
        return super.getLastModified();
    }

}
