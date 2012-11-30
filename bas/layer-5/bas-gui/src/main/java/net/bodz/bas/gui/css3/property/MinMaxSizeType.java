package net.bodz.bas.gui.css3.property;

public enum MinMaxSizeType {

    /** Specifies a fixed minimum or maximum computed height. */
    length,

    /**
     * Specifies a percentage for determining the used value. The percentage is calculated with
     * respect to the height of the generated box's containing block. If the height of the
     * containing block is not specified explicitly (i.e., it depends on content height), and this
     * element is not absolutely positioned, the percentage value is treated as '0' (for
     * 'min-height') or 'none' (for 'max-height').
     */
    percentage,

    /** (Only on 'max-height') No limit on the height of the box. */
    none,

}
