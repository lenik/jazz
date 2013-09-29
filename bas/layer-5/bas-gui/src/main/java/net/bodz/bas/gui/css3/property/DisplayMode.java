package net.bodz.bas.gui.css3.property;

public enum DisplayMode {

    /** This value causes an element to generate a block box. */
    block,

    /**
     * This value causes an element to generate an inline-level block container. The inside of an
     * inline-block is formatted as a block box, and the element itself is formatted as an atomic
     * inline-level box.
     */
    inline_block,

    /** This value causes an element to generate one or more inline boxes. */
    inline_,

    /**
     * This value causes an element (e.g., LI in HTML) to generate a principal block box and a
     * marker box. For information about lists and examples of list formatting, please consult the
     * section on lists.
     */
    list_item,

    /**
     * This value causes an element to not appear in the formatting structure (i.e., in visual media
     * the element generates no boxes and has no effect on layout). Descendant elements do not
     * generate any boxes either; the element and its content are removed from the formatting
     * structure entirely. This behavior cannot be overridden by setting the 'display' property on
     * the descendants.
     * 
     * Please note that a display of 'none' does not create an invisible box; it creates no box at
     * all. CSS includes mechanisms that enable an element to generate boxes in the formatting
     * structure that affect formatting but are not visible themselves. Please consult the section
     * on visibility for details.,
     * */
    none,

    /*
     * These values cause an element to behave like a table element (subject to restrictions
     * described in the chapter on tables).
     */
    table, inline_table, table_row_group, table_column, table_column_group, //
    table_header_group, table_footer_group, table_row, table_cell, //
    table_caption,

}
