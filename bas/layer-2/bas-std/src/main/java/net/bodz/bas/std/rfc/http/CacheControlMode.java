package net.bodz.bas.std.rfc.http;

public enum CacheControlMode {

    /**
     * Marks authenticated responses as cacheable; normally, if HTTP authentication is required,
     * responses are automatically private.
     */
    PUBLIC,

    /**
     * Automatically determine public or private.
     */
    AUTO,

    /**
     * Allows caches that are specific to one user (e.g., in a browser) to store the response;
     * shared caches (e.g., in a proxy) may not.
     */
    PRIVATE,

    /**
     * Forces caches to submit the request to the origin server for validation before releasing a
     * cached copy, every time. This is useful to assure that authentication is respected (in
     * combination with public), or to maintain rigid freshness, without sacrificing all of the
     * benefits of caching.
     */
    NO_CACHE,

    /**
     * Instructs caches not to keep a copy of the representation under any conditions
     */
    NO_STORE,

}
