package net.bodz.mda.xjdoc.contrib.maven;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import net.bodz.bas.c.java.time.DateTimes;

public class DurationTimer {

    LocalDateTime start;
    LocalDateTime end;

    public DurationTimer() {
    }

    public DurationTimer(boolean start) {
        if (start)
            start();
    }

    public DurationTimer start() {
        start = LocalDateTime.now();
        return this;
    }

    public DurationTimer end() {
        end = LocalDateTime.now();
        return this;
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }

    public LocalDateTime getDurationTime() {
        Duration duration = getDuration();
        LocalDateTime durationTime = LocalDateTime.ofEpochSecond(duration.getSeconds(), duration.getNano(),
                ZoneOffset.UTC);
        return durationTime;
    }

    public long getDurationMillis() {
        Duration duration = getDuration();
        long seconds = duration.getSeconds();
        int nano = duration.getNano();
        int milli = nano / 1_000_000;
        return seconds * 1_000 + milli;
    }

    public long getDurationMicros() {
        Duration duration = getDuration();
        long seconds = duration.getSeconds();
        int nano = duration.getNano();
        int micro = nano / 1_000;
        return seconds * 1_000_000 + micro;
    }

    @Override
    public String toString() {
        LocalDateTime durationTime = getDurationTime();
        String s = DateTimes.ISO_LOCAL_TIME.format(durationTime);
        return s;
    }

}
