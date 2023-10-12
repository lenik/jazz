package net.bodz.bas.doc.node;

import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.io.res.IStreamInputSource;

public class Image
        extends AbstractDocPar
        implements
            IRun {

    String href;
    IStreamInputSource source;
    MeasureLength width;
    MeasureLength height;
    String description;

    public Image(INode parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.IMAGE;
    }

    @Override
    public boolean isRun() {
        return true;
    }

    @Override
    public String getText() {
        return description;
    }

    @Override
    public void buildText(StringBuilder a) {
        a.append(description);
    }

    @Override
    public void setText(String s) {
        description = s;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public IStreamInputSource getSource() {
        return source;
    }

    public void setSource(IStreamInputSource source) {
        this.source = source;
    }

    public MeasureLength getWidth() {
        return width;
    }

    public void setWidth(MeasureLength width) {
        this.width = width;
    }

    public MeasureLength getHeight() {
        return height;
    }

    public void setHeight(MeasureLength height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.image(this);
    }

}
