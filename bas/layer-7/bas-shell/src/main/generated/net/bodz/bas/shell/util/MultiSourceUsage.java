package net.bodz.bas.shell.util;

import java.util.HashMap;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.meta.decl.NotNull;

public class MultiSourceUsage
        extends HashMap<Object, UsageData> {

    @NotNull
    public UsageData getOrCreate(@NotNull Object source) {
        UsageData usage = get(source);
        if (usage == null)
            put(source, usage = new UsageData(source));
        return usage;
    }

    public boolean isUsed() {
        for (Object k : keySet()) {
            String s = k.toString();
            if (s.contains("-add"))
                return true;
        }
        return false;
    }

    public void dump(ITreeOut out) {
        for (Object source : keySet()) {
            UsageData usage = get(source);
            out.printf("%s: %d\n", source, usage.count);
        }
    }

}
