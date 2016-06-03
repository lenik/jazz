package net.bodz.bas.std.rfc.http;

public enum CacheRevalidationMode {

    /**
     * Tells caches that they must obey any freshness information you give them about a
     * representation.
     * 
     * When the must-revalidate directive is present in a response received by a cache, that cache
     * MUST NOT use the entry after it becomes stale to respond to a subsequent request without
     * first revalidating it with the origin server
     */
    MUST_REVALIDATE("must-revalidate"),

    /**
     * If the no-cache directive does not specify a field-name, then a cache MUST NOT use the
     * response to satisfy a subsequent request without successful revalidation with the origin
     * server. This allows an origin server to prevent caching even by caches that have been
     * configured to return stale responses to client requests.
     */
    NO_CACHE("no-cache"),

    /**
     * Similar to {@link #MUST_REVALIDATE}, except that it only applies to proxy caches.
     */
    PROXY_REVALIDATE("proxy-revalidate"),

    OPTIONAL(null),

    ;

    String httpEquivContent;

    private CacheRevalidationMode(String httpEquivContent) {
        this.httpEquivContent = httpEquivContent;
    }

    public String getHttpEquivContent() {
        return httpEquivContent;
    }

    public static final CacheRevalidationMode DEFAULT = OPTIONAL;

}
