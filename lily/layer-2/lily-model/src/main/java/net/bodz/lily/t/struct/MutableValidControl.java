package net.bodz.lily.t.struct;

import org.joda.time.DateTime;

public class MutableValidControl
        extends MixinStruct
        implements
            IValidControl {

    boolean valid;
    DateTime since;
    DateTime until;

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public DateTime getValidSince() {
        return since;
    }

    @Override
    public void setValidSince(DateTime since) {
        this.since = since;
    }

    @Override
    public DateTime getValidUntil() {
        return until;
    }

    @Override
    public void setValidUntil(DateTime until) {
        this.until = until;
    }

}
