package net.bodz.lily.concrete.util;

import java.time.OffsetDateTime;

public class MutableValidControl
        extends MixinStruct
        implements
            IValidControl {

    boolean valid;
    OffsetDateTime since;
    OffsetDateTime until;

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public OffsetDateTime getValidSince() {
        return since;
    }

    @Override
    public void setValidSince(OffsetDateTime since) {
        this.since = since;
    }

    @Override
    public OffsetDateTime getValidUntil() {
        return until;
    }

    @Override
    public void setValidUntil(OffsetDateTime until) {
        this.until = until;
    }

}
