package net.bodz.bas.c.java.util;

import java.util.TimeZone;

public class TimeZones
        implements ITimeZoneConsts {

}

interface ITimeZoneConsts {

    TimeZone TZ_0 = TimeZone.getTimeZone("GMT+0:00");
    TimeZone TZ_SHANGHAI = TimeZone.getTimeZone("GMT+8:00");

}