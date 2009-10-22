package net.bodz.six.misc;

import java.util.Map;

import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;

public class BgScheduler {

    TextMap<BgWorker> registry = new HashTextMap<BgWorker>();

    public static class Preference {
        boolean enabled;
        int     importance;
        int     interval;  // ms
    }

    // TODO - preference of each BgWorker,
    // so when resource is limited, high preference would be activated first.
    Map<String, Preference> preferences;

    public void start() {
        // TODO - capture Idle events.
    }

    public void stop() {

    }

    public Preference register(String name, BgWorker worker) {
        if (registry.containsKey(name))
            throw new RuntimeException("Job " + name
                    + " has already be registered.");
        registry.put(name, worker);
        Preference preference = new Preference();
        preferences.put(name, preference);
        return preference;
    }

    public void unregister(String name) {
        registry.remove(name);
        preferences.remove(name);
    }

    public void unregisterAll() {
        registry.clear();
        preferences.clear();
    }
}
