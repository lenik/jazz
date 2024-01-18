package net.bodz.lily.t.struct;

import java.time.ZonedDateTime;

public class MutableValidControl
        extends MixinStruct
        implements
            IValidControl {

    boolean valid;
    ZonedDateTime since;
    ZonedDateTime until;

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public ZonedDateTime getValidSince() {
        return since;
    }

    @Override
    public void setValidSince(ZonedDateTime since) {
        this.since = since;
    }

    @Override
    public ZonedDateTime getValidUntil() {
        return until;
    }

    @Override
    public void setValidUntil(ZonedDateTime until) {
        this.until = until;
    }

}
