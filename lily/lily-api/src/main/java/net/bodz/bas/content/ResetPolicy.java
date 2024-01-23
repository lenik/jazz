package net.bodz.bas.content;

import net.bodz.bas.content.ContentSelection.Builder;

public enum ResetPolicy {

    /**
     * Try best to release allocated stuff, while keep the object allocated.
     *
     * The operation will destory most of the information in the object.
     */
    CONTENT(new Builder().all()),

    /**
     * Clear out the identity, so the object can be used as a different one.
     */
    ID(new Builder().id()),

    /**
     * Try best to reduce memory occupied by the object.
     * <ul>
     * <li>Delete extra allocated buffer;
     * <li>Drop caches
     * </ul>
     *
     * This could be slow.
     */
    COMPACT(new Builder().buffer()),

    /**
     * Delete unimporant pieces.
     *
     * This should be fast.
     */
    CLEAN(new Builder().cache()),

    TOUCH(new Builder().lastModifiedTime()),

    ;

    final ContentSelection selection;

    private ResetPolicy(Builder builder) {
        selection = builder.build();
    }

    public ContentSelection getContentSelection() {
        return selection;
    }

}
