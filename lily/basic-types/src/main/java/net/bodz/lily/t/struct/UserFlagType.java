package net.bodz.lily.t.struct;

public enum UserFlagType {

    /**
     * Exists only to promote a product or service, does not disclose the author's affiliation.
     */
    SPAM,

    /**
     * rude or abusive.
     *
     * A reasonable person would find this content inappropriate for respectful discourse.
     */
    RUDE,

    /**
     * The content is completely unclear, incomplete, overly-broad, primarily opinion-based, and it
     * is unlikely to be fixed via editing.
     */
    SHOULD_BE_CLOSED,

    /**
     * The content has been seen before.
     */
    DUPLICATE,

    /**
     * in need of moderator intervention.
     *
     * A problem not lised above that requires action by a moderator. <i>Be specific and
     * detailed.</i>
     */
    MODERATE,

}
