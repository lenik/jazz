package net.bodz.bas.io.html.tag;

import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;
import net.bodz.bas.io.xml.IXmlTagBuilder;

/**
  * <!--TOPIC:HTML-->The track element allows authors to specify explicit external timed text tracks for media elements. It does not represent anything on its own. 
  */
public class HtmlTrackBuilder
        extends DecoratedHtmlTagBuilder<HtmlTrackBuilder> {

    public HtmlTrackBuilder(IXmlTagBuilder o) {
        super(o);
    }

    /**
      * The kind attribute is an enumerated attribute. The following table lists the keywords defined for this attribute. The keyword given in the first cell of each row maps to the state given in the second cell. 
      */
    public HtmlTrackBuilder kind(String val) {
        attr("kind", val);
        return this;
    }

    /**
      * The src attribute gives the address of the text track data. The value must be a valid non-empty URL potentially surrounded by spaces. This attribute must be present. 
      */
    public HtmlTrackBuilder src(String val) {
        attr("src", val);
        return this;
    }

    /**
      * The srclang attribute gives the language of the text track data. The value must be a valid BCP 47 language tag. This attribute must be present if the element's kind attribute is in the subtitles state. [BCP47] 
      */
    public HtmlTrackBuilder srclang(String val) {
        attr("srclang", val);
        return this;
    }

    /**
      * The label attribute gives a user-readable title for the track. This title is used by user agents when listing subtitle, caption, and audio description tracks in their user interface. 
      */
    public HtmlTrackBuilder label(String val) {
        attr("label", val);
        return this;
    }

    /**
      * The default attribute is a boolean attribute, which, if specified, indicates that the track is to be enabled if the user's preferences do not indicate that another track would be more appropriate. 
      */
    public HtmlTrackBuilder default_(String val) {
        attr("default", val);
        return this;
    }

}
