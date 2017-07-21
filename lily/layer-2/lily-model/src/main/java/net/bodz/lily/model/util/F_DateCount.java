package net.bodz.lily.model.util;

import java.sql.Date;

import net.bodz.bas.c.object.Nullables;

public class F_DateCount
        implements Comparable<F_DateCount> {

    Date date;
    long count;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public int compareTo(F_DateCount o) {
        if (o == null)
            return 1;

        if (o == this)
            return 0;

        int cmp = Nullables.compare(date, o.date);
        if (cmp != 0)
            return cmp;

        return equals(o) ? 0 : -1;
    }

}
