package net.bodz.lily.concrete;

import java.time.ZonedDateTime;

public interface IMomentInterval {

    ZonedDateTime getBeginTime();

    void setBeginTime(ZonedDateTime beginTime);

    ZonedDateTime getEndTime();

    void setEndTime(ZonedDateTime endTime);

}
