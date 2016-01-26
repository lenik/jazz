package net.bodz.lily.model.base;

import org.joda.time.DateTime;

public interface IMomentInterval {

    DateTime getBeginDate();

    void setBeginDate(DateTime beginDate);

    DateTime getEndDate();

    void setEndDate(DateTime endDate);

}
