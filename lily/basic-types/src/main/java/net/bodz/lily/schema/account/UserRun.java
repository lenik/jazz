package net.bodz.lily.schema.account;

import java.time.ZonedDateTime;

import javax.persistence.Table;

import net.bodz.bas.meta.decl.Redundant;

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
    public ZonedDateTime getActiveTime() {
        return super.getLastModifiedDate();
    }

}
