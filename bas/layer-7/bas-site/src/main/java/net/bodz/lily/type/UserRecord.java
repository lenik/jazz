package net.bodz.lily.type;

import java.io.Serializable;
import java.util.UUID;

/**
 * 
 */
public class UserRecord
        implements Serializable {

    private static final long serialVersionUID = 1L;

    // OPT Lazy-init.
    private UUID uuid = UUID.randomUUID();

    public UserRecord() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        if (uuid == null)
            throw new NullPointerException("uuid");
        this.uuid = uuid;
    }

}
