package net.bodz.bas.ui.css3;

import java.io.Serializable;

import net.bodz.bas.ui.css3.property.ContentItemType;

public class ContentItem
        implements Serializable {

    private static final long serialVersionUID = 1L;

    ContentItemType type;
    String content;

    public ContentItemType getType() {
        return type;
    }

    public void setType(ContentItemType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
