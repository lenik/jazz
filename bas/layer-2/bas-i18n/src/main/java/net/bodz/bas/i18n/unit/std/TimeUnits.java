package net.bodz.bas.i18n.unit.std;

import net.bodz.bas.i18n.unit.IMeasureUnitConstants;

public interface TimeUnits
        extends IMeasureUnitConstants {

    TimeUnit SECOND = new TimeUnit("second", "sec", 1.0);
    TimeUnit MILLISECOND = new TimeUnit("millisecond", "ms", 1e-3, SECOND);
    TimeUnit MICROSECOND = new TimeUnit("microsecond", "µs", 1e-6, SECOND);
    TimeUnit NANOSECOND = new TimeUnit("nanosecond", "ns", 1e-9, SECOND);
    TimeUnit PICOSECOND = new TimeUnit("picosecond", "ps", 1e-12, SECOND);

    TimeUnit MINUTE = new TimeUnit("minute", "min", 60, SECOND);
    TimeUnit HOUR = new TimeUnit("hour", "h", 60, MINUTE);
    TimeUnit DAY = new TimeUnit("day", "d", 24, HOUR);
    TimeUnit SHAKE = new TimeUnit("shake", "min", 10, NANOSECOND);

}
