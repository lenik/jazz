package net.bodz.bas.repr.rest;

public interface StandardViews {

    /**
     * The index listing for the specific object type.
     * 
     * @see #FORMAT_PARAM
     */
    public static final String LIST = "list";

    /**
     * The normal view of the object.
     * 
     * @see #ID_PARAM
     * @see #FORMAT_PARAM
     */
    public static final String CONTENT = "content";

    /**
     * The creating form of the object.
     */
    public static final String CREATE_FORM = "createForm";

    /**
     * The editing form of the object.
     */
    public static final String EDIT_FORM = "editForm";

    /**
     * The id of object (or entity).
     */
    public static final String ID_PARAM = "id";

    /**
     * The presentation format.
     * 
     * This value could be:
     * <ul>
     * <li>PLAIN: Plain (generally pre-formatted) text format.
     * <li>HTML: Rich text in HTML.
     * <li>CSV: Machine readable CSV format.
     * <li>PDF: Portable document format.
     * </ul>
     */
    public static final String FORMAT_PARAM = "format";

}
