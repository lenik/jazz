package net.bodz.lily.entity;

import org.joda.time.DateTime;

public interface IMomentInterval {

    DateTime getBeginTime();

    void setBeginTime(DateTime beginTime);

    DateTime getEndTime();

    void setEndTime(DateTime endTime);

}
