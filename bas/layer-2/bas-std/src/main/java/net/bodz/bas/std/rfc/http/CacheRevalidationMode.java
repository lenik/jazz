package net.bodz.bas.std.rfc.http;

public enum CacheRevalidationMode {

    /**
     * Tells caches that they must obey any freshness information you give them about a
     * representation.
     * 
     * aka. must-revalidate
     */
    REQUIRED,

    /**
     * Similar to {@link #REQUIRED}, except that it only applies to proxy caches.
     */
    WANTED,

    OPTIONAL,

}
