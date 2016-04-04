package net.bodz.lily.model.mx.base;

import java.io.Serializable;

import org.joda.time.DateTime;

public abstract class UserClickRecord
        implements Serializable {

    private static final long serialVersionUID = 1L;

    DateTime time;

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public void setTime(long time) {
        this.time = new DateTime(time);
    }

}
