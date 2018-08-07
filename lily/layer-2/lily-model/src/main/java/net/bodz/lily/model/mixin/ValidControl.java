package net.bodz.lily.model.mixin;

import org.joda.time.DateTime;

public class ValidControl {

    boolean valid;
    DateTime since;
    DateTime until;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public DateTime getSince() {
        return since;
    }

    public void setSince(DateTime since) {
        this.since = since;
    }

    public DateTime getUntil() {
        return until;
    }

    public void setUntil(DateTime until) {
        this.until = until;
    }

}
