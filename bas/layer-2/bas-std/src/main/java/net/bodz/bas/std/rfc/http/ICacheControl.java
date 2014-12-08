package net.bodz.bas.std.rfc.http;

/**
 * @label Cache Control
 * @label.zh.cn 缓存控制
 */
public interface ICacheControl {

    /**
     * Get cache control mode.
     * 
     * @return Non-<code>null</code> value.
     */
    CacheControlMode getCacheControlMode();

    /**
     * Get cache revalidation mode.
     * 
     * @return Non-<code>null</code> value.
     */
    CacheRevalidationMode getCacheRevalidationMode();

    /**
     * Get the maximum amount of time that a representation will be considered fresh.
     * 
     * @return The number of seconds from the time of the request you wish the representation to be
     *         fresh for. Returns positive integer or zero.
     */
    int getMaxAge();

    /**
     * Get the last modified date.
     */
    long getLastModified();

    /**
     * An ETag is an opaque identifier assigned by a web server to a specific version of a resource
     * found at a URL. If the resource content at that URL ever changes, a new and different ETag is
     * assigned. Used in this manner ETags are similar to fingerprints, and they can be quickly
     * compared to determine whether two versions of a resource are the same. Comparing ETags only
     * makes sense with respect to one URL—ETags for resources obtained from different URLs may or
     * may not be equal, so no meaning can be inferred from their comparison.
     */
    String getETag();

    /**
     * A weakly validating ETag match only indicates that the two resources are semantically
     * equivalent, meaning that for practical purposes they are interchangeable and that cached
     * copies can be used. However the resources are not necessarily byte-for-byte identical, and
     * thus weak ETags are not suitable for byte-range requests. Weak ETags may be useful for cases
     * in which strong ETags are impractical for a web server to generate, such as with
     * dynamically-generated content.
     * 
     * @return <code>true</code> for weak validation.
     */
    boolean isWeakValidation();

}
