package net.bodz.bas.c.java.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.bodz.bas.typer.std.AbstractCommonTypers;

public abstract class TemporalCommonTypers<T extends TemporalAccessor>
        extends AbstractCommonTypers<T> {

    static List<String> zoneIds = new ArrayList<>(ZoneId.getAvailableZoneIds());

    public TemporalCommonTypers(Class<T> type) {
        super(type);
    }

    static ZoneId getRandomZoneId(Random prng) {
        int index = prng.nextInt(zoneIds.size());
        String id = zoneIds.get(index);
        return ZoneId.of(id);
    }

    static ZoneOffset getRandomZoneOffset(Random prng) {
        int hours = prng.nextInt(32) - 16;
        int minutes = prng.nextInt(60);
        if (hours < 0)
            minutes = -minutes;
        return ZoneOffset.ofHoursMinutes(hours, minutes);
    }

    static Instant fromTheYear(long delta) {
        LocalDate startOfTheYear = LocalDate.now().withMonth(1).withDayOfMonth(1);
        long milli = startOfTheYear.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
        return Instant.ofEpochMilli(milli + delta);
    }

}
