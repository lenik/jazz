package net.bodz.bas.i18n.dom1;

import java.util.Set;

public interface ITagged {
    /**
     * Specify the category names this element belongs to.
     * 
     * @return Empty set if no tag is specified.
     */
    Set<String> getTagNames();

}
