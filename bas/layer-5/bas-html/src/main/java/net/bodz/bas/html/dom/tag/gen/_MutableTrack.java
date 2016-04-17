package net.bodz.bas.html.dom.tag.gen;

import net.bodz.bas.html.dom.IHtmlTag;
import net.bodz.bas.html.dom.MutableHtmlTag;

/**
  * <!--TOPIC:HTML-->The track element allows authors to specify explicit external timed text tracks for media elements. It does not represent anything on its own. 
  */
@SuppressWarnings("unchecked")
public class _MutableTrack<self_t extends IHtmlTag>
        extends MutableHtmlTag<self_t> {

    public _MutableTrack(IHtmlTag parent, String tagName) {
        super(parent, tagName);
    }

    /**
      * The kind attribute is an enumerated attribute. The following table lists the keywords defined for this attribute. The keyword given in the first cell of each row maps to the state given in the second cell. 
      */
    public self_t kind(Object val) {
        attr("kind", val);
        return (self_t) this;
    }

    /**
      * The src attribute gives the address of the text track data. The value must be a valid non-empty URL potentially surrounded by spaces. This attribute must be present. 
      */
    public self_t src(Object val) {
        attr("src", val);
        return (self_t) this;
    }

    /**
      * The srclang attribute gives the language of the text track data. The value must be a valid BCP 47 language tag. This attribute must be present if the element's kind attribute is in the subtitles state. [BCP47] 
      */
    public self_t srclang(Object val) {
        attr("srclang", val);
        return (self_t) this;
    }

    /**
      * The label attribute gives a user-readable title for the track. This title is used by user agents when listing subtitle, caption, and audio description tracks in their user interface. 
      */
    public self_t label(Object val) {
        attr("label", val);
        return (self_t) this;
    }

    /**
      * The default attribute is a boolean attribute, which, if specified, indicates that the track is to be enabled if the user's preferences do not indicate that another track would be more appropriate. 
      */
    public self_t default_(Object val) {
        attr("default", val);
        return (self_t) this;
    }

}
