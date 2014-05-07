package net.bodz.bas.ui.css3.property;

public enum EmptyCellsMode {

    /**
     * borders and backgrounds are drawn around/behind empty cells (like normal cells).
     */
    show,

    /**
     * that no borders or backgrounds are drawn around/behind empty cells. Furthermore, if all the
     * cells in a row have a value of 'hide' and have no visible content, then the row has zero
     * height and there is vertical border-spacing on only one side of the row.
     */
    hide,

}
