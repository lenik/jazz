package net.bodz.bas.codegen.util;

import java.util.Comparator;
import java.util.TreeMap;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.proxy.java.util.DecoratedMap;
import net.bodz.bas.repr.form.SortOrder;

public class UsageMap<T>
        extends DecoratedMap<T, MultiSourceUsage> {

    public UsageMap() {
        this(SortOrder.KEEP);
    }

    public UsageMap(@NotNull SortOrder order) {
        super(order.newMapDefault());
    }

    public UsageMap(@NotNull Comparator<? super T> cmp) {
        super(new TreeMap<>(cmp));
    }

    public MultiSourceUsage getOrCreate(@NotNull T key) {
        MultiSourceUsage usageMap = get(key);
        if (usageMap == null)
            put(key, usageMap = new MultiSourceUsage());
        return usageMap;
    }

    @NotNull
    public UsageData getUsage(T key, @NotNull Object source) {
        MultiSourceUsage usageMap = getOrCreate(key);
        return usageMap.getOrCreate(source);
    }

    public void add(T key, @NotNull String source) {
        UsageData data = getUsage(key, source);
        data.count++;
    }

    public void remove(T key, @NotNull String source) {
        UsageData data = getUsage(key, source);
        data.count--;
    }

    public void dump(ITreeOut out) {
        for (T key : keySet()) {
            out.println(key + ": ");
            out.enter();
            MultiSourceUsage usages = get(key);
            usages.dump(out);
            out.leaveln();
        }
    }
}
