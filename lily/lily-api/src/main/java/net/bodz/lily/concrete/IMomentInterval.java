package net.bodz.lily.concrete;

import java.time.OffsetDateTime;

public interface IMomentInterval {

    OffsetDateTime getBeginTime();

    void setBeginTime(OffsetDateTime beginTime);

    OffsetDateTime getEndTime();

    void setEndTime(OffsetDateTime endTime);

}
