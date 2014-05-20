package net.bodz.bas.site.org;

import java.util.ArrayList;
import java.util.Date;

import net.bodz.bas.std.rfc.http.CacheControlMode;
import net.bodz.bas.std.rfc.http.CacheRevalidationMode;
import net.bodz.bas.std.rfc.http.ICacheControl;

public class Sitemap
        extends ArrayList<SitemapEntry>
        implements ICacheControl {

    private static final long serialVersionUID = 1L;

    public void normalizePriorities(double newMin, double newMax) {
        int n = size();
        double min = 0;
        double max = 0;
        for (int i = 0; i < n; i++) {
            double priority = get(i).getPriority();
            if (i == 0)
                min = max = priority;
            else if (priority < min)
                min = priority;
            else if (priority > max)
                max = priority;
        }

        if (min == max || min == Double.NaN || max == Double.NaN) {
            for (int i = 0; i < n; i++)
                get(i).setPriority(0.5);
            return;
        }

        double w0 = max - min;
        double w1 = newMax - newMin;
        for (int i = 0; i < n; i++) {
            SitemapEntry entry = get(i);
            double priority = entry.getPriority();
            double newPriority = (priority - min) / w0 * w1 + newMin;
            entry.setPriority(newPriority);
        }
    }

    /** ⇱ Implementation Of {@link ICacheControl}. */
    /* _____________________________ */static section.iface __CACHE__;

    @Override
    public CacheControlMode getCacheControlMode() {
        return CacheControlMode.AUTO;
    }

    @Override
    public CacheRevalidationMode getCacheRevalidationMode() {
        return CacheRevalidationMode.WANTED;
    }

    @Override
    public int getMaxAge() {
        return 86400;
    }

    @Override
    public Date getLastModified() {
        return new Date();
    }

    @Override
    public String getETag() {
        return null;
    }

    @Override
    public boolean isWeakValidation() {
        return true;
    }

}
