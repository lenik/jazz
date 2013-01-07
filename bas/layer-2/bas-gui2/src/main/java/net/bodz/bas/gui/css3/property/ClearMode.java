package net.bodz.bas.gui.css3.property;

public enum ClearMode {

    /**
     * Requires that the top border edge of the box be below the bottom outer edge of any
     * left-floating boxes that resulted from elements earlier in the source document.
     */
    left,

    /**
     * Requires that the top border edge of the box be below the bottom outer edge of any
     * right-floating boxes that resulted from elements earlier in the source document.
     */
    right,

    /**
     * Requires that the top border edge of the box be below the bottom outer edge of any
     * right-floating and left-floating boxes that resulted from elements earlier in the source
     * document.
     */
    both,

    /** No constraint on the box's position with respect to floats. */
    none,

}
