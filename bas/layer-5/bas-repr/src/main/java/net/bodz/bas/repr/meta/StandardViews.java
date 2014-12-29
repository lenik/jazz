package net.bodz.bas.repr.meta;

public @interface StandardViews {

    /**
     * The index listing for the specific object type.
     * 
     * @see StandardViews#PARAM_FORMAT
     */
    @interface Index {
    }

    /**
     * The normal view of the object.
     * 
     * @see StandardViews#PARAM_ID
     * @see StandardViews#PARAM_FORMAT
     */
    @interface Content {
    }

    /**
     * The creating form of the object.
     * 
     * @see StandardViews#PARAM_ID Copy-From-Id. Initial values from this instance.
     */
    @interface CreateForm {
    }

    /**
     * The editing form of the object.
     * 
     * @see StandardViews#PARAM_ID
     */
    @interface EditForm {
    }

    /**
     * The id of object (or entity).
     */
    String PARAM_ID = "id";

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
    String PARAM_FORMAT = "format";

}
