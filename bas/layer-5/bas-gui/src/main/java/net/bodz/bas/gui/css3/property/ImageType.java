package net.bodz.bas.gui.css3.property;

import net.bodz.bas.gui.style.IFillType;
import net.bodz.bas.meta.source.boDzFeature;

public enum ImageType {

    none,

    uri,

    /**
     * @see IFillType
     */
    @boDzFeature
    pattern,

    ;

    public String format(String image) {
        switch (this) {
        case uri:
            if (image == null)
                throw new NullPointerException("image");
            else
                return "uri(\"" + image + "\")";

        case pattern:
            return "pattern(\"" + image + "\")";

        default:
            return this.toString();
        }
    }

}
