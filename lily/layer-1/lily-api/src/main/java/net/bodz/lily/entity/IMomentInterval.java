package net.bodz.lily.entity;

import java.time.ZonedDateTime;

public interface IMomentInterval {

    ZonedDateTime getBeginTime();

    void setBeginTime(ZonedDateTime beginTime);

    ZonedDateTime getEndTime();

    void setEndTime(ZonedDateTime endTime);

}
