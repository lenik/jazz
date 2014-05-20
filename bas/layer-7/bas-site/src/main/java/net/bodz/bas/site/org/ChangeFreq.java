package net.bodz.bas.site.org;

public enum ChangeFreq {

    ALWAYS(0),

    HOURLY(60 * 60),

    DAILY(60 * 60 * 24),

    WEEKLY(60 * 60 * 24 * 7),

    MONTHLY(60 * 60 * 24 * 3651 / 48),

    YEARLY(60 * 60 * 24 * 3651 / 4),

    NEVER(-1),

    ;

    int timeout;

    /**
     * @param timeout
     *            in seconds.
     */
    private ChangeFreq(int timeout) {
        this.timeout = timeout;
    }

    /**
     * In seconds.
     */
    public int getTimeout() {
        return timeout;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    /**
     * @param maxAge
     *            in seconds.
     */
    public static ChangeFreq fromMaxAge(int maxAge) {
        if (maxAge < 0)
            return NEVER;
        if (maxAge == 0)
            return ALWAYS;
        if (maxAge < HOURLY.getTimeout())
            return HOURLY;
        if (maxAge < DAILY.getTimeout())
            return DAILY;
        if (maxAge < WEEKLY.getTimeout())
            return WEEKLY;
        if (maxAge < MONTHLY.getTimeout())
            return MONTHLY;
        return YEARLY;
    }

}