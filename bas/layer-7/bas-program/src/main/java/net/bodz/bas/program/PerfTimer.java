package net.bodz.bas.program;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class PerfTimer {

    private long start;
    private long last;

    public PerfTimer() {
        this(System.currentTimeMillis());
    }

    public PerfTimer(long start) {
        this.start = start;
        this.last = start;
    }

    public long getStart() {
        return start;
    }

    public long getLast() {
        return last;
    }

    public synchronized void restart() {
        start = System.currentTimeMillis();
    }

    public synchronized void reset() {
        last = start;
    }

    public synchronized long measure() {
        long now = System.currentTimeMillis();
        long duration = now - last;
        last = now;
        return duration;
    }

    public String measure(DateFormat dateFormat) {
        long duration = measure();
        String str = dateFormat.format(duration);
        return str;
    }

    private static PerfTimer instance = new PerfTimer();
    private static Map<String, PerfTimer> registry = new HashMap<String, PerfTimer>();

    public static PerfTimer getInstance() {
        return instance;
    }

    public static PerfTimer getOrCreate(String key) {
        PerfTimer timer = registry.get(key);
        if (timer == null)
            synchronized (registry) {
                if (timer == null)
                    timer = new PerfTimer();
            }
        return timer;
    }

}
