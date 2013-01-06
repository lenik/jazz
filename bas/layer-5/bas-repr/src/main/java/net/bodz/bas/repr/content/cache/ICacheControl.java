package net.bodz.bas.repr.content.cache;

import java.util.Date;

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
     * Get the created date.
     */
    Date getCreatedDate();

    /**
     * Get the last modified date.
     */
    Date getLastModifiedDate();

    /**
     * Get the maximum amount of time that a representation will be considered fresh.
     * 
     * @return The number of seconds from the time of the request you wish the representation to be
     *         fresh for. Returns positive integer or zero.
     */
    int getMaxAge();

}
